package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObject {

    private Picture picture;
    private Position position; //top left position
    private Position maxPosition; //bottom right position
    private Direction direction;
    public static final int STEP_SIZE = 10; //number of pixels it moves per step

    public GameObject(Position position, Picture picture, Direction direction){

        this.position=position;
        this.maxPosition=new Position(position.getX()+picture.getMaxX(),position.getY()+picture.getMaxY());
        this.picture=picture;
        this.direction=direction;
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



    public void translateTo(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        picture.translate(translateX,translateY);
    }

    public void draw(){
        picture.draw();
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

    private void moveUp(){
        position.translatePosition(0,-STEP_SIZE);
        picture.translate(0,-STEP_SIZE);
    }
    private void moveUpRight(){
        position.translatePosition(STEP_SIZE,-STEP_SIZE);
        picture.translate(STEP_SIZE,-STEP_SIZE);
    }
    private void moveRight(){
        position.translatePosition(STEP_SIZE,0);
        picture.translate(STEP_SIZE,0);
    }
    private void moveDownRight(){
        position.translatePosition(STEP_SIZE,STEP_SIZE);
        picture.translate(STEP_SIZE,STEP_SIZE);
    }
    private void moveDown(){
        position.translatePosition(0,STEP_SIZE);
        picture.translate(0,STEP_SIZE);
    }
    private void moveDownLeft(){
        position.translatePosition(-STEP_SIZE,STEP_SIZE);
        picture.translate(-STEP_SIZE,STEP_SIZE);
    }
    private void moveLeft(){
        position.translatePosition(-STEP_SIZE,0);
        picture.translate(-STEP_SIZE,0);
    }
    private void moveUpLeft(){
        position.translatePosition(-STEP_SIZE,-STEP_SIZE);
        picture.translate(-STEP_SIZE,-STEP_SIZE);
    }

    public void move(Position ballPosition){

        switch (direction){
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

    public Direction getDirection(){
        return direction;
    }
}
