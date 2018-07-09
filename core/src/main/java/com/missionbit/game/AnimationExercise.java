package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.math.Vector3;


public class AnimationExercise extends ApplicationAdapter {
    private OrthographicCamera camera;


    private FPSLogger fpsLog = new FPSLogger();
    private CreatureManager manager;

    long lastSpawn;

    @Override
    public void create() {

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        manager =  new CreatureManager();

        for(int i = 0; i < 10; i++){
            manager.spawnCreature();
        }
        lastSpawn = System.currentTimeMillis();

    }

    @Override
    public void render() {
        //fpsLog.log();

        if(System.currentTimeMillis() - lastSpawn > 500) {
            manager.spawnCreature();
            lastSpawn = System.currentTimeMillis();
        }

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();

        manager.update();
        manager.draw(camera);
        // System.out.println("Length " + creatures.size());

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);
            //System.out.println(touchPos.x + " " + touchPos.y);
            manager.handleClick(touchPos);


        }


    }

    @Override
    public void dispose() {
    }

}


