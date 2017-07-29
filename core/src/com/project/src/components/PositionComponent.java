package com.project.src.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Kenneth on 7/22/2017.
 */
public class PositionComponent implements Component{
        public float x=0;
        public float y=0;

        public PositionComponent(float x, float y){
            this.x=x;
            this.y=y;
        }

        public float getX(){
            return this.x;
        }

        public float getY(){
            return this.y;
        }

        public void setPosition(float x,float y){
            this.x=x;
            this.y=y;
        }
}
