package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Hoop {

    private Picture hoop;

    public Hoop(Picture picture) {
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
