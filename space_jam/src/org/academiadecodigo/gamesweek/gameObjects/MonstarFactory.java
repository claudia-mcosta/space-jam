package org.academiadecodigo.gamesweek.gameObjects;


import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MonstarFactory {

    public static Monstar createMonstar(int i){

        Picture picture = new Picture(0,0,"space_jam/resources/monstar"+(i+1)+".png");
        Direction direction = Direction.values()[(int)(Math.random()*Direction.values().length)];

        Monstar monstar = new Monstar(picture, StartingPositions.values()[i+1],direction);

        return monstar;
    }
}
