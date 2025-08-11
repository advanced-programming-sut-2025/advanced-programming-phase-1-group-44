package main;

import com.badlogic.gdx.ApplicationAdapter;

import java.io.IOException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    KryoServer kryoServer;


    public Main() throws IOException {
        kryoServer = new KryoServer();
    }
}
