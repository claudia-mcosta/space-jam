package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MichaelJordan {

    private Picture player;

    public MichaelJordan(Picture player){
        this.player = player;
        player.draw();
    }

    public void moveRight(){
        player.translate(10,0);
    }

    public void moveLeft(){
        player.translate(-10,0);
    }

    public void moveUp(){
        player.translate(0,-10);
    }

    public void moveDown(){
        player.translate(0,10);
    }

    public void moveDiagonalDownRight(){
        player.translate(10,10);
    }

    public  void moveDiagonalUpRight(){
        player.translate(10,-10);
    }

    public void moveDiagonalUpLeft(){
        player.translate(-10,10);
    }

    public void moveDiagonalDownLeft(){
        player.translate(-10,-10);
    }
}
