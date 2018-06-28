package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class FlyingCreature {
    private Sprite myImage;
    private Vector2 velocity;

    private SpriteBatch myBatch;

    private ParticleEffect effect;

    private boolean alive = true;

    public FlyingCreature(SpriteBatch batch){
        myBatch = batch;
        myImage = new Sprite( new Texture(Gdx.files.internal("images/ufo.png")));

        velocity = new Vector2(MathUtils.random() * 300, MathUtils.random() * 300);

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/explode.p"), Gdx.files.internal("images"));
    }

    public void update(){
        float xPos = myImage.getX() + velocity.x * Gdx.graphics.getDeltaTime();
        float yPos = myImage.getY() + velocity.y * Gdx.graphics.getDeltaTime();

        myImage.setX(xPos);
        myImage.setY(yPos);

        if(myImage.getX() < 0){
            myImage.setX(0);
            velocity.x *= -1;
        }
        if(myImage.getX() + myImage.getWidth() > Gdx.graphics.getWidth()){
            myImage.setX(Gdx.graphics.getWidth() - myImage.getWidth());
            velocity.x *= -1;
        }

        if(myImage.getY() < 0){
            myImage.setY(0);
            velocity.y *= -1;
        }
        if(myImage.getY() + myImage.getHeight() > Gdx.graphics.getHeight()){
            myImage.setY(Gdx.graphics.getHeight() - myImage.getHeight());
            velocity.y *= -1;
        }
    }


    public void draw(){
        if(alive) {
            myImage.draw(myBatch);
        }
        else{
            effect.draw(myBatch, Gdx.graphics.getDeltaTime());
        }
    }

    //This handles my mouse clicks!
    public boolean handleClick(Vector3 touchPos){
        boolean hit = myImage.getBoundingRectangle().contains(touchPos.x, touchPos.y);
        if(hit){
            if(alive){
                // Calculate the center of our sprite by adding half of the width to the x coordinate
                // and half of the height to the y coordinate
                // then move our effect there
                effect.setPosition(myImage.getX() + myImage.getWidth() / 2.0f, myImage.getY() + myImage.getHeight() / 2.0f);

                // Start our effect
                effect.start();
            }
            alive = false;
        }
        return hit;
    }
}
