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
        super(picture, position, direction);
        speed=1;
        size= Game.CELL_SIZE;
        this.currentSteps=0;
    }

    /*public boolean playerHit(){

        return false;
    }*/

    //Find ball and hoop
    /*public Direction findObjectOppositeDirection(GameObject object){

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
    }*/


    /*public Direction findObjectDirection(GameObject object){
        if(this.getMaxPosition().getX()<object.getPosition().getX())
            return Direction.RIGHT;
        else if(this.getPosition().getX()>object.getMaxPosition().getX())
            return Direction.LEFT;
        else
            return Direction.LEFT;
        //if(this.getMaxPosition().getY()<object.getPosition().getY())
            //return Direction.DOWN;
        // if(this.getPosition().getY()>object.getMaxPosition().getY())
        //            return Direction.UP;
    }*/

    /*public void chooseObjectDirection(boolean ball, Ball ballObject, HoopPosition hoopObject){

        Direction newDirection = this.getDirection();

        if(!ball) {
            switch (this.findObjectDirection(ballObject)) {
                case LEFT:
                    switch ((int)(Math.random()*3)){
                        case 0:
                            newDirection=Direction.UP_LEFT;
                            break;
                        case 1:
                            newDirection=Direction.LEFT;
                            break;
                        case 2:
                            newDirection=Direction.DOWN_LEFT;
                        default:
                            newDirection=Direction.values()[(int) (Math.random() * (Direction.values().length-1))];
                    }
                    break;
                case RIGHT:
                    switch ((int)(Math.random()*3)){
                        case 0:
                            newDirection=Direction.UP_RIGHT;
                            break;
                        case 1:
                            newDirection=Direction.RIGHT;
                            break;
                        case 2:
                            newDirection=Direction.DOWN_RIGHT;
                            break;
                        default:
                            newDirection=Direction.values()[(int) (Math.random() * (Direction.values().length-1))];
                    }
                    break;
                default:
                    newDirection=Direction.values()[(int) (Math.random() * (Direction.values().length-1))];
            }
        }else{
            switch ((int)(Math.random()*3)){
                case 0:
                    newDirection=Direction.UP_LEFT;
                    break;
                case 1:
                    newDirection=Direction.LEFT;
                    break;
                case 2:
                    newDirection=Direction.DOWN_LEFT;
                default:
                    newDirection=Direction.values()[(int) (Math.random() * (Direction.values().length-1))];
            }
        }

        this.setDirection(newDirection);
    }*/


    //Chooses random Direction for monstars
    public void chooseRandomDirection(boolean ball, Ball ballObject, Hoop hoopObject){

        Direction newDirection = getDirection();

        // Sometimes, we want to change Direction...
        if (Math.random() > ((double) probOfChange) / 10) {

            newDirection = Direction.values()[(int) (Math.random() * (Direction.values().length-1))];

            // but we do not want to go back (or away from player)
            while(newDirection.isOpposite(getDirection())) {
                newDirection = Direction.values()[(int) (Math.random() * (Direction.values().length-1))];
            }
        }
        this.setDirection(newDirection);
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
