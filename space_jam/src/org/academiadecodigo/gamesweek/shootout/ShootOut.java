package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.Position;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/* TO DO
 *
 * Overall cleanup with methods and classes
 * Animations
 * Clear field at start and clear shootOut at end
 * Place score in Player? Find best place/object/class for methods
 * Creating a grid would be nice
 * Add message with shootOut explanation before start that goes away once player presses "I" to start shootOut?
 */

public class ShootOut {

    private Canvas canvas;
    private Picture background;
    private Hoop hoop;
    private Picture ball;
    private int score;
    private Player player;
    private Text textScore;

    public ShootOut() {
        this.canvas = Canvas.getInstance();
        this.background = new Picture(Game.PADDING, Game.PADDING,"resources/bleachers.jpeg");
        this.hoop = new Hoop((double) 1250 / 2, (double) 750 / 2); // Replace with new Hoop(Game.screenWidth / 2, Game.screen.Height / 2);
        this.ball = new Picture(Game.PADDING, Game.PADDING,"resources/ball.png");
        this.player = new Player();
        this.score = 0;
    }

    public void init(){

        /*
         * Test to hide objects that are outside the canvas bounds. NOT WORKING!!
         *
         * JFrame frame = new JFrame("FrameDemo");
         * frame.pack();
         * frame.setVisible(true);
         */

        // Reposition, resize and show background image (change 1250 and 750 to Game.getWidth() and Game.getHeight())
        background.translate((double) (1250 - background.getWidth()) / 2, (double) (750 - background.getHeight()) / 2);
        background.grow((double) (1250 - background.getWidth()) / 2, (double) (750 - background.getHeight()) / 2);
        canvas.show(background);

        // Draw hoop image and target frame
        hoop.draw();

        // Reposition, resize and show ball image - Improve ball translate to be more dynamic in relation to screen size
        ball.translate((double) 1250 / 2,500);
        canvas.show(ball);

        // Draw score board
        Rectangle scoreBoard = new Rectangle(50,50,80,60);
        scoreBoard.draw();
        scoreBoard.fill();
        scoreBoard.setColor(Color.WHITE);

        this.textScore = new Text(75, 75, score + " POINTS");
        textScore.grow(20,20);
        textScore.draw();

    }

    public void start() {
        player.aim(hoop, this);
    }

    public int score(Position shot) {

        /* Debugging
         *
         * int shotGetX = (int) shot.getX();
         *
         * System.out.println("Shot Left Edge = " + shotGetX);
         * System.out.println("Shot Right Edge = " + (shotGetX + 100));
         * System.out.println("Hoop Left Edge = " + hoop.getTarget().getX());
         * System.out.println("Hoop Right Edge = " + (hoop.getTarget().getX() + hoop.getTarget().getWidth()));
         */

        // change 100 to aim size
        // Show score from shot and add it to the overall score
        if ((int) shot.getX() + 100 >= hoop.getTarget().getX() && (int) shot.getX() <= (hoop.getTarget().getWidth() + hoop.getTarget().getX())) {
            score += 3;
        }

        // System.out.println("Score: " + score);

        textScore.setText(score + " POINTS");

        return score;
    }


    public static void shoot() {

        ShootOut shootOut = new ShootOut();

        shootOut.init();
        shootOut.start();

    }

}
