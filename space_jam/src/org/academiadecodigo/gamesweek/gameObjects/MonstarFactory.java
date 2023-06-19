package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MonstarFactory {

    public static Monstar createMonstar(int i){
        Picture picture = new Picture(0,0,"resources/monstar"+(i+1)+".png");
        Direction direction=Direction.values()[(int)(Math.random()*Direction.values().length)];

        Monstar monstar = new Monstar(StartingPositions.values()[i+1],picture, direction);

        monstar.translateTo(StartingPositions.POSITION_0.getPosition(),StartingPositions.values()[i+1].getPosition());
        monstar.draw();

        return monstar;
    }

}
