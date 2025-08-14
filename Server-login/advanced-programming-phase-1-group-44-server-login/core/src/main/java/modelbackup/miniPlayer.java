package modelbackup;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class miniPlayer {
    public String username, password, nickname, email, gender;
    public int tokenVersion = 0;

    public boolean loggedIn = false;

    public miniPlayer() {};
    public miniPlayer (String username, String password, String email, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;

    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!", e);
        }
    }

    public void incrementToken() {
        tokenVersion++;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
