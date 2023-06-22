package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Character extends GameObject{

    private boolean hasBall=false;

    public Character(Picture picture, Position position, Direction direction){
        super(picture,position,direction);
    }

    public void tryStealBall(Ball ball){
        if(this.overlaps(ball)) {
            hasBall = true;
            ball.setFollowing(this);
            //System.out.println(this);
        }else {
            hasBall = false;
            ball.setFollowing(null);
        }
    }

    public boolean hasBall(){
        return hasBall;
    }
}
