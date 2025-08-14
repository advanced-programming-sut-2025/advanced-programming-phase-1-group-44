package main;

import com.auth0.jwt.interfaces.DecodedJWT;
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

                    Map<String, Object> mp = new HashMap<>();
                    mp.put("command", "getToken");
                    mp.put("token", newToken);

                    connection.sendTCP(mp);

                    DataCenter.connectPlayer(player);
                    break;
                case "logout":
                    username = (String) data.get("username");
                    player = DataCenter.getUserByUsername(username);
                    DataCenter.disconnectPlayer(player);

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

                    message = new HashMap<>();


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
                    //System.out.println("lobby request received!");
                    mp = new HashMap<>();
                    mp.put("lobbies" , DataCenter.getLobbies());
                    mp.put("command" , "lobbiesList");
                    connection.sendTCP(mp);
                    break;
                case "addPlayer":
                    lobby = DataCenter.getLobby((Lobby) data.get("lobby"));
                    lobby.addPlayer((miniPlayer) data.get("player"));
                    System.out.println("player " + ((miniPlayer) data.get("player")).getUsername() + " added to lobby " + lobby.getName());
                    break;
                case "getLobby":
                    player = (miniPlayer) data.get("player");
                    ArrayList<Lobby> lobbies = DataCenter.getLobbies();
                    Map<String, Object> mp2 = new HashMap<>();
                    Lobby debug = getLobby(player);
                    mp2.put("lobby", getLobby(player));
                    mp2.put("command" , "getLobby");
                    System.out.println("player " + player.getUsername() + " is in lobby " + debug.getName());
                    System.out.println("players : ");
                    for (miniPlayer debugPlayer : debug.getPlayers()) {
                        System.out.println(debugPlayer.username);
                    }
                    connection.sendTCP(mp2);
                    break;
                case "removePlayer":
                    player = (miniPlayer) data.get("player");
                    lobbies = DataCenter.getLobbies();
                    Lobby targetLobby = getLobby(player);
                    targetLobby.removePlayer(player);
                default:


                    break;
            }
        }
    }

    public Lobby getLobby(miniPlayer player){
        Lobby targetLobby = null;
        ArrayList<Lobby> lobbies = DataCenter.getLobbies();
        for (Lobby lobby1 : lobbies) {
            for (miniPlayer lobby1Player : lobby1.getPlayers()) {
                if(lobby1Player.getUsername().equals(player.getUsername())){
                    targetLobby = lobby1;
                }
            }
        }
        return targetLobby;
    }

}
