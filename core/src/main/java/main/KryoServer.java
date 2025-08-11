package main;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import model.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KryoServer {

    // Thread pool for handling client messages
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final Server server = new Server(16384, 65536);

    public KryoServer() throws IOException {

        NetworkRegistry.registerClasses(server.getKryo());
        server.start();
        server.bind(54555, 54777);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                executor.submit(() -> handleReceived(connection, object));
            }

            @Override
            public void disconnected(Connection connection) {
                // Remove player on disconnect
                miniPlayer disconnected = null;
                for (miniPlayer p : DataCenter.getConnectedPlayers()) {
                    Connection stored = DataCenter.getConnection(p);
                    if (stored != null && stored.getID() == connection.getID()) {
                        disconnected = p;
                        break;
                    }
                }
                if (disconnected != null) {
                    DataCenter.disconnectPlayerConnection(disconnected);
                    DataCenter.disconnectPlayer(disconnected);
                    System.out.println("Player disconnected: " + disconnected.getUsername());
                }
            }
        });

        System.out.println("server started!");
    }

    private void handleReceived(Connection connection, Object object) {
        if (object instanceof String) {
            System.out.println((String) object);
            return;
        }

        if (object instanceof HashMap) {
            Map<String, Object> data = (Map<String, Object>) object;

            miniPlayer player;
            String username, password, nickname, email;
            Map<String, Object> mp = new HashMap<>();

            switch ((String) data.get("command")) {
                case "newClient":
                    System.out.println("New client connected. Sending player list...");
                    Map<String, Object> payload = new HashMap<>();
                    payload.put("command", "initialData");
                    payload.put("playerList", DataCenter.getPlayerList());
                    connection.sendTCP(payload);
                    break;

                case "register":
                    player = (miniPlayer) data.get("player");
                    DataCenter.addPlayer(player);
                    Map<String, Object> message = new HashMap<>();
                    message.put("command", "newSignup");
                    message.put("player", player);
                    for (Connection conn : server.getConnections()) {
                        if (conn.getID() != connection.getID()) {
                            conn.sendTCP(message);
                        }
                    }
                    break;

                case "login":
                    player = (miniPlayer) data.get("player");
                    String newToken = JwtUtils.generateToken(player.username, HashUtils.hashPassword(player.password), player.tokenVersion);

                    mp = new HashMap<>();
                    mp.put("command", "getToken");
                    mp.put("token", newToken);
                    connection.sendTCP(mp);

                    DataCenter.connectPlayer(player);
                    DataCenter.connectPlayer(player, connection); // Store connection for sending messages
                    break;

                case "logout":
                    username = (String) data.get("username");
                    player = DataCenter.getUserByUsername(username);
                    if (player != null) {
                        DataCenter.disconnectPlayerConnection(player);
                        DataCenter.disconnectPlayer(player);
                    }
                    break;

                case "changeProfile":
                    String oldUsername = (String) data.get("oldUsername");
                    String newUsername = (String) data.get("newUsername");
                    password = (String) data.get("password");
                    email = (String) data.get("email");
                    nickname = (String) data.get("nickname");

                    player = DataCenter.getUserByUsername(oldUsername);
                    player.setUsername(newUsername);
                    player.setEmail(email);
                    player.setNickname(nickname);
                    player.setPassword(password);

                    for (Connection conn : server.getConnections()) {
                        if (conn.getID() != connection.getID()) {
                            conn.sendTCP(data);
                        }
                    }
                    break;

                case "newLobby":
                    Lobby lobby = (Lobby) data.get("lobby");
                    DataCenter.addLobby(lobby);
                    System.out.println("Lobby Received!");
                    break;

                case "getLobbies":
                    mp = new HashMap<>();
                    mp.put("lobbies", DataCenter.getLobbies());
                    mp.put("command", "lobbiesList");
                    connection.sendTCP(mp);
                    break;

                case "addPlayer":
                    lobby = DataCenter.getLobby((Lobby) data.get("lobby"));
                    lobby.addPlayer((miniPlayer) data.get("player"));
                    System.out.println("player " + ((miniPlayer) data.get("player")).getUsername() + " added to lobby " + lobby.getName());
                    break;

                case "getLobby":
                    player = (miniPlayer) data.get("player");
                    Lobby debug = getLobby(player);
                    Map<String, Object> mp2 = new HashMap<>();
                    mp2.put("lobby", debug);
                    mp2.put("command", "getLobby");
                    System.out.println("player " + player.getUsername() + " is in lobby " + debug.getName());
                    connection.sendTCP(mp2);
                    break;

                case "removePlayer":
                    player = (miniPlayer) data.get("player");
                    Lobby targetLobby = getLobby(player);
                    targetLobby.removePlayer(player);
                    break;

                case "getChatHistory":
                    username = (String) data.get("username");
                    player = DataCenter.getUserByUsername(username);
                    ArrayList<String> publicChat = DataCenter.getPublicChats(player);
                    Map<miniPlayer, ArrayList<String>> privateChats = DataCenter.getPrivateChats(player);

                    mp = new HashMap<>();
                    mp.put("command", "chatHistory");
                    mp.put("publicMessages", publicChat);
                    mp.put("privateMessages", privateChats);

                    sendToRoommates(player, mp);
                    break;

                case "sentPublicMessage":
                    username = (String) data.get("sender");
                    player = DataCenter.getUserByUsername(username);
                    DataCenter.sendPublicMessage(player, (String) data.get("message"));
                    sendToRoommates(player, data);
                    break;

                case "sentPrivateMessage":
                    username = (String) data.get("sender");
                    miniPlayer sender = DataCenter.getUserByUsername(username);
                    username = (String) data.get("receiver");
                    miniPlayer receiver = DataCenter.getUserByUsername(username);
                    DataCenter.sendPrivateMessage(sender, receiver, (String) data.get("message"));
                    sendToPlayer(receiver, data);
                    break;

                case "startVote":
                    boolean voteType = (boolean) data.get("voteType");
                    username = (String) data.get("username");
                    String playerVoteUsername = (String) data.get("playerVoteUsername");
                    player = DataCenter.getUserByUsername(username);
                    lobby = DataCenter.findLobbyByUser(player);
                    lobby.startVote(voteType, playerVoteUsername);

                    sendToRoommates(player, data);
                    break;

                case "vote":
                    username = (String) data.get("username");
                    miniPlayer voter = DataCenter.getUserByUsername(username);


                    boolean result = (boolean) data.get("vote");
                    lobby = DataCenter.findLobbyByUser(voter);
                    int res = lobby.addVote(result);
                    if (res > 0) {

                        mp.put("command", "voteFinished");
                        mp.put("result", res);
                        mp.put("voteType", lobby.getVoteType());
                        mp.put("playerVote", lobby.getPlayerVote());


                        sendToAllRoommates(voter, mp);
                    }


                    break;

            }
        }
    }

    public Lobby getLobby(miniPlayer player) {
        Lobby targetLobby = null;
        ArrayList<Lobby> lobbies = DataCenter.getLobbies();
        for (Lobby lobby1 : lobbies) {
            for (miniPlayer lobby1Player : lobby1.getPlayers()) {
                if (lobby1Player.getUsername().equals(player.getUsername())) {
                    targetLobby = lobby1;
                }
            }
        }
        return targetLobby;
    }

    public void sendToRoommates(miniPlayer sender, Map<String, Object> mp) {
        Lobby lobby = DataCenter.findLobbyByUser(sender);
        if (lobby == null) return;
        for (miniPlayer p : lobby.getPlayers()) {
            if (!p.getUsername().equals(sender.getUsername())) {
                Connection conn = DataCenter.getConnection(p);
                if (conn != null) {
                    conn.sendTCP(mp);
                }
            }
        }
    }

    public void sendToAllRoommates(miniPlayer sender, Map<String, Object> mp) {
        Lobby lobby = DataCenter.findLobbyByUser(sender);
        if (lobby == null) return;
        for (miniPlayer p : lobby.getPlayers()) {
            Connection conn = DataCenter.getConnection(p);
            if (conn != null) {
                conn.sendTCP(mp);
            }
        }
    }

    public void sendToPlayer(miniPlayer receiver, Map<String, Object> mp) {
        Connection conn = DataCenter.getConnection(receiver);
        if (conn != null) {
            conn.sendTCP(mp);
        }
    }
}