package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

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

    /*
     *  Line tests NOT WORKING!!
     *
     *  private int startX;
     *  private int startY;
     *  private int endX;
     *  private int endY;
     *  private Line line = new Line(startX, startY, endX, endY);
     */

    private Rectangle aim;
    private int aimSize = Game.cellSize * 2;
    private Position pos;
    private Color color = Color.BLUE;
    private Rectangle aimBar;
    private Position[] aimBarSize = new Position[2];
    private Direction direction = Direction.RIGHT;

    public Aim(Hoop hoop) {

        /*
         * Line tests NOT WORKING!!
         *
         * this.startX = (1250 / 2) + Game.PADDING; // (Game.screenWidth / 2) + Game.PADDING;
         * this.startY = 750 + Game.PADDING; // Game.screenHeight + Game.PADDING;
         * this.endX = 0;
         * this.endY = 750 + Game.PADDING; // Game.screenHeight + Game.PADDING;
         * this.line = new Line(startX, startY, endX, endY);
         * line.draw();
         */

        // Set positions for start and end of aimbar
        aimBarSize[0] = new Position(Game.PADDING + Game.cellSize, hoop.getPos().getY());
        aimBarSize[1] = new Position((Game.PADDING + 1250) - (Game.cellSize * 2), hoop.getPos().getY());

        // Set aim starting position (currently starting at left boundary)
        this.pos = new Position(aimBarSize[0].getX(), aimBarSize[0].getY());
        this.aim = new Rectangle(pos.getX(), pos.getY(), aimSize, aimSize);
        this.aimBar = new Rectangle(aimBarSize[0].getX(), aimBarSize[0].getY(), aimBarSize[1].getX(),aimSize);

        // Set initial color
        aim.setColor(color);
        // Show aimbar + aim on screen
        aimBar.draw();
        aim.draw();
        // Fill aim shape
        aim.fill();
    }

    public Position getPos() {
        return pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        aim.setColor(color);
    }

    public void moveRight() {
        pos.setX(pos.getX() + Game.cellSize);
        aim.translate(Game.cellSize,0);
    }

    public void moveLeft() {
        pos.setX(pos.getX() - Game.cellSize);
        aim.translate(-Game.cellSize,0);
    }

    public void moveOppositeDirection(Direction currentDirection) {
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

        // Change movement to opposite direction when aim hits boundaries - find better way to calculate the right boundary
        if (aim.getX() <= aimBarSize[0].getX() || aim.getX() >= (aimBarSize[1].getX() - Game.cellSize)) {
            moveOppositeDirection(direction);
        }

        if (aim.getX() >= aimBarSize[1].getX()) {
            System.out.println(aimBarSize[1].getX());
            System.out.println(aim.getX());
            System.out.println(aim.getX() + (aimSize));
        }

        // Draw and fill aim rectangle in new position
        aim.draw();
        aim.fill();

        /* Line tests NOT WORKING!!
         * if (endY > 0 && endX < 1250 + Game.PADDING) {
         *     endY -= 10;
         *     System.out.println("EndY " + endY);
         * }
         * if (endY == 0 && endX < 1250 + Game.PADDING) {
         *     endX += 10;
         *     System.out.println("EndX " + endX);
         * }
         *
         * if (endX == 1250 + Game.PADDING && endY == 0) {
         *     endY += 10;
         * }
         *
         * line.delete();
         * line = new Line(startX, startY, endX, endY);
         * line.draw();
         */
    }



}
