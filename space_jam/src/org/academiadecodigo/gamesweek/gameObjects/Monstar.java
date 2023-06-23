package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.gamesweek.shootout.Hoop;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar extends Character {

    private int speed;
    private int size;
    public static int MAX_STEPS=5;
    private int currentSteps;
    private int probOfChange = 5; //0-10 where 10 it never changes direction and 0 it changes every time

    //CONSTRUCTOR
    public Monstar(Picture picture, StartingPositions position, Direction direction){
        super(picture, position, direction);
        speed=1;
        size= Game.CELL_SIZE;
        this.currentSteps=0;
    }

    public boolean playerHit(){

        return false;
    }

    //Find ball and hoop
    public Direction findObjectOppositeDirection(GameObject object){

        //Returns the OPPOSITE direction that the object is
        if(this.getPosition().getX()>object.getPosition().getX() && this.getPosition().getY()>object.getMaxPosition().getY() && this.getMaxPosition().getY()<object.getPosition().getY()){
            return Direction.RIGHT;
        } else if (this.getPosition().getY()>object.getMaxPosition().getY() && this.getPosition().getX()<object.getMaxPosition().getX()) {
            return Direction.DOWN_RIGHT;
        } else if (this.getPosition().getX()<object.getMaxPosition().getX() && this.getPosition().getY()>object.getMaxPosition().getY() && this.getMaxPosition().getX()<object.getPosition().getX()) {
            return Direction.DOWN;
        }else if(this.getMaxPosition().getX()<object.getPosition().getX() && this.getPosition().getY()>object.getMaxPosition().getY()){
            return Direction.DOWN_LEFT;
        } else if (this.getPosition().getY()<object.getMaxPosition().getY() && this.getMaxPosition().getY()>object.getPosition().getY() && this.getMaxPosition().getX()<object.getPosition().getX()) {
            return Direction.LEFT;
        } else if (this.getMaxPosition().getY()<object.getPosition().getY() && this.getMaxPosition().getX()<object.getPosition().getX()) {
            return Direction.UP_LEFT;
        } else if (this.getMaxPosition().getX() > object.getPosition().getX() && this.getPosition().getX() < object.getMaxPosition().getX() && this.getPosition().getY()<object.getPosition().getY()) {
            return Direction.UP;
        } else if (this.getPosition().getX()>object.getMaxPosition().getX() && this.getMaxPosition().getY()<object.getPosition().getY()) {
            return Direction.UP_RIGHT;
        }else
            return Direction.NONE;
    }

    //Chooses random Direction for monstars
    public void chooseDirection(boolean ball, Ball ballObject, HoopPosition hoopObject){

        Direction newDirection = getDirection();

        // Sometimes, we want to change Direction...
        if (Math.random() > ((double) probOfChange) / 10) {

            Direction oppositeDirection = Direction.RIGHT;

            /*if(!ball){
                oppositeDirection = findObjectOppositeDirection(ballObject);
            }else{
                oppositeDirection = findObjectOppositeDirection(hoopObject);
            }
            newDirection = oppositeDirection.oppositeDirection()[(int) (Math.random() * 3)];*/

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
