package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObject {

    private Picture picture;
    private Position position; //top left position
    private Direction direction;
    public static final int STEP_SIZE = 10; //number of pixels it moves per step

    public GameObject(Picture picture, Position position, Direction direction){

        this.picture=picture;
        this.position=position;
        this.direction=direction;
    }

    public GameObject(Picture picture, Position position){
        this.picture=picture;
        this.position=position;
    }

    public void translateTo(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        picture.translate(translateX,translateY);
    }

    public void chooseDirection(int probOfChange){

        Direction newDirection = direction;

        // Sometimes, we want to change Direction...
        if (Math.random() > ((double) probOfChange) / 10) {
            newDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];

            // but we do not want to go back (or away from player)
            while(newDirection.isOpposite(direction)) {
                newDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];
            }
        }
        this.direction=newDirection;
    }

    public boolean hitsBorder(){

        switch (direction) {
            case LEFT:
                if(picture.getX()<=STEP_SIZE)
                    return true;
                break;
            case UP:
                if(picture.getY()<=STEP_SIZE)
                    return true;
                break;
            case RIGHT:
                if((picture.getMaxX()+STEP_SIZE)>= Game.screenWidth)
                    return true;
                break;
            case DOWN:
                if((picture.getMaxY()+STEP_SIZE)>=Game.screenHeight)
                    return true;
                break;
            case UP_RIGHT:
                if(picture.getY()<STEP_SIZE || (picture.getMaxX()+STEP_SIZE)>=Game.screenWidth)
                    return true;
                break;
            case DOWN_RIGHT:
                if((picture.getMaxY()+STEP_SIZE)>=Game.screenHeight || (picture.getMaxX()+STEP_SIZE)>=Game.screenWidth)
                    return true;
                break;
            case DOWN_LEFT:
                if((picture.getMaxY()+STEP_SIZE)>=Game.screenHeight || picture.getX()<=STEP_SIZE)
                    return true;
                break;
            case UP_LEFT:
                if(picture.getY()<=STEP_SIZE || picture.getX()<=STEP_SIZE)
                    return true;
                break;
            default:
                return true;
        }
        return false;
    }

    public void moveUp(){
        getPosition().translatePosition(0,-STEP_SIZE);
        picture.translate(0,-STEP_SIZE);
    }
    public void moveUpRight(){
        getPosition().translatePosition(STEP_SIZE,-STEP_SIZE);
        picture.translate(STEP_SIZE,-STEP_SIZE);
    }
    public void moveRight(){
        getPosition().translatePosition(STEP_SIZE,0);
        picture.translate(STEP_SIZE,0);
    }
    public void moveDownRight(){
        getPosition().translatePosition(STEP_SIZE,STEP_SIZE);
        picture.translate(STEP_SIZE,STEP_SIZE);
    }
    public void moveDown(){
        getPosition().translatePosition(0,STEP_SIZE);
        picture.translate(0,STEP_SIZE);
    }
    public void moveDownLeft(){
        getPosition().translatePosition(-STEP_SIZE,STEP_SIZE);
        picture.translate(-STEP_SIZE,STEP_SIZE);
    }
    public void moveLeft(){
        getPosition().translatePosition(-STEP_SIZE,0);
        picture.translate(-STEP_SIZE,0);
    }
    public void moveUpLeft(){
        getPosition().translatePosition(-STEP_SIZE,-STEP_SIZE);
        picture.translate(-STEP_SIZE,-STEP_SIZE);
    }

    public void move(Position ballPosition){

        switch (getDirection()){
            case UP:
                moveUp();
                break;
            case UP_RIGHT:
                moveUpRight();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN_RIGHT:
                moveDownRight();
                break;
            case DOWN:
                moveDown();
                break;
            case DOWN_LEFT:
                moveDownLeft();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP_LEFT:
                moveUpLeft();
                break;
        }
    }

    public void draw(){
        picture.draw();
    }
    public void setDirection(Direction direction){
        this.direction=direction;
    }
    public Direction getDirection(){
        return direction;
    }
    public Position getPosition(){return position;}
}
