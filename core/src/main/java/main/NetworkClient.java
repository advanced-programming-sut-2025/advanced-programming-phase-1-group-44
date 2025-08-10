package main;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import controller.ProfileMenuController;
import controller.SignupMenuController;
import model.App;
import model.Player;
import model.Result;
import model.miniPlayer;

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
                    }
                }
            }
        });

        // Connect to server
        try {
            System.out.println("Attempting to connect to localhost:1010...");
            client.connect(5000, "localhost", 1010, 1100);
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
}


