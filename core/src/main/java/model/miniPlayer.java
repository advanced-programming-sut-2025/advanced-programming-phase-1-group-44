package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class miniPlayer {
    public String username, password, nickname, email, gender;
    public int tokenVersion = 0;

    public boolean loggedIn = false;

    private int money, questDone, abilityPower;

    public int getTotalAbilityPower() {
        return abilityPower;
    }

    public int getMoney() {
        return money;
    }

    public int getQuestsDone() {
        return questDone;
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTokenVersion(int tokenVersion) {
        this.tokenVersion = tokenVersion;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    @Override
    public String toString() {
        return username; // This will make the SelectBox show usernames
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof miniPlayer)) return false;
        miniPlayer other = (miniPlayer) obj;
        return username != null && username.equals(other.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }


}
