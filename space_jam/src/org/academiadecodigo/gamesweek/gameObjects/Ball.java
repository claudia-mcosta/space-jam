package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;

public class Ball extends GameObject {

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
}
