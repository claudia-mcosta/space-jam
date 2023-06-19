package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar extends GameObject{

    private int speed;
    private int size;
    private Picture picture;
    private Position position; //top left position
    private Position maxPosition; //bottom right position
    private Direction direction;
    public static int MAX_STEPS=5;
    private int currentSteps;
    private int stepSize = 10; //number of pixels it moves per step
    private int probOfChange = 5; //0-10 where 10 it never changes direction and 0 it changes every time
    private boolean hasBall;

    public Monstar(StartingPositions position, Picture picture){
        speed=1;
        size= Game.cellSize;
        this.picture = picture;
        this.position = position.getPosition();
        this.maxPosition = new Position(position.getPosition().getX()+picture.getMaxX(),position.getPosition().getY()+picture.getMaxY());
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

    public Position findInscribedSquare(Position ballPosition, double squareSide){

        double squareX = ballPosition.getX()+(Game.BALL_SIZE-squareSide)/2;
        double squareY = ballPosition.getY()+(Game.BALL_SIZE-squareSide)/2;

        return new Position(squareX,squareY);
    }

    public void tryStealBall(Position ballPosition){

        double insideSquareSize =Math.sqrt(Math.pow(Game.BALL_SIZE,2)/2);
        Position insideSquarePosition = findInscribedSquare(ballPosition, insideSquareSize);



        switch (direction){
            case UP:
                if(position.getY()>(insideSquarePosition.getY()+insideSquareSize) && maxPosition.getX()>insideSquarePosition.getX() && position.getX()<(insideSquarePosition.getX()+insideSquareSize)){
                    hasBall=true;
                }
                break;
            case UP_RIGHT:
                if(position.getY()>(insideSquarePosition.getY()+insideSquareSize) && maxPosition.getX()>insideSquarePosition.getX() && position.getX()<(insideSquarePosition.getX()+insideSquareSize) ||
                        maxPosition.getX()>insideSquarePosition.getX() && maxPosition.getY()>insideSquarePosition.getY() && (position.getY()<insideSquarePosition.getY()+insideSquareSize) ){
                    hasBall=true;
                }
                break;
            case RIGHT:
                if(maxPosition.getX()>insideSquarePosition.getX() && maxPosition.getY()>insideSquarePosition.getY() && (position.getY()<insideSquarePosition.getY()+insideSquareSize)){
                    hasBall=true;
                }
                break;
            case DOWN_RIGHT:
                if (maxPosition.getX()>insideSquarePosition.getX() && maxPosition.getY()>insideSquarePosition.getY() && (position.getY()<insideSquarePosition.getY()+insideSquareSize) ||
                        maxPosition.getY()>insideSquarePosition.getY() && maxPosition.getX()>insideSquarePosition.getX() && (position.getX()<insideSquarePosition.getX()+insideSquareSize)){
                    hasBall=true;
                }
                break;
            case DOWN:
                if(maxPosition.getY()>insideSquarePosition.getY() && maxPosition.getX()>insideSquarePosition.getX() && (position.getX()<insideSquarePosition.getX()+insideSquareSize)){
                    hasBall=true;
                }
                break;
            case DOWN_LEFT:
                if(maxPosition.getY()>insideSquarePosition.getY() && maxPosition.getX()>insideSquarePosition.getX() && (position.getX()<insideSquarePosition.getX()+insideSquareSize) ||
                        position.getX()<(insideSquarePosition.getX()+insideSquareSize) && (position.getY()<insideSquarePosition.getY()+insideSquareSize) && maxPosition.getY()>insideSquarePosition.getY()){
                    hasBall=true;
                }
                break;
            case LEFT:
                if(position.getX()<(insideSquarePosition.getX()+insideSquareSize) && (position.getY()<insideSquarePosition.getY()+insideSquareSize) && maxPosition.getY()>insideSquarePosition.getY()){
                    hasBall=true;
                }
                break;
            case UP_LEFT:
                if(position.getY()>(insideSquarePosition.getY()+insideSquareSize) && maxPosition.getX()>insideSquarePosition.getX() && position.getX()<(insideSquarePosition.getX()+insideSquareSize) ||
                        position.getX()<(insideSquarePosition.getX()+insideSquareSize) && (position.getY()<insideSquarePosition.getY()+insideSquareSize) && maxPosition.getY()>insideSquarePosition.getY()){
                    hasBall=true;
                }
                break;
            default:
                hasBall=false;
        }

    }

    public void dragBall(Position ballPosition){

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
                if((picture.getMaxX()+stepSize)>=Game.screenWidth)
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

    public void chooseDirection(){

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

    public int getCurrentSteps(){
        return currentSteps;
    }
    public void takeAStep(){
        currentSteps++;
    }
    public void resetCurrentSteps(){
        currentSteps=0;
    }
    public Direction getDirection(){
        return direction;
    }
    public boolean hasBall(){
        return hasBall;
    }

}
