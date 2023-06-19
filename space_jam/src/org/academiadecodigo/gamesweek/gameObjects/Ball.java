package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball extends GameObject{

    private Ellipse ball;
    private Position ballPosition;
    public static double BALL_SIZE=Game.CELL_SIZE/2;

    public Ball(){
        super(StartingPositions.POSITION_6.getPosition(),new Position(StartingPositions.POSITION_6.getPosition().getX()+BALL_SIZE,StartingPositions.POSITION_6.getPosition().getY()+BALL_SIZE), Direction.LEFT);

        ballPosition = new Position(StartingPositions.POSITION_6);
        ball = new Ellipse(ballPosition.getX(), ballPosition.getY(), BALL_SIZE, BALL_SIZE);
        ball.setColor(Color.ORANGE);
        ball.fill();

    }

    public Position findInscribedSquare(Position ballPosition, double squareSide){

        double squareX = ballPosition.getX()+(Game.BALL_SIZE-squareSide)/2;
        double squareY = ballPosition.getY()+(Game.BALL_SIZE-squareSide)/2;

        return new Position(squareX,squareY);
    }

    public void translateTo(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        ball.translate(translateX,translateY);
    }

    private void moveUp(){
        getPosition().translatePosition(0,-STEP_SIZE);
        ball.translate(0,-STEP_SIZE);
    }
    private void moveUpRight(){
        getPosition().translatePosition(STEP_SIZE,-STEP_SIZE);
        ball.translate(STEP_SIZE,-STEP_SIZE);
    }
    private void moveRight(){
        getPosition().translatePosition(STEP_SIZE,0);
        ball.translate(STEP_SIZE,0);
    }
    private void moveDownRight(){
        getPosition().translatePosition(STEP_SIZE,STEP_SIZE);
        ball.translate(STEP_SIZE,STEP_SIZE);
    }
    private void moveDown(){
        getPosition().translatePosition(0,STEP_SIZE);
        ball.translate(0,STEP_SIZE);
    }
    private void moveDownLeft(){
        getPosition().translatePosition(-STEP_SIZE,STEP_SIZE);
        ball.translate(-STEP_SIZE,STEP_SIZE);
    }
    private void moveLeft(){
        getPosition().translatePosition(-STEP_SIZE,0);
        ball.translate(-STEP_SIZE,0);
    }
    private void moveUpLeft(){
        getPosition().translatePosition(-STEP_SIZE,-STEP_SIZE);
        ball.translate(-STEP_SIZE,-STEP_SIZE);
    }
}
