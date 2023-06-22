package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Character extends GameObject{

    private boolean hasBall=false;

    public Character(Picture picture, Position position, Direction direction){
        super(picture,position,direction);
    }

    public void ballCollision(Ball ball){

        double cx = ball.getCenter().getX();
        double cy = ball.getCenter().getY();
        double radius = ball.getRadius();
        double rx = this.getPosition().getX();
        double ry = this.getPosition().getY();
        double rw = this.getWidth();
        double rh = this.getHeight();

        // temporary variables to set edges for testing
        double testX = cx;
        double testY = cy;

        // which edge is closest?
        if (cx < rx)         testX = rx;      // test left edge
        else if (cx > rx+rw) testX = rx+rw;   // right edge
        if (cy < ry)         testY = ry;      // top edge
        else if (cy > ry+rh) testY = ry+rh;   // bottom edge

        // get distance from closest edges
        double distX = cx-testX;
        double distY = cy-testY;
        double distance = Math.sqrt((distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance <= radius) {
            hasBall=true;
            ball.setDirection(this.getDirection());
            //if(!ball.hitsBorder())
            ball.move();
            System.out.println("Ball Centre: "+ball.getCenter().toString());
            System.out.println("OVERLAPS");
        }else {
            hasBall=false;
        }
    }

    public boolean hasBall(){
        return hasBall;
    }
}
