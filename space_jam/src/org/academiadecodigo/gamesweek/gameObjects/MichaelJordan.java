package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.gameObjects.Character;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MichaelJordan extends Character {

    public MichaelJordan(Picture picture, Position position, Direction direction){
        super(picture, position,direction);
        picture.grow(12.5,12.5);
    }
}
