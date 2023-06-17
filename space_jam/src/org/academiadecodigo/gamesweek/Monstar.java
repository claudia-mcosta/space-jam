package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Monstar extends Adversaries{

    private int speed;
    private int size;
    private Picture picture;
    private Position position;

    public Monstar(){
        speed=1;
        size=Game.cellSize;
    }

    public void setPicture(Picture picture){
        this.picture = picture;
    }

    public void translate(Position position){
        picture.translate(position.getX(),position.getY());
    }

    public void draw(){
        picture.draw();
    }
}
