package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObject {

    private Position position; //top left position
    private Position maxPosition; //bottom right position
    private Direction direction;
    public static final int STEP_SIZE = 10; //number of pixels it moves per step

    public GameObject(Position position, Position maxPosition, Direction direction){

        this.position=position;
        this.maxPosition=maxPosition;
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



    public abstract void translateTo(Position startingPosition, Position endPosition);






    public Direction getDirection(){
        return direction;
    }
    public Position getPosition(){return position;}
}
