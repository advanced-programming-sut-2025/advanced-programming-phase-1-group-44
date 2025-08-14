package main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import controller.GameMenuController;
import controller.GamePlayController;
import controller.ProfileMenuController;
import controller.SignupMenuController;
import model.*;
import model.enums.Menu;
import view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkClient {

    private Client client;
    private String token;

    public void start() {
        SignupMenuController controller = new SignupMenuController();
        client = new Client(1010, 1100);
        client.start();

        Kryo kryo = client.getKryo();
        NetworkRegistry.registerClasses(kryo);

        client.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                Map<String, Object> data = new HashMap<>();
                data.put("command", "newClient");
                client.sendTCP(data);
            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("Disconnected from server: " + connection.getID());
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println("Received from server: " + object);
                } else if (object instanceof HashMap) {
                    Map<String, Object> data = (Map<String, Object>) object;
                    String password, email, nickname, username, message;

                    AppMenu menu;

                    switch ((String) data.get("command")) {
                        case "initialData":
                            ArrayList<miniPlayer> playerList = (ArrayList<miniPlayer>) data.get("playerList");
                            for (miniPlayer player : playerList) controller.register(player);
                            System.out.println("initialized!!");
                            break;

                        case "newSignup":
                            controller.register((miniPlayer) data.get("player"));
                            break;

                        case "getToken":
                            token = (String) data.get("token");
                            break;

                        case "changeProfile":
                            String oldUsername = (String) data.get("oldUsername");
                            String newUsername = (String) data.get("newUsername");
                            String oldPassword = (String) data.get("oldPassword");
                            password = (String) data.get("password");
                            email = (String) data.get("email");
                            nickname = (String) data.get("nickname");

                            ProfileMenuController profileMenuController = new ProfileMenuController();
                            profileMenuController.changeEmail2(oldUsername, email);
                            profileMenuController.changePassword2(oldUsername, password);
                            profileMenuController.changeNickname2(oldUsername, nickname);
                            profileMenuController.changeUsername2(oldUsername, newUsername);
                            System.out.println("profile changed for: " + newUsername);
                            break;

                        case "lobbiesList":
                            LobbyMenuScreen.lobbies = (ArrayList<Lobby>) data.get("lobbies");
                            if (Main.getMain().getScreen() instanceof LobbyMenuScreen) {
                                ((LobbyMenuScreen) Main.getMain().getScreen()).updateLobbiesDisplay();
                            }
                            break;

                        case "getLobby":
                            System.out.println("new Lobby !!!");
                            Main.lobby = (Lobby) data.get("lobby");
                            break;

                        case "startGame":
                            miniPlayer player = (miniPlayer) data.get("player");
                            ArrayList<miniPlayer> players = (ArrayList<miniPlayer>) data.get("players");
                            boolean flg = false;
                            for (miniPlayer miniPlayer : players) {
                                if (miniPlayer.username.equals(player.username)) {
                                    flg = true;
                                    break;
                                }
                            }
                            if (flg) {
                                getQuests();
                                Gdx.app.postRunnable(() -> {
                                    GameMenuController mc = new GameMenuController();
                                    ArrayList<String> playerNames = new ArrayList<>();
                                    for (miniPlayer miniPlayer : players) {
                                        playerNames.add(miniPlayer.username);
                                    }
                                    mc.createNewGame(playerNames);
                                    Main.setMenu(new gameplayScreen());
                                });
                            }
                            break;

                        case "offerTrade":
                            String receiver = (String) data.get("receiver");
                            if (App.getAdmin().getUsername().equals(receiver)) {
                                Stage stage = ((AppMenu) Main.getMain().getScreen()).getStage();
                                TradeOfferDialog dialog = new TradeOfferDialog(data);
                                dialog.setMessage(data.get("sender") + " wants to trade with you!");
                                dialog.show(stage);
                            }
                            break;

                        case "refreshQuest":
                            Map<String, Object> mpQuest = new HashMap<>();
                            mpQuest.put("command", "getQuests");
                            client.sendTCP(mpQuest);
                            break;

                        case "getQuests":
                            Main.quests = (ArrayList<GroupQuest>) data.get("quests");
                            if (Main.getMain().getScreen() instanceof QuestMenuScreen) {
                                Gdx.app.postRunnable(() -> Main.setMenu(new QuestMenuScreen()));
                            }
                            break;

                        case "chatHistory":
                            ChatScreen chatMenu = (ChatScreen) Main.getMain().getScreen();
                            chatMenu.initializeChats(data);

                            System.out.println("got the history");
                            break;

                        case "sentPublicMessage":
                            username = (String) data.get("sender");
                            message = (String) data.get("message");
                            if (Main.getMain().getScreen() instanceof  ChatScreen)
                                Gdx.app.postRunnable(() -> ((ChatScreen) Main.getMain().getScreen()).addPublicMessage(message));
                            break;

                        case "sentPrivateMessage":
                            miniPlayer sender = (miniPlayer) data.get("sender");
                            miniPlayer recv = (miniPlayer) data.get("receiver");
                            String privateMsg = (String) data.get("message");

                            Player senderP = App.findUserByUsername(sender.getUsername());
                            Player receiverP = App.findUserByUsername(recv.getUsername());

                            App.getCurrentGame().talk(privateMsg, senderP, receiverP);
                            AppMenu screen = (AppMenu) Main.getMain().getScreen();

                            if (screen instanceof  ChatScreen) {

                                Gdx.app.postRunnable(() -> {
                                    ChatScreen chatScreenPrivate = (ChatScreen) Main.getMain().getScreen();
                                    miniPlayer otherPlayer;
                                    String fullMessage;
                                    if (sender.getUsername().equals(App.getAdmin().getUsername())) {
                                        otherPlayer = recv;
                                        fullMessage = sender.getUsername() + ": " + privateMsg;
                                    } else if (recv.getUsername().equals(App.getAdmin().getUsername())) {
                                        otherPlayer = sender;
                                        fullMessage = sender.getUsername() + ": " + privateMsg;
                                    } else return;
                                    chatScreenPrivate.addPrivateMessage(otherPlayer, fullMessage);
                                    chatScreenPrivate.updateChatMessages();
                                });
                            }

                            else if (screen instanceof FarmobjScreen) {
                                ((FarmobjScreen) screen).notify("Message", "new message from " + sender.getUsername());
                            }
                            else if (screen instanceof gameplayScreen) {
                                ((gameplayScreen) screen).notify("Message", "new message from " + sender.getUsername());
                            }
                            break;

                        case "startTerminateVote":
                            menu = (AppMenu) Main.getMain().getScreen();
                            if (menu instanceof FarmobjScreen) {
                                ((FarmobjScreen) menu).startVote("terminate game?");
                            }
                            else if (menu instanceof gameplayScreen) {
                                ((gameplayScreen) menu).startVote("terminate game?");
                            }

                            break;
                        case "startKickVote":
                            menu = (AppMenu) Main.getMain().getScreen();
                            if (menu instanceof FarmobjScreen) {
                                ((FarmobjScreen) menu).startVote("kick " + (String) data.get("targetPlayerUsername") + "?");
                            }
                            else if (menu instanceof gameplayScreen) {
                                ((gameplayScreen) menu).startVote("kick " + (String) data.get("targetPlayerUsername") + "?");
                            }
                            break;
                        case "voteFinished":
                            int res = (int) data.get("result");
                            boolean voteType = (boolean) data.get("voteType");
                            player = (miniPlayer) data.get("playerVote");
                            if (!voteType) { // kick
                                Gdx.app.postRunnable(() -> {
                                    if (res == 2) Main.getMain().dispose();

                                    else {
                                        showVoteResult("nothing happened in vote!");
                                    }
                                });

                                return;
                            }

                            else {
                                if (res == 2) {
                                    showVoteResult("kicked " + player.getUsername() + "!");
                                }
                                else showVoteResult("nothing happened in vote!");

                            }
                            break;
                        case "sendGift": // actually receive gift
                            username = (String) data.get("username");
                            String senderUsername = (String) data.get("sender");
                            GamePlayController gamePlayController = new GamePlayController();
                            String itemName = (String) data.get("itemName");
                            int amount = (int) data.get("amount");
                            gamePlayController.receiveGift(username, itemName, amount);
                            break;

                        case "kicked":
                            // TODO
                            break;

                        case "getPlayersList":
                        case "getScoreboard":
                            // Handle scoreboard or players list updates here if needed
                            break;
                    }
                }
            }
        });

        try {
            System.out.println("Attempting to connect to localhost:1010...");
            client.connect(5000, "localhost", 54555, 54777);
        } catch (IOException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private void showVoteResult(String des) {
        AppMenu menu = (AppMenu) Main.getMain().getScreen();
        if (menu instanceof FarmobjScreen) {
            ((FarmobjScreen) menu).showVoteResult(des);
        }
        else if (menu instanceof gameplayScreen) {
            ((gameplayScreen) menu).showVoteResult(des);
        }
    }

    // --- Shared Methods ---
    public void register(Player player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("player", new miniPlayer(player.getUsername(), player.getPassword(), player.getEmail(), player.getGender().name()));
        mp.put("command", "register");
        client.sendTCP(mp);
    }

    public void login(Player player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("player", new miniPlayer(player.getUsername(), player.getPassword(), player.getEmail(), player.getGender().name()));
        mp.put("command", "login");
        client.sendTCP(mp);
    }

    public void logout() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "logout");
        mp.put("username", App.getAdmin().getUsername());
        client.sendTCP(mp);
    }

    public void changeProfile(String oldUsername, String newUsername, String oldPassword, String password, String email, String nickname) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "changeProfile");
        mp.put("oldPassword", oldPassword);
        mp.put("oldUsername", oldUsername);
        mp.put("newUsername", newUsername);
        mp.put("password", password);
        mp.put("nickname", nickname);
        mp.put("email", email);
        client.sendTCP(mp);
    }

    public void buildLobby(Lobby lobby) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "newLobby");
        mp.put("lobby", lobby);
        client.sendTCP(mp);
    }

    public void getLobbies() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getLobbies");
        client.sendTCP(mp);
    }

    public void addPlayer(Lobby lobby, miniPlayer player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "addPlayer");
        mp.put("lobby", lobby);
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void getLobby(miniPlayer player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getLobby");
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void removePlayer(miniPlayer player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "removePlayer");
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void createGame(miniPlayer player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "startGame");
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void offerTrade(Player sender, String receiver) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "offerTrade");
        mp.put("sender", sender.getUsername());
        mp.put("receiver", receiver);
        client.sendTCP(mp);
    }

    public void joinQuest(String quest, String player) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "joinQuest");
        mp.put("quest", quest);
        mp.put("player", player);
        mp.put("time", new DateTime()); // assuming DateTime is available
        client.sendTCP(mp);
    }

    public void getQuests() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getQuests");
        client.sendTCP(mp);
    }

    public void getChats() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getChatHistory");
        mp.put("username", App.getAdmin().getUsername());
        client.sendTCP(mp);
    }

    public void sendPublicMessage(String username, String message) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "sentPublicMessage");
        mp.put("sender", username);
        mp.put("message", username + ": " + message);
        client.sendTCP(mp);
    }

    public void sendPrivateMessage(String sender, String receiver, String message) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "sentPrivateMessage");
        mp.put("sender", sender);
        mp.put("receiver", receiver);
        mp.put("message", message);
        client.sendTCP(mp);

        Player senderP = App.findUserByUsername(sender);
        Player receiverP = App.findUserByUsername(receiver);

        App.getCurrentGame().talk(message, senderP, receiverP);
    }

    public void getPlayersList() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getPlayersList");
        mp.put("username", App.getAdmin().getUsername());
        client.sendTCP(mp);
    }

    public void requestScoreboard() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getScoreboard");
        mp.put("username", App.getAdmin().getUsername());
        client.sendTCP(mp);
    }

    public void startTerminateVote() {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "startTerminateVote");
        mp.put("lobby", Main.lobby);
        mp.put("username", App.getAdmin().getUsername());
        client.sendTCP(mp);
    }

    public void startKickVote(String targetPlayer) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "startKickVote");
        mp.put("lobby", Main.lobby);
        mp.put("username", App.getAdmin().getUsername());
        mp.put("targetPlayerUsername", targetPlayer);
        client.sendTCP(mp);
    }

    public void sendVote(boolean result) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "vote");
        mp.put("username", App.getAdmin().getUsername());
        mp.put("vote", result);
        client.sendTCP(mp);
    }

    public void sendGift(String username, String itemName, int amount) {
        Map<String , Object> mp = new HashMap<>();
        mp.put("command", "sendGift");
        mp.put("sender", App.getAdmin().getUsername());
        mp.put("username", username);
        mp.put("itemName", itemName);
        mp.put("amount", amount);

        client.sendTCP(mp);
    }
}