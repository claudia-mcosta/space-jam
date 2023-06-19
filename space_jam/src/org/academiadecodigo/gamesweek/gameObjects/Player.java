package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Player extends GameObject{

    private boolean hasBall=false;
    private Picture picture;

    public Player(Position position, Picture picture, Direction direction) {
        super(position, new Position(position.getX()+picture.getMaxX(),position.getY()+picture.getMaxY()), direction);

        this.picture=picture;
    }

    public void translateTo(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        picture.translate(translateX,translateY);
    }

    public boolean hitsBorder(){

        switch (getDirection()) {
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
        getPosition().translatePosition(0,-STEP_SIZE);
        picture.translate(0,-STEP_SIZE);
    }
    private void moveUpRight(){
        getPosition().translatePosition(STEP_SIZE,-STEP_SIZE);
        picture.translate(STEP_SIZE,-STEP_SIZE);
    }
    private void moveRight(){
        getPosition().translatePosition(STEP_SIZE,0);
        picture.translate(STEP_SIZE,0);
    }
    private void moveDownRight(){
        getPosition().translatePosition(STEP_SIZE,STEP_SIZE);
        picture.translate(STEP_SIZE,STEP_SIZE);
    }
    private void moveDown(){
        getPosition().translatePosition(0,STEP_SIZE);
        picture.translate(0,STEP_SIZE);
    }
    private void moveDownLeft(){
        getPosition().translatePosition(-STEP_SIZE,STEP_SIZE);
        picture.translate(-STEP_SIZE,STEP_SIZE);
    }
    private void moveLeft(){
        getPosition().translatePosition(-STEP_SIZE,0);
        picture.translate(-STEP_SIZE,0);
    }
    private void moveUpLeft(){
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
    public boolean hasBall(){
        return hasBall;
    }

}
