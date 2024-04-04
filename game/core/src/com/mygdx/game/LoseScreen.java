package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.audio.Music;

public class LoseScreen implements Screen {

    final OraclesOdyssey game;
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    private Sprite loseSprite = new Sprite(new Texture(Gdx.files.internal("loseBackground.png")));
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("Music/womp-womp.mp3"));
    OrthographicCamera camera;
    BitmapFont scoreFont;

    public LoseScreen(final OraclesOdyssey game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, width, height);
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        sound.play(1.0f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(loseSprite, 0, 0, width, height);

        GlyphLayout waveLayout = new GlyphLayout(scoreFont,"Wave(s) Survived: " + (GameScreen.wave - 1));
        GlyphLayout endingLayout = new GlyphLayout(scoreFont,"You have been shot down...");
        scoreFont.draw(game.batch, waveLayout, width/ 2 - waveLayout.width / 2, height - waveLayout.height - 300);
        scoreFont.draw(game.batch, endingLayout, width/ 2 - endingLayout.width / 2, height - endingLayout.height - 10);
        scoreFont.getData().setScale(2);
        game.batch.end();


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
    public void dispose()
    {
        sound.dispose();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

