package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Character extends GameObject{

    private boolean hasBall=false;

    public Character(Picture picture, Position position, Direction direction){
        super(picture,position,direction);
    }

    public boolean hasBall(){
        return hasBall;
    }

}
