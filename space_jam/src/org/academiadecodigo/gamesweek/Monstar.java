package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar{

    private int speed;
    private int size;
    private Picture picture;
    private Position position;

    public Monstar(StartingPositions position, Picture picture){
        speed=1;
        size=Game.cellSize;
        this.position = position.getPosition();
        this.picture = picture;
    }

    public void moveTo(Position startingPosition, Position endPosition){
        double translateX = endPosition.getX()-startingPosition.getX();
        double translateY = endPosition.getY()-startingPosition.getY();
        picture.translate(translateX,translateY);
    }

    public void draw(){
        picture.draw();
    }
}
