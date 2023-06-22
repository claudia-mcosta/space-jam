package org.academiadecodigo.gamesweek.gameObjects;
import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball extends GameObject{

    private Position center;
    private double radius;

    public Ball(Picture picture){
        super(picture);

        center = StartingPositions.POSITION_12.getPosition();
        radius = (double) picture.getHeight() /2;
    }


    public boolean hitsBorder(){

        if(center.getX()<(Game.PADDING*4) || center.getX()>Game.screenWidth-(Game.PADDING*4) || center.getY()<(Game.PADDING*4) || center.getY()>Game.screenHeight-(Game.PADDING*4)){
            double currentX = this.getPicture().getX();
            double currentY = this.getPicture().getY();
            double currentMaxX = this.getPicture().getMaxX();
            double currentMaxY = this.getPicture().getMaxY();
            double currentCenterX = center.getX();
            double currentCenterY = center.getY();

            double finalX = StartingPositions.POSITION_6.getPosition().getX();
            double finalY = StartingPositions.POSITION_6.getPosition().getY();
            double finalMaxX = StartingPositions.POSITION_6.getPosition().getX()+this.getPicture().getWidth();
            double finalMaxY = StartingPositions.POSITION_6.getPosition().getY()+this.getPicture().getHeight();
            double finalCenterX = StartingPositions.POSITION_12.getPosition().getX();
            double finalCenterY = StartingPositions.POSITION_12.getPosition().getY();

            this.getPicture().translate(currentX-finalX,currentY-finalY);
            this.getPosition().translatePosition(currentX-finalX,currentY-finalY);
            this.getMaxPosition().translatePosition(currentMaxX-finalMaxX,currentMaxY-finalMaxY);
            center.translatePosition(currentCenterX-finalCenterX,currentCenterY-finalCenterY);
        }
        return false;
    }

    public void move(){

        switch (getDirection()){
            case UP:
                moveUp();
                center.translatePosition(0,-STEP_SIZE);
                break;
            case UP_RIGHT:
                moveUpRight();
                center.translatePosition(DIAGONAL_STEP,-DIAGONAL_STEP);
                break;
            case RIGHT:
                moveRight();
                center.translatePosition(STEP_SIZE,0);
                break;
            case DOWN_RIGHT:
                moveDownRight();
                center.translatePosition(DIAGONAL_STEP,DIAGONAL_STEP);
                break;
            case DOWN:
                moveDown();
                center.translatePosition(0,STEP_SIZE);
                break;
            case DOWN_LEFT:
                moveDownLeft();
                center.translatePosition(-DIAGONAL_STEP,DIAGONAL_STEP);
                break;
            case LEFT:
                moveLeft();
                center.translatePosition(-STEP_SIZE,0);
                break;
            case UP_LEFT:
                moveUpLeft();
                center.translatePosition(-DIAGONAL_STEP,-DIAGONAL_STEP);
                break;
            default:
                break;
        }
        //reCenterBall();
            /*System.out.println(position.toString());
            System.out.println("Max Position- "+maxPosition.toString());
            System.out.println("Picture - X: "+picture.getX()+" Y: "+picture.getY()+"|| Max X: "+picture.getMaxX()+" Max Y: "+picture.getMaxY());*/
    }

    public Position getCenter(){return center;}
    public double getRadius(){return radius;}

}
