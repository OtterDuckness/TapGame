package com.mygame.tapgaem;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Preferences;

public class MainMenuScreen implements Screen {

    private final TapGame game;

    private float bestTime;
    private int bestScore;

    private SpriteBatch batch;
    private BitmapFont font;

    public MainMenuScreen(TapGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);

        Preferences prefs = Gdx.app.getPreferences("tapgame_prefs");

        bestTime = prefs.getFloat("best_time", 0f);
        bestScore = prefs.getInteger("best_score", 0);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        GlyphLayout layout = new GlyphLayout();

        batch.begin();

        // ===== TITLE =====
        String title = "TAP TO START";
        layout.setText(font, title);

        font.draw(
            batch,
            title,
            (screenWidth - layout.width) / 2f,
            screenHeight * 0.60f
        );

        // ===== BEST TIME =====
        String bestTimeText = "Best Time: " + (int) bestTime;
        layout.setText(font, bestTimeText);

        font.draw(
            batch,
            bestTimeText,
            (screenWidth - layout.width) / 2f,
            screenHeight * 0.55f
        );

        // ===== BEST SCORE =====
        String bestScoreText = "Best Obstacles: " + bestScore;
        layout.setText(font, bestScoreText);

        font.draw(
            batch,
            bestScoreText,
            (screenWidth - layout.width) / 2f,
            screenHeight * 0.45f
        );

        batch.end();

        // ===== INPUT =====
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
