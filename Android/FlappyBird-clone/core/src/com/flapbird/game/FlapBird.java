package com.flapbird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import sun.rmi.runtime.Log;

public class FlapBird extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture[] birds;
    private Texture background;
    private Texture high_pipe;
    private Texture low_pipe;
    private Circle birdCircle;
    private Rectangle highPipeRectangle;
    private Rectangle lowPipeRectangle;
    private ShapeRenderer shapeRenderer;
    private Texture gameOver;


    //configs
    private Integer gameState = 0;
    private Random randNumber;
    private float deltaTime;
    private float width;
    private float height;
    private float variation = 0;
    private float fall_velocity = 0;
    private float birdVerticalInitialPosition = 0;
    private float pipeMovesHorizontal = 0;
    private float enterPipesSpace = 0;
    private float heightPipes = 0;
    private BitmapFont configFont;
    private BitmapFont message;

    private Integer score = 0;
    private boolean pointed = false;

    private OrthographicCamera camera;
    private Viewport viewport;
    private final float VIRTUAL_WIDTH = 768;
    private final float VIRTUAL_HEIGHT = 1024;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //load assets

        birds = new Texture[3];
        birds[0] = new Texture("passaro1.png");
        birds[1] = new Texture("passaro2.png");
        birds[2] = new Texture("passaro3.png");

        high_pipe = new Texture("cano_topo.png");
        low_pipe = new Texture("cano_baixo.png");

        background = new Texture("fundo.png");
        gameOver = new Texture("game_over.png");

        birdCircle = new Circle();
        highPipeRectangle = new Rectangle();
        lowPipeRectangle = new Rectangle();

        configFont = new BitmapFont();
        configFont.setColor(Color.BLACK.WHITE);
        configFont.getData().setScale(6);

        message = new BitmapFont();
        message.setColor(Color.WHITE);
        message.getData().setScale(3);

        //load configs
        randNumber = new Random();
        width = VIRTUAL_WIDTH;
        height = VIRTUAL_HEIGHT;

        birdVerticalInitialPosition = height / 2;
        pipeMovesHorizontal = width;
        enterPipesSpace = 300;

//        shapeRenderer = new ShapeRenderer();


        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2,0);
        viewport = new StretchViewport(VIRTUAL_WIDTH,VIRTUAL_WIDTH,camera);
    }

    @Override
    public void render() {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        deltaTime = Gdx.graphics.getDeltaTime();
        variation += deltaTime * 10;
        if (variation > 2) {
            variation = 0;
        }


        if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else {


            fall_velocity++;

            if (birdVerticalInitialPosition > 10 || fall_velocity < 0) {
                birdVerticalInitialPosition -= fall_velocity;
            } else {
                birdVerticalInitialPosition = 10;
            }

            if (gameState == 1) {
                pipeMovesHorizontal -= deltaTime * 400;

                if (Gdx.input.justTouched()) {
                    fall_velocity = -15;
                }

                if (pipeMovesHorizontal < -high_pipe.getWidth()) {
                    pipeMovesHorizontal = width;
                    heightPipes = randNumber.nextInt(400) - 200;
                    pointed = false;
                }
                if (pipeMovesHorizontal < 120) {
                    if (!pointed) {
                        score++;
                        pointed = true;
                    }
                }
            } else {//game over
                if (Gdx.input.isTouched()) {
                    gameState = 0;
                    score = 0;
                    fall_velocity = 0;
                    birdVerticalInitialPosition = height / 2;
                    pipeMovesHorizontal = width;
                }


            }

        }

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, width, height);
        batch.draw(high_pipe, pipeMovesHorizontal, height / 2 + enterPipesSpace / 2 + heightPipes);
        batch.draw(low_pipe, pipeMovesHorizontal, height / 2 - low_pipe.getHeight() - enterPipesSpace / 2 + heightPipes);
        batch.draw(birds[(int) variation], 120, birdVerticalInitialPosition);
        configFont.draw(batch, score.toString(), width / 2, height - 50);
        if (gameState == 2) {
            batch.draw(gameOver, width / 2 - gameOver.getWidth() / 2, height / 2);
            message.draw(batch, "Toque para Iniciar.", width / 2 - 205, height / 2 - gameOver.getHeight() / 2);

        }
        batch.end();

        birdCircle.set(120 + birds[(int) variation].getWidth() / 2, birdVerticalInitialPosition + birds[(int) variation].getHeight() / 2, birds[(int) variation].getWidth() / 2);

        lowPipeRectangle = new Rectangle(
                pipeMovesHorizontal,
                height / 2 - low_pipe.getHeight() - enterPipesSpace / 2 + heightPipes,
                low_pipe.getWidth(),
                low_pipe.getHeight()
        );

        highPipeRectangle = new Rectangle(
                pipeMovesHorizontal,
                height / 2 + enterPipesSpace / 2 + heightPipes,
                high_pipe.getWidth(),
                high_pipe.getHeight()
        );


//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);
//        shapeRenderer.rect(lowPipeRectangle.x,lowPipeRectangle.y,lowPipeRectangle.width,lowPipeRectangle.height);
//        shapeRenderer.rect(highPipeRectangle.x,highPipeRectangle.y,highPipeRectangle.width,highPipeRectangle.height);
//
//        shapeRenderer.end();

        if (Intersector.overlaps(birdCircle, highPipeRectangle)
                || Intersector.overlaps(birdCircle, lowPipeRectangle)
                || birdVerticalInitialPosition < 15
                || birdVerticalInitialPosition > height){
            Gdx.app.log("COLIDER", "COLIDING");
            gameState = 2;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height);
    }

    @Override
    public void dispose() {

    }
}
