package main;

import com.esotericsoftware.kryo.Kryo;
import model.Lobby;
import model.Result;
import model.miniPlayer;

import java.util.ArrayList;

public class NetworkRegistry {
    public static void registerClasses(Kryo kryo) {
        kryo.register(Result.class);
        kryo.register(miniPlayer.class);
        kryo.register(ArrayList.class);
        kryo.register(java.util.Map.class);
        kryo.register(java.util.HashMap.class);
        kryo.register(String.class);
        kryo.register(Lobby.class);
    }
}
