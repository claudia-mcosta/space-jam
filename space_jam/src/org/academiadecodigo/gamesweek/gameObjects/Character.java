package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Character extends GameObject{

    private boolean hasBall=false;

    public Character(Picture picture, StartingPositions position, Direction direction){
        super(picture,position,direction);
    }

    public void ballCollision(Ball ball){

        //Ball
        double cx = ball.getCenter().getX();
        double cy = ball.getCenter().getY();
        double radius = ball.getRadius();
        //Character
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
            if(ball.hitsBorder()){
                ball.reCenter();
            }else
                ball.move();
            System.out.println("Ball Centre: "+ball.getCenter().toString());
            System.out.println("OVERLAPS");
        }else {
            hasBall=false;
        }
    }


    public void reCenter(StartingPositions position, double width, double height){
        //double pictureX = this.getPicture().getX();
        //double pictureY = this.getPicture().getY();
        double currentX = getPosition().getX();
        double currentY = getPosition().getY();
        double currentMaxX = currentX+width;
        double currentMaxY = currentY+height;

        //double pictureFinalX = position.getPosition().getX();
        //double pictureFinalY = position.getPosition().getY();
        double finalX = position.getPosition().getX();
        double finalY = position.getPosition().getY();
        double finalMaxX = finalX+width;
        double finalMaxY = finalY+height;

        this.getPicture().translate(finalX-currentX,finalY-currentY);
        this.getPosition().translatePosition(finalX-currentX,finalY-currentY);
        this.getMaxPosition().translatePosition(finalMaxX-currentMaxX,finalMaxY-currentMaxY);
    }

    public boolean hasBall(){
        return hasBall;
    }
}
