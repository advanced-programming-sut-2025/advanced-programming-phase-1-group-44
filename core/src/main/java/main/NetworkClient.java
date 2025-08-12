package main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import controller.GameMenuController;
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
        client = new Client(16384, 65536);
        client.start();

        // Register classes for Kryo serialization
        Kryo kryo = client.getKryo();


       NetworkRegistry.registerClasses(kryo);

        // Add listener for server events
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
                    String message = (String) object;
                    System.out.println("Received from server: " + message);
                } else if (object instanceof HashMap) {

                    Map<String, Object> data = (Map<String, Object>) object;
                    String password, email, nickname;

                    switch ((String) data.get("command")) {
                        case "initialData":
                            ArrayList<miniPlayer> playerList = (ArrayList<miniPlayer>) data.get("playerList");

                            for (miniPlayer player : playerList) {
                                controller.register(player);
                            }

                            System.out.println("initialized!!");
                            break;

                        case "newSignup":

                            miniPlayer player = (miniPlayer) data.get("player");
                            controller.register(player);
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

                            System.out.println("profile changed for:" + " " + newUsername);
                            break;
                        case "lobbiesList":
                            ArrayList<Lobby> newLobbies = (ArrayList<Lobby>) data.get("lobbies");
                            LobbyMenuScreen.lobbies = newLobbies;

                            if (Main.getMain().getScreen() instanceof LobbyMenuScreen) {
                                ((LobbyMenuScreen) Main.getMain().getScreen()).updateLobbiesDisplay();
                            }
                            break;
                        case "getLobby":
                            System.out.println("new Lobby !!!");
                            Main.lobby = (Lobby) data.get("lobby");
                            //System.out.println(Main.lobby.name);
                            break;
                        case "startGame":
                            player = (miniPlayer) data.get("player");
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
                            if(App.getAdmin().getUsername().equals(receiver)){
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
                            System.out.println("Debug Get Quests");
                            if(Main.getMain().getScreen() instanceof QuestMenuScreen){
                                System.out.println("Salam");
                                Gdx.app.postRunnable(() -> {
                                    Main.setMenu(new QuestMenuScreen());
                                    System.out.println("bye");
                                });
                            }
                            break;
                    }
                }
            }
        });

        // Connect to server
        try {
            System.out.println("Attempting to connect to localhost:1010...");
            client.connect(5000, "localhost", 54555, 54777);
        } catch (IOException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void register(Player player) {
        Map<String, Object> mp = new HashMap<>();
        miniPlayer minPlayer = new miniPlayer(player.getUsername(), player.getPassword(), player.getEmail(), player.getGender().name());
        mp.put("player", minPlayer);
        mp.put("command", "register");

        client.sendTCP(mp);
    }

    public void login(Player player) {
        Map<String, Object> mp = new HashMap<>();
        miniPlayer minPlayer = new miniPlayer(player.getUsername(), player.getPassword(), player.getEmail(), player.getGender().name());
        mp.put("player", minPlayer);
        mp.put("command", "login");

        client.sendTCP(mp);
    }

    public void logout() {
        Player player = App.getAdmin();
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "logout");
        mp.put("username", player.getUsername());

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
    public void buildLobby(Lobby lobby){
        Map<String , Object> mp = new HashMap<>();
        mp.put("command", "newLobby");
        mp.put("lobby" , lobby);
        client.sendTCP(mp);
    }

    public void getLobbies(){
        Map<String, Object> mp = new HashMap<>();
        //System.out.println("lobby request sent!");
        mp.put("command", "getLobbies");
        client.sendTCP(mp);
    }

    public void addPlayer(Lobby lobby, miniPlayer player){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "addPlayer");
        mp.put("lobby" , lobby);
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void getLobby(miniPlayer player){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getLobby");
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void removePlayer(miniPlayer player){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "removePlayer");
        mp.put("player", player);
        client.sendTCP(mp);
    }

    public void createGame(miniPlayer player){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command" , "startGame");
        mp.put("player" , player);
        client.sendTCP(mp);
    }

    public void offerTrade(Player sender, String receiver){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command" , "offerTrade");
        mp.put("sender" , sender.getUsername());
        mp.put("receiver", receiver);
        client.sendTCP(mp);
    }

    public void joinQuest(String quest, String player){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "joinQuest");
        mp.put("quest" , quest);
        mp.put("player", player);
        //TODO: fix this.
        mp.put("time", new DateTime());
        client.sendTCP(mp);
    }
    public void getQuests(){
        Map<String, Object> mp = new HashMap<>();
        mp.put("command", "getQuests");
        client.sendTCP(mp);
    }
}


