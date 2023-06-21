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
    private int probOfChange = 5; //0-10 where 10 it never changes direction and 0 it changes every time

    //CONSTRUCTOR
    public Monstar(Picture picture, StartingPositions position, Direction direction){
        super(picture, position.getPosition(), direction);
        speed=1;
        size= Game.CELL_SIZE;
        this.currentSteps=0;
    }

    //Chooses random Direction for monstars
    public void chooseDirection(){

        Direction newDirection = getDirection();

        // Sometimes, we want to change Direction...
        if (Math.random() > ((double) probOfChange) / 10) {
            newDirection = Direction.values()[(int) (Math.random() * (Direction.values().length-1))];

            // but we do not want to go back (or away from player)
            while(newDirection.isOpposite(getDirection())) {
                newDirection = Direction.values()[(int) (Math.random() * (Direction.values().length-1))];
            }
        }
        setDirection(newDirection);
    }

    //GETTERS & SETTERS
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
