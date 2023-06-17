package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Adversaries {
    private Picture[] monstars;
    private int numAdversaries=5;
    private StartingPositions startingPosition;

    public Adversaries(){

        monstars = new Picture[numAdversaries];

        for(int i=0; i<numAdversaries;i++){
            monstars[i] = new Picture(0,0,"resources/monstar"+(i+1)+".png");
        }
    }

    private void drawMonstars(){

        for(int i=0;i<numAdversaries;i++){
            startingPosition = StartingPositions.values()[i];
            monstars[i].translate(startingPosition.getPosition().getX(),startingPosition.getPosition().getY());
            monstars[i].draw();
        }
    }


}
