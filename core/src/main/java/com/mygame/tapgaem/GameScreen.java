package com.mygame.tapgaem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;

public class GameScreen implements Screen {

    private Sound popSound;

    private ShapeRenderer shapeRenderer;

    // ===== RIPPLE SYSTEM =====
    private Array<Ripple> ripples;

    private SpriteBatch batch;

    private final TapGame game;

    // ===== OBSTACLES =====
    private int obstaclesAvoided = 0;

    // ===== INVULNERABILITY =====
    private boolean isInvulnerable = false;
    private float invulnerableTimer = 0f;
    private float invulnerableDuration = 3f;

    // ===== SPAWN CHANCES =====
    private float obstacleChance = 0.2f;   // 20%
    private float powerUpChance = 0.05f;   // 5%

    // ===== SCORE =====
    private int score = 0;

    // ===== TIMER =====
    private float gameTime = 0f;

    // ===== DIFFICULTY =====
    private float minSpawnInterval = 0.3f;   // cap (don’t go faster than this)
    private float difficultySpeed = 0.02f;   // how fast it scales

    // ===== UI =====
    private BitmapFont font;

    // Storage
    private Array<Circle> circles;

    // Spawn system
    private float spawnTimer;
    private float spawnInterval = 1.0f; // seconds

    // Screen size
    private int screenWidth;
    private int screenHeight;

    public GameScreen(TapGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        circles = new Array<>();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);

        shapeRenderer = new ShapeRenderer();
        ripples = new Array<>();

