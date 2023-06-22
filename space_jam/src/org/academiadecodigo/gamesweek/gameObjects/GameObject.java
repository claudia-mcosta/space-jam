package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public abstract class GameObject {

    private Picture picture;
    private Position position; //top left position
    private Position maxPosition; //bottom right position
    private double width;
    private double height;
    private Direction direction;
    public static final int STEP_SIZE = 10; //number of pixels it moves per step
    public static final double DIAGONAL_STEP=Math.sqrt(Math.pow(STEP_SIZE,2)/2);

    //CONSTRUCTORS
    public GameObject(Picture picture, Position position, Direction direction){

        this.picture=picture;
        this.position=position;
        this.direction=direction;
        this.maxPosition = new Position(position.getX()+picture.getWidth(), position.getY()+picture.getHeight());
        this.width=picture.getWidth();
        this.height=picture.getHeight();
    }
    public GameObject(Picture picture, Position position, double side){

        this.picture=picture;
        this.position=position;
        this.direction=null;
        this.maxPosition = new Position(position.getX()+side, position.getY()+side);
        this.width=picture.getWidth();
        this.height=picture.getHeight();
    }

    public GameObject(Position position,Position maxPosition){
        this.position=position;
        this.maxPosition=maxPosition;
        this.width=maxPosition.getX()-position.getX();
        this.height=maxPosition.getY()-position.getY();
    }


    //Checks if object overlaps object2 - POS
    public boolean overlaps(GameObject object2){

        if(maxPosition.getX()>object2.getPosition().getX() &&
                position.getX()<object2.getMaxPosition().getX() &&
                position.getY()<object2.getMaxPosition().getY() &&
                maxPosition.getY()>object2.getPosition().getY()){
            return true;
        }
        return false;
    }

    //Translates picture from one position to another - GFX
    public void translateFrom(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        picture.translate(translateX,translateY);
    }

    //Border checks - GFX
    public boolean hitsBorder(){

        switch (direction) {
            case LEFT:
                if(picture.getX()<=STEP_SIZE)
                    return true;
                break;
            case UP:
                if(picture.getY()<=STEP_SIZE)
                    return true;
                break;
            case RIGHT:
                if((picture.getMaxX()+STEP_SIZE)>= Game.screenWidth)
                    return true;
                break;
            case DOWN:
                if((picture.getMaxY()+STEP_SIZE)>=Game.screenHeight)
                    return true;
                break;
            case UP_RIGHT:
                if(picture.getY()<STEP_SIZE || (picture.getMaxX()+STEP_SIZE)>=Game.screenWidth)
                    return true;
                break;
            case DOWN_RIGHT:
                if((picture.getMaxY()+STEP_SIZE)>=Game.screenHeight || (picture.getMaxX()+STEP_SIZE)>=Game.screenWidth)
                    return true;
                break;
            case DOWN_LEFT:
                if((picture.getMaxY()+STEP_SIZE)>=Game.screenHeight || picture.getX()<=STEP_SIZE)
                    return true;
                break;
            case UP_LEFT:
                if(picture.getY()<=STEP_SIZE || picture.getX()<=STEP_SIZE)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    //Move object in a direction - POS & GFX
    public void moveUp(){
        position.translatePosition(0,-STEP_SIZE);
        maxPosition.translatePosition(0,-STEP_SIZE);
        picture.translate(0,-STEP_SIZE);
    }
    public void moveUpRight(){
        position.translatePosition(DIAGONAL_STEP,-DIAGONAL_STEP);
        maxPosition.translatePosition(DIAGONAL_STEP,-DIAGONAL_STEP);
        picture.translate(DIAGONAL_STEP,-DIAGONAL_STEP);
    }
    public void moveRight(){
        position.translatePosition(STEP_SIZE,0);
        maxPosition.translatePosition(STEP_SIZE,0);
        picture.translate(STEP_SIZE,0);
    }
    public void moveDownRight(){
        position.translatePosition(DIAGONAL_STEP,DIAGONAL_STEP);
        maxPosition.translatePosition(DIAGONAL_STEP,DIAGONAL_STEP);
        picture.translate(DIAGONAL_STEP,DIAGONAL_STEP);
    }
    public void moveDown(){
        position.translatePosition(0,STEP_SIZE);
        maxPosition.translatePosition(0,STEP_SIZE);
        picture.translate(0,STEP_SIZE);
    }
    public void moveDownLeft(){
        position.translatePosition(-DIAGONAL_STEP,DIAGONAL_STEP);
        maxPosition.translatePosition(-DIAGONAL_STEP,DIAGONAL_STEP);
        picture.translate(-DIAGONAL_STEP,DIAGONAL_STEP);
    }
    public void moveLeft(){
        position.translatePosition(-STEP_SIZE,0);
        maxPosition.translatePosition(-STEP_SIZE,0);
        picture.translate(-STEP_SIZE,0);
    }
    public void moveUpLeft(){
        position.translatePosition(-DIAGONAL_STEP,-DIAGONAL_STEP);
        maxPosition.translatePosition(-DIAGONAL_STEP,-DIAGONAL_STEP);
        picture.translate(-DIAGONAL_STEP,-DIAGONAL_STEP);
    }

    //Check direction on which to move object
    public void move(){

        switch (getDirection()){
            case UP:
                moveUp();
                break;
            case UP_RIGHT:
                moveUpRight();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN_RIGHT:
                moveDownRight();
                break;
            case DOWN:
                moveDown();
                break;
            case DOWN_LEFT:
                moveDownLeft();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP_LEFT:
                moveUpLeft();
                break;
            default:
                break;
        }
    }

    //Draws object - GFX
    public void draw(){
        picture.draw();
    }

    //GETTERS & SETTERS
    public void setDirection(Direction direction){
        this.direction=direction;
    }
    public Direction getDirection(){
        return direction;
    }
    public Picture getPicture(){return picture;}
    public Position getPosition(){return position;}
    public Position getMaxPosition(){return maxPosition;}
    public void setPosition(Position position){this.position = position;}

    @Override
    public String toString() {
        return "X: "+position.getX()+" Y: "+position.getY()+" MaxX: "+maxPosition.getX()+" MaxY: "+maxPosition.getY();
    }
}
