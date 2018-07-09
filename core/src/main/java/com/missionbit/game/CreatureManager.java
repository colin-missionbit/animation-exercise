package com.missionbit.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class CreatureManager {
    //Creatures we're actively drawing
    private ArrayList<FlyingCreature> activeCreatures = new ArrayList<FlyingCreature>();

    private ArrayList<FlyingCreature> pool = new ArrayList<FlyingCreature>();

    private ArrayList<FlyingCreature> removed = new ArrayList<FlyingCreature>();


    private SpriteBatch batch;

    public CreatureManager(){
        batch = new SpriteBatch();
    }

    public FlyingCreature spawnCreature(){
        FlyingCreature f;
        if(pool.isEmpty()){
            f = new FlyingCreature(batch);
            activeCreatures.add(f);
            System.out.println("New");
        }
        else{
            f = pool.remove(0);
            f.reset();
            activeCreatures.add(f);
            System.out.println("From pool");
        }
        return f;
    }

    public void update(){
        for(FlyingCreature c : activeCreatures){
            c.update();
            if(!c.isActive()) {
                removed.add(c);
            }
        }
        activeCreatures.removeAll(removed);
        pool.addAll(removed);
        removed.clear();

    }

    public void draw(Camera camera){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(FlyingCreature c : activeCreatures){
            c.draw();
        }
        batch.end();

    }

    public void handleClick(Vector3 pos){
        for(FlyingCreature c : activeCreatures){
            c.handleClick(pos);
        }
    }



}
