package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball extends GameObject{

    private Character following;

    public Ball(Picture picture, Position innerSquarePosition, double innerSquareSide){
        super(picture, innerSquarePosition, innerSquareSide);

        following=null;
    }

    public void moveBall(){
        this.setDirection(following.getDirection());
        move();
    }

    public boolean isFollowing(){
        return following!=null;
    }
}
