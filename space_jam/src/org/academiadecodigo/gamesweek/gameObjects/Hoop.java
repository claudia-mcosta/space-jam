package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Hoop extends GameObject{

    private Picture hoop;

    public Hoop(Position position, Position maxPosition){
        super(position,maxPosition);
    }

    public Hoop(Picture picture) {
        super(picture);
        this.hoop = picture;

        // Center hoop in frame
        hoop.translate((Game.screenWidth - hoop.getWidth()) / 2, Game.screenHeight - hoop.getHeight());
    }

        // Getters
    public Picture getTarget(){
            return hoop;
        }

        // Methods
    public void draw() {
    // Draw hoop image
        hoop.draw();
    }

    public void delete() {
        hoop.delete();
    }

}
