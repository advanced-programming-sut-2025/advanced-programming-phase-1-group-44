package main;

import com.badlogic.gdx.ApplicationAdapter;

import java.io.IOException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class tmp extends ApplicationAdapter {
    NetworkClient kryoSimpleClient;


    public tmp() throws IOException {
        kryoSimpleClient = new NetworkClient();

        kryoSimpleClient.start();
    }


}
