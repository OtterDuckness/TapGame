package com.mygame.tapgaem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameOverScreen implements Screen {

    private float bestTime;
    private int bestScore;

    private final TapGame game;

    private SpriteBatch batch;
    private BitmapFont font;

    // ===== RESULTS =====
    private int score;
    private float time;
    private int obstacles;

    public GameOverScreen(TapGame game, int score, float time, int obstacles) {
        this.game = game;
        this.score = score;
        this.time = time;
        this.obstacles = obstacles;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);
        Preferences prefs = Gdx.app.getPreferences("tapgame_prefs");


        bestTime = prefs.getFloat("best_time", 0f);
        bestScore = prefs.getInteger("best_score", 0);

// UPDATE LOGIC
        if (time > bestTime) {
            bestTime = time;
            prefs.putFloat("best_time", bestTime);
        }

        if (obstacles > bestScore) {
            bestScore = obstacles;
            prefs.putInteger("best_score", bestScore);
        }

        prefs.flush();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        font.draw(batch, "GAME OVER", centerX - 220, centerY + 300);

        font.draw(batch, "Score: " + score, centerX - 220, centerY + 180);
        font.draw(batch, "Time: " + (int) time, centerX - 220, centerY + 100);
        font.draw(batch, "Obstacles: " + obstacles, centerX - 220, centerY + 20);

        font.draw(batch, "Best Time: " + (int) bestTime, centerX - 220, centerY - 100);
        font.draw(batch, "Best Obstacles: " + bestScore, centerX - 220, centerY - 180);

        font.draw(batch, "Tap to Restart", centerX - 220, centerY - 320);

        batch.end();

        // ===== INPUT =====
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game)); // NEW instance (clean state)
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
