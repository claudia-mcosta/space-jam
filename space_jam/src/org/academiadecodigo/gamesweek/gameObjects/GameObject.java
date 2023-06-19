package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameObject {

    private Picture picture;
    private Position position;
    private Position maxPosition;
    private Direction direction;

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
                if(picture.getX()<=stepSize)
                    return true;
                break;
            case UP:
                if(picture.getY()<=stepSize)
                    return true;
                break;
            case RIGHT:
                if((picture.getMaxX()+stepSize)>= Game.screenWidth)
                    return true;
                break;
            case DOWN:
                if((picture.getMaxY()+stepSize)>=Game.screenHeight)
                    return true;
                break;
            case UP_RIGHT:
                if(picture.getY()<stepSize || (picture.getMaxX()+stepSize)>=Game.screenWidth)
                    return true;
                break;
            case DOWN_RIGHT:
                if((picture.getMaxY()+stepSize)>=Game.screenHeight || (picture.getMaxX()+stepSize)>=Game.screenWidth)
                    return true;
                break;
            case DOWN_LEFT:
                if((picture.getMaxY()+stepSize)>=Game.screenHeight || picture.getX()<=stepSize)
                    return true;
                break;
            case UP_LEFT:
                if(picture.getY()<=stepSize || picture.getX()<=stepSize)
                    return true;
                break;
            default:
                return true;
        }
        return false;
    }

    private void moveUp(){
        position.translatePosition(0,-stepSize);
        picture.translate(0,-stepSize);
    }
    private void moveUpRight(){
        position.translatePosition(stepSize,-stepSize);
        picture.translate(stepSize,-stepSize);
    }
    private void moveRight(){
        position.translatePosition(stepSize,0);
        picture.translate(stepSize,0);
    }
    private void moveDownRight(){
        position.translatePosition(stepSize,stepSize);
        picture.translate(stepSize,stepSize);
    }
    private void moveDown(){
        position.translatePosition(0,stepSize);
        picture.translate(0,stepSize);
    }
    private void moveDownLeft(){
        position.translatePosition(-stepSize,stepSize);
        picture.translate(-stepSize,stepSize);
    }
    private void moveLeft(){
        position.translatePosition(-stepSize,0);
        picture.translate(-stepSize,0);
    }
    private void moveUpLeft(){
        position.translatePosition(-stepSize,-stepSize);
        picture.translate(-stepSize,-stepSize);
    }

    public void moveRandom(Position ballPosition){

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
        if (hasBall)
            dragBall(ballPosition);

        takeAStep();
    }
}
