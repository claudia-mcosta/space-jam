package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar{

    private int speed;
    private int size;
    private Picture picture;
    private Position position;
    private Direction direction;
    public static int MAX_STEPS=5;
    private int currentSteps;
    private int stepSize = 20; //number of pixels it moves per step

    public Monstar(StartingPositions position, Picture picture){
        speed=1;
        size=Game.cellSize;
        this.position = position.getPosition();
        this.picture = picture;
        this.currentSteps=0;
        this.direction=Direction.values()[(int)(Math.random()*Direction.values().length)];
    }

    public void translateTo(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        picture.translate(translateX,translateY);
    }

    public void draw(){
        picture.draw();
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

    public void moveRandom(){

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

        takeAStep();
    }

    public void newDirection(){
        this.direction=Direction.values()[(int)(Math.random()*Direction.values().length)];
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