        // SOUND
        popSound = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));
    }

    @Override
    public void render(float delta) {

        // ===== CLEAR (WHITE) =====
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // ===== UPDATE =====
        update(delta);

        // ===== ENABLE ALPHA =====
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // ===== DRAW FILLED CIRCLES =====
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Circle c : circles) {

            if (c.type == Circle.Type.OBSTACLE) {
                shapeRenderer.setColor(0, 0, 0, 1); // black
            }
            else if (c.type == Circle.Type.POWER_UP) {
                shapeRenderer.setColor(0.7f, 0f, 1f, 1); // PURPLE (visible on white)
            }
            else {
                shapeRenderer.setColor(getDifficultyColor());
            }

            shapeRenderer.circle(c.x, c.y, c.radius);
        }

        shapeRenderer.end();


        // ===== DRAW RIPPLE (FILLED) =====
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Ripple r : ripples) {

            r.time += delta;

            if (!r.isActive()) continue;

            float localTime = r.time - r.delay;
            float progress = localTime / r.duration;

            float radius = 140 + progress * 460;
            float alpha = 1 - (progress * 0.8f);

            shapeRenderer.setColor(
                r.color.r,
                r.color.g,
                r.color.b,
                alpha
            );

            shapeRenderer.circle(r.x, r.y, radius);
        }

        shapeRenderer.end();


        // ===== DRAW OUTLINES (ON TOP) =====
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (Circle c : circles) {
            shapeRenderer.setColor(0, 0, 0, 1); // black outline
            shapeRenderer.circle(c.x, c.y, c.radius);
        }

        shapeRenderer.end();


        // ===== UI =====
        batch.begin();

        font.draw(batch, "Score: " + score, 40, screenHeight - 40);
        font.draw(batch, "Time: " + (int) gameTime, 40, screenHeight - 120);
        font.draw(batch, "Obstacles: " + obstaclesAvoided, 40, screenHeight - 200);

        if (isInvulnerable) {
            font.draw(batch, "INVULNERABLE", 40, screenHeight - 280);
        }
        batch.end();
    }

    private void update(float delta) {

        // ===== TIMER =====
        gameTime += delta;

        // ===== INVULNERABILITY =====
        if (isInvulnerable) {
            invulnerableTimer -= delta;
            if (invulnerableTimer <= 0) {
                isInvulnerable = false;
            }
        }

        // ===== DIFFICULTY =====
        spawnInterval -= difficultySpeed * delta;
        if (spawnInterval < minSpawnInterval) {
            spawnInterval = minSpawnInterval;
        }

        // ===== SPAWN =====
        spawnTimer += delta;

        if (spawnTimer >= spawnInterval) {
            spawnCircle();
            spawnTimer = 0;
        }

        // ===== UPDATE CIRCLES =====
        for (int i = circles.size - 1; i >= 0; i--) {
            Circle c = circles.get(i);
            c.lifetime += delta;

            if (c.isExpired()) {

                if (c.type == Circle.Type.OBSTACLE) {
                    obstaclesAvoided++;
                }

                circles.removeIndex(i);
            }
        }

        // ===== UPDATE RIPPLES =====
        for (int i = ripples.size - 1; i >= 0; i--) {
            Ripple r = ripples.get(i);

            if (r.isFinished()) {
                ripples.removeIndex(i);
            }
        }

        // ===== INPUT =====
        handleInput();
    }

    private void spawnCircle() {

        float radius = screenWidth * 0.08f;

        float x = MathUtils.random(radius, screenWidth - radius);
        float y = MathUtils.random(radius, screenHeight - radius);

        float maxLifetime = 2.0f;

        float rand = MathUtils.random();

        Circle.Type type;

        if (rand < powerUpChance) {
            type = Circle.Type.POWER_UP;
        } else if (rand < powerUpChance + obstacleChance) {
            type = Circle.Type.OBSTACLE;
        } else {
            type = Circle.Type.NORMAL;
        }

        circles.add(new Circle(x, y, radius, maxLifetime, type));
    }

    private void handleInput() {

        if (Gdx.input.justTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            touchY = screenHeight - touchY;

            for (int i = circles.size - 1; i >= 0; i--) {
                Circle c = circles.get(i);

                if (isTouchInsideCircle(touchX, touchY, c)) {

                    Color rippleColor;

                    // NORMAL
                    if (c.type == Circle.Type.NORMAL) {
                        score++;
                        rippleColor = getDifficultyColor();
                    }

                    // OBSTACLE
                    else if (c.type == Circle.Type.OBSTACLE) {
                        rippleColor = Color.BLACK;

                        if (!isInvulnerable) {
                            game.setScreen(new GameOverScreen(
                                game,
                                score,
                                gameTime,
                                obstaclesAvoided
                            ));
                            return;
                        }
                    }

                    // POWER-UP
                    else {
                        rippleColor = Color.WHITE;
                        isInvulnerable = true;
                        invulnerableTimer = invulnerableDuration;
                    }

                    // ===== MULTI RIPPLE BURST =====
                    for (int j = 0; j < 4; j++) {
                        ripples.add(new Ripple(
                            c.x,
                            c.y,
                            j * 0.12f,   // delay between waves
                            rippleColor
                        ));
                    }

                    popSound.play(0.7f); // volume (0.0 - 1.0)

                    circles.removeIndex(i);
                    break;
                }
            }
        }
    }

    private static class Ripple {
        float x, y;
        float time;
        float duration;
        float delay;
        Color color;

        Ripple(float x, float y, float delay, Color color) {
            this.x = x;
            this.y = y;
            this.delay = delay;
            this.time = 0;
            this.duration = 0.8f;
            this.color = new Color(color);
        }

        boolean isFinished() {
            return time >= duration + delay;
        }

        boolean isActive() {
            return time >= delay;
        }
    }


    private boolean isTouchInsideCircle(float tx, float ty, Circle c) {

        float dx = tx - c.x;
        float dy = ty - c.y;

        float distanceSquared = dx * dx + dy * dy;

        return distanceSquared <= c.radius * c.radius;
    }

    private Color getDifficultyColor() {
        float t = Math.min(gameTime / 30f, 1f); // 30 sec → full red

        if (t < 0.25f) return Color.BLUE.cpy().lerp(Color.GREEN, t / 0.25f);
        if (t < 0.5f) return Color.GREEN.cpy().lerp(Color.YELLOW, (t - 0.25f) / 0.25f);
        if (t < 0.75f) return Color.YELLOW.cpy().lerp(Color.ORANGE, (t - 0.5f) / 0.25f);
        return Color.ORANGE.cpy().lerp(Color.RED, (t - 0.75f) / 0.25f);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
        popSound.dispose();
    }

}
