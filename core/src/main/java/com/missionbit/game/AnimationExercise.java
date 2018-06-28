package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

public class AnimationExercise extends ApplicationAdapter {
    private OrthographicCamera camera;

    private SpriteBatch myBatch;

    //FlyingCreature creature;
    private ArrayList<FlyingCreature> creatures = new ArrayList<FlyingCreature>();



    @Override
    public void create() {

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        //creature = new FlyingCreature(myBatch);
        // Load our image
        /*myImage = new Sprite( new Texture(Gdx.files.internal("images/ufo.png")));
        myImage.setX(200);
        myImage.setY(200);*/


        // Create a random X and Y velocity
        //velocity = new Vector2(-randomSource.nextFloat() * 300, randomSource.nextFloat() * 300);
        for(int i = 0; i < 10; i++){
            FlyingCreature f = new FlyingCreature(myBatch);
            creatures.add(f);
        }
    }

    @Override
    public void render() {

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);


        /*float xPos = myImage.getX() + velocity.x * Gdx.graphics.getDeltaTime();
        float yPos = myImage.getY() + velocity.y * Gdx.graphics.getDeltaTime();

        myImage.setX(xPos);
        myImage.setY(yPos);

        if(myImage.getX() < 0){
            myImage.setX(0);
            velocity.x *= -1;
        }

        myImage.setColor(1, 0, 0, 1);*/

        //creature.update();
        myBatch.begin();
       // myImage.draw(myBatch);
        //creature.draw();

        for(FlyingCreature f : creatures){
            f.update();
            f.draw();
        }

        myBatch.end();

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);

            for(FlyingCreature f : creatures){
                f.handleClick(touchPos);
            }
        }

    }

    @Override
    public void dispose() {
        myBatch.dispose();
    }
}