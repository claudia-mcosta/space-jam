package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar extends Character {

    private int speed;
    private int size;
    public static int MAX_STEPS=5;
    private int currentSteps;

    public Monstar(Picture picture, StartingPositions position, Direction direction){
        super(picture, position.getPosition(), direction);
        speed=1;
        size= Game.CELL_SIZE;
        this.currentSteps=0;
    }

    public int getCurrentSteps(){
        return currentSteps;
    }
    public void takeAStep(){
        currentSteps++;
    }
    public void resetCurrentSteps(){
        currentSteps=0;
    }


}
