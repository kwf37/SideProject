package com.project.src;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by Kenneth on 7/28/2017.
 */
public class MC extends Entity{

    public BodyDef getBodyDef(){
        BodyDef body= new BodyDef();
        return body;
    }
}

