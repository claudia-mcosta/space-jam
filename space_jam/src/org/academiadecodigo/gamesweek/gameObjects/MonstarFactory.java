package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MonstarFactory {

    private Monstar createAdversaries(int numAdversaries){

        Picture picture = new Picture(0,0,"resources/monstar"+(i+1)+".png");
        Monstar monstar = new Monstar(StartingPositions.values()[i+1],picture);

        for(int i=0; i<numAdversaries;i++){
            Picture picture = new Picture(0,0,"resources/monstar"+(i+1)+".png");
            monstar[i] = new Monstar(StartingPositions.values()[i+1],picture);
        }

    }

    private void drawAdversaries(){

        for(int i=0;i<numAdversaries;i++){
            monstar[i].translateTo(StartingPositions.POSITION_0.getPosition(),StartingPositions.values()[i+1].getPosition());
            monstar[i].draw();
        }
    }
}
