package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Hoop {

        private Picture picture;
        private Rectangle target;
        private int width;
        private int height;
        private Position pos;

        public Hoop(double x, double y) {
            this.pos = new Position(x + Game.PADDING, y + Game.PADDING);
            this.picture = new Picture(pos.getX(), pos.getY(), "resources/hoop.png");
            this.target = new Rectangle(pos.getX(), pos.getY(), picture.getWidth(), picture.getHeight());

            grow(width, height);
        }

        // Getters
        public Rectangle getTarget(){
            return target;
        }
        public Position getPos() {
            return pos;
        }

        // Methods
        public void draw() {
        // Draw hoop image
            picture.draw();
        }

        private void center() {
        // Center the picture and target on the Position

            picture.translate(-(double) picture.getWidth() / 2, -(double) picture.getHeight() / 2);
            target.translate(-(double) target.getWidth() / 2, -(double) target.getHeight() / 2);
            
        }

        private void grow(int width, int height) {
        // Change size of picture and target

            // Center picture before resizing, so it remains in the correct position
            center();

            picture.grow(width, height);
            target.grow(width, height);

        }

}
