package io.github.JellyBrew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.JellyBrew.screens.TitleScreen;

public class YourGame extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new TitleScreen());
    }

    @Override
    public void render() {
        super.render(); // Important: delegate to current screen
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}