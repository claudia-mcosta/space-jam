package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Character extends GameObject{

    private boolean hasBall=false;
    private int hoopSize = 40;

    public Character(Picture picture, Position position, Direction direction){
        super(picture,position,direction);
    }

    public boolean isAtHoop(){

        Picture picture = getPicture();
        Position hoopPosition = StartingPositions.POSITION_8.getPosition();

        if(picture.getMaxX()>hoopPosition.getX() &&
        picture.getX()<hoopPosition.getX()+hoopSize &&
        picture.getY()<hoopPosition.getY()+hoopSize &&
        picture.getMaxY()>hoopPosition.getY()){
            return true;
        }
        return false;
    }

    public boolean hasBall(){
        return hasBall;
    }

}
