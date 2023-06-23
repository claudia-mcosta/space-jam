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
    private Rectangle aimBar;
    private Position[] aimBarSize = new Position[2];
    private Direction direction = Direction.RIGHT;

    public Aim(Hoop hoop) {

        // Set positions for start and end of aimbar
        aimBarSize[0] = StartingPositions.POSITION_9.getPosition();
        aimBarSize[1] = StartingPositions.POSITION_10.getPosition();

        // Set aim starting position (currently starting at left boundary)
        this.pos = new Position(aimBarSize[0].getX(), aimBarSize[0].getY());
        this.aim = new Picture(pos.getX(), pos.getY(), "resources/aim.png"); // new Rectangle(pos.getX(), pos.getY(), aimSize, aimSize);
        this.aimSize = aim.getWidth();
        this.aimBar = new Rectangle(aimBarSize[0].getX(), aimBarSize[0].getY(), aimBarSize[1].getX(), aimSize);

        /* To use if aim is a square and not a picture
         * // Set initial color
         * aim.setColor(color);
         * // Show aimbar + aim on screen
         * aimBar.draw();
         * // Fill aim shape
         * aim.fill();
         */

        // To use if aim is a picture
        aim.draw();
    }

    public Position getPos() {
        return pos;
    }

    public int getAimSize() {
        return aimSize;
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

    public void moveOppositeDirection(Direction currentDirection) {
    // Switch movement direction to opposite direction - Place in Direction class?

        switch (currentDirection) {
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
        if (aim.getX() <= aimBarSize[0].getX() || (aim.getX() + aim.getWidth()) >= (aimBarSize[0].getX() + aimBar.getWidth())) {
            moveOppositeDirection(direction);
        }

        // Draw and fill aim rectangle in new position
        // aim.draw();
        // aim.fill();

    }

    public void clearAim(){
        aim.delete();
    }

}
