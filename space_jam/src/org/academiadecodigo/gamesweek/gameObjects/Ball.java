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
        super(StartingPositions.POSITION_6.getPosition(),null, Direction.LEFT);

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
}
