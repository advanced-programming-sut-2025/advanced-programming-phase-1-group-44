package model.Animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class pig {
    private static final int FRAME_WIDTH = 32;
    private static final int FRAME_HEIGHT = 32;
    private static final float FRAME_DURATION = 0.15f;

    public static Animation<TextureRegion>[] loadAnimations() {
        Texture spriteSheet = new Texture(Gdx.files.internal("sprites/pig.png"));
        TextureRegion[][] frames = TextureRegion.split(spriteSheet, FRAME_WIDTH, FRAME_HEIGHT);

        Animation<TextureRegion>[] animations = new Animation[6];

        // Walk animations
        animations[0] = new Animation<>(FRAME_DURATION, frames[0]); // walkDown
        animations[1] = new Animation<>(FRAME_DURATION, frames[1]); // walkRight

        // Create walkLeft by flipping walkRight frames
        TextureRegion[] rightFrames = frames[1];
        TextureRegion[] flippedLeftFrames = new TextureRegion[rightFrames.length];
        for (int i = 0; i < rightFrames.length; i++) {
            flippedLeftFrames[i] = new TextureRegion(rightFrames[i]);
            flippedLeftFrames[i].flip(true, false); // Flip horizontally
        }
        animations[3] = new Animation<>(FRAME_DURATION, frames[2]);         // walkUp
        animations[2] = new Animation<>(FRAME_DURATION, flippedLeftFrames); // walkLeft (mirrored)

        // Idle: single frame
        animations[4] = new Animation<>(FRAME_DURATION, frames[3][0]);      // idle

        // Peck or pet animation
        animations[5] = new Animation<>(FRAME_DURATION, frames[4]);         // peck/pet

        return animations;
    }
}

