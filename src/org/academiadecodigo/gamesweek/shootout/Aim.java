package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/* TO DO
 *
 * IMPORTANT --> Slow down aim movement, it's too fast!
 * Replace 1250 for Game.screenWidth and 750 for Game.screenHeight once shootOut can run from Game
 *
 *
 * NON MVP IMPROVEMENTS:
 *
 * Increase aim speed as levels go up ?
 * Reduce aim size as levels go up ?
 */

public class Aim {

    private Picture aim; // Rectangle aim;
    private int aimSize; // = Game.SHOOTOUT_CELL_SIZE * 5;
    private Position pos;
    private Color color = Color.BLUE;
    private Position[] aimBorder = new Position[2];
    private Direction direction = Direction.RIGHT;

    public Aim() {

        // Set positions for start and end of aimbar
        aimBorder[0] = StartingPositions.POSITION_9.getPosition();
        aimBorder[1] = StartingPositions.POSITION_10.getPosition();

        // Set aim starting position (currently starting at left boundary)
        this.pos = new Position(aimBorder[0].getX(), aimBorder[0].getY());
        this.aim = new Picture(pos.getX(), pos.getY(), "resources/aimYellow.png"); // new Rectangle(pos.getX(), pos.getY(), aimSize, aimSize);
        this.aimSize = aim.getWidth();

        /* To use if aim is a square and not a picture
         * // Set initial color
         * aim.setColor(color);
         * // Show aimbar + aim on screen
         * aimBar.draw();
         */

    }

    public void reset() {
        pos.setX(aimBorder[0].getX());
        aim.translate(pos.getX() - aim.getX(), pos.getY() - aim.getY());
        direction = Direction.RIGHT;
        show();
    }

    public Position getPos() {
        return pos;
    }

    public int getAimCenter() {
        return aim.getWidth() / 2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        // aim.setColor(color);
    }

    public void moveRight() {
        pos.setX(pos.getX() + Game.SHOOTOUT_CELL_SIZE);
        aim.translate(Game.SHOOTOUT_CELL_SIZE,0);
    }

    public void moveLeft() {
        pos.setX(pos.getX() - Game.SHOOTOUT_CELL_SIZE);
        aim.translate(-Game.SHOOTOUT_CELL_SIZE,0);
    }

    public void moveOppositeDirection() {
    // Switch movement direction to opposite direction - Place in Direction class?

        switch (direction) {
            case LEFT:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.LEFT;
                break;
            default:
                System.out.println("Unable to change direction.");
                break;
        }
    }

    public void move() {
        // Call specific movement method for current direction

        switch (direction) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            default:
                System.out.println("Unable to move aim.");
        }

        // Change movement to opposite direction when aim hits boundaries
        if (aim.getX() == aimBorder[0].getX() || aim.getX() == (aimBorder[1].getX() - aimSize)) {
            moveOppositeDirection();
        }

        // Draw and fill aim rectangle in new position
        // aim.draw();
        // aim.fill();

    }

    public void clear(){
        reset();
        aim.delete();
    }
    public void show() {
        aim.draw();
        // aim.fill();
    }

}
