package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Player extends GameObject{

    private boolean hasBall=false;

    public Player(Position position, Picture picture, Direction direction) {
        super(position, picture, direction);
    }

    public boolean hasBall(){
        return hasBall;
    }

}
