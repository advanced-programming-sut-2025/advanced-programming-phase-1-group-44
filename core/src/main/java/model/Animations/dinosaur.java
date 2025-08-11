package model.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class dinosaur {
    private static final int FRAME_WIDTH = 16;
    private static final int FRAME_HEIGHT = 16;
    private static final float FRAME_DURATION = 0.15f;

    public static Animation<TextureRegion>[] loadAnimations() {
        Texture spriteSheet = new Texture(Gdx.files.internal("sprites/dinosaur.png"));
        TextureRegion[][] frames = TextureRegion.split(spriteSheet, FRAME_WIDTH, FRAME_HEIGHT);


        Animation<TextureRegion>[] animations = new Animation[6];
        animations[0] = new Animation<>(FRAME_DURATION, frames[0]); // walkDown
        animations[1] = new Animation<>(FRAME_DURATION, frames[1]); // walkRight
        animations[2] = new Animation<>(FRAME_DURATION, frames[2]); // walkUp
        animations[3] = new Animation<>(FRAME_DURATION, frames[3]); // walkLeft
        animations[4] = new Animation<>(FRAME_DURATION, frames[4][0]);            // idle
        animations[5] = new Animation<>(FRAME_DURATION, frames[6]);            // pet/peck

        return animations;
    }
}

