package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.Character;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MichaelJordan extends Character {

    public MichaelJordan(Picture picture,Direction direction){
        super(picture, new Position(StartingPositions.POSITION_7),direction);

        picture.grow(12.5,12.5);
        picture.draw();
    }
}
