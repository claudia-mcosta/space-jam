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

    public boolean characterCollision(Character character){

        double r1x = this.getPosition().getX();
        double r1y = this.getPosition().getY();
        double r1w = this.getWidth();
        double r1h = this.getHeight();
        double r2x = character.getPosition().getX();
        double r2y = character.getPosition().getY();
        double r2w = character.getWidth();
        double r2h = character.getHeight();

        if (r1x + r1w >= r2x &&    // r1 right edge past r2 left
                r1x <= r2x + r2w &&    // r1 left edge past r2 right
                r1y + r1h >= r2y &&    // r1 top edge past r2 bottom
                r1y <= r2y + r2h) {    // r1 bottom edge past r2 top

            return true;
        }
        return false;
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
        }else {
            hasBall=false;
        }
    }


    public void reCenter(StartingPositions position, double width, double height){

        double currentX = getPosition().getX();
        double currentY = getPosition().getY();
        double currentMaxX = currentX+width;
        double currentMaxY = currentY+height;

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
