package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar extends Player{

    private int speed;
    private int size;
    public static int MAX_STEPS=5;
    private int currentSteps;

    public Monstar(StartingPositions position, Picture picture, Direction direction){
        super(position.getPosition(), picture, direction);
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
