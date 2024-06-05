package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball extends GameObject {

    private Position center;
    private double radius;
    private Picture picture;

    public Ball(Picture picture){
        super(picture);

        this.picture=picture;
        center = new Position(StartingPositions.POSITION_12);
        radius = (double) picture.getHeight() /2;
    }


    public boolean hitsBorder(){
        return (center.getX()<(Game.PADDING*4) || center.getX()>Game.screenWidth-(Game.PADDING*4) || center.getY()<(Game.PADDING*4) || center.getY()>Game.screenHeight-(Game.PADDING*4));
    }

    public void reCenter(){
        double currentX = this.getPicture().getX();
        double currentY = this.getPicture().getY();
        double currentCenterX = center.getX();
        double currentCenterY = center.getY();

        double finalX = StartingPositions.POSITION_6.getPosition().getX();
        double finalY = StartingPositions.POSITION_6.getPosition().getY();
        double finalCenterX = StartingPositions.POSITION_12.getPosition().getX();
        double finalCenterY = StartingPositions.POSITION_12.getPosition().getY();
        //double finalCenterY = StartingPositions.POSITION_12.getPosition().getY();

        this.getPicture().translate(finalX-currentX,finalY-currentY);
        //this.getPosition().translatePosition(currentX-finalX,currentY-finalY);
        center.translatePosition(finalCenterX-currentCenterX,finalCenterY-currentCenterY);
    }

    //Move object in a direction - POS & GFX
    public void moveUp(){
        picture.translate(0,-STEP_SIZE);
        center.translatePosition(0,-STEP_SIZE);
    }
    public void moveUpRight(){
        picture.translate(DIAGONAL_STEP,-DIAGONAL_STEP);
        center.translatePosition(DIAGONAL_STEP,-DIAGONAL_STEP);
    }
    public void moveRight(){
        picture.translate(STEP_SIZE,0);
        center.translatePosition(STEP_SIZE,0);
    }
    public void moveDownRight(){
        picture.translate(DIAGONAL_STEP,DIAGONAL_STEP);
        center.translatePosition(DIAGONAL_STEP,DIAGONAL_STEP);
    }
    public void moveDown(){
        picture.translate(0,STEP_SIZE);
        center.translatePosition(0,STEP_SIZE);
    }
    public void moveDownLeft(){
        picture.translate(-DIAGONAL_STEP,DIAGONAL_STEP);
        center.translatePosition(-DIAGONAL_STEP,DIAGONAL_STEP);
    }
    public void moveLeft(){
        picture.translate(-STEP_SIZE,0);
        center.translatePosition(-STEP_SIZE,0);

    }
    public void moveUpLeft(){
        picture.translate(-DIAGONAL_STEP,-DIAGONAL_STEP);
        center.translatePosition(-DIAGONAL_STEP,-DIAGONAL_STEP);
    }

    public Position getCenter(){return center;}
    public double getRadius(){return radius;}

}
