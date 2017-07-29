package com.project.src.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Kenneth on 7/28/2017.
 */
public class HealthComponent implements Component{
    private float hp;
    private float maxHp;

    public HealthComponent(float maxHp){
        this.hp=maxHp;
        this.maxHp=maxHp;
    }

    public float getHp(){
        return hp;
    }

    public float getMaxHp(){
        return maxHp;
    }

    public void setHp(float newHp){
        this.hp=newHp;
    }
}
