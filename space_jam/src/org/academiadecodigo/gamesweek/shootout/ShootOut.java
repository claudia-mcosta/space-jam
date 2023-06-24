package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.gamesweek.gameObjects.MichaelJordan;
import org.academiadecodigo.gamesweek.positions.Position;
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

    private Picture background;
    private Hoop hoop;
    private Picture ball;
    private int score;
    private MichaelJordan michaelJordan;
    private Text textScore;
    private Game field;
    private InputHandler inputHandler;


    public ShootOut(Game field) {
        this.background = new Picture(Game.PADDING, Game.PADDING,"resources/bleachers.jpeg");
        this.hoop = new Hoop(Game.screenWidth / 2, Game.screenHeight / 2);
        this.ball = new Picture(Game.PADDING, Game.PADDING,"resources/ball_shootout.png");
        this.michaelJordan = field.getPlayer();
        this.score = 0;
        this.field = field;
        this.inputHandler = field.getInputHandler();
    }

    public void init(){

        // Reposition, resize and show background image (change 1250 and 750 to Game.getWidth() and Game.getHeight())
        background.translate((Game.screenWidth - background.getWidth()) / 2, (Game.screenHeight - background.getHeight()) / 2);
        background.grow((Game.screenWidth - background.getWidth()) / 2, (Game.screenHeight - background.getHeight()) / 2);
        background.draw();

        // Draw hoop image and target frame
        hoop.draw();

        // Reposition, resize and show ball image - Improve ball translate to be more dynamic in relation to screen size
        // ball.translate((Game.screenWidth / 2,500);
        // ball.draw();

        inputHandler.createKeyPressedEventsShootOut();

    }

    public void start() {
        michaelJordan.aim(hoop, this);
    }

    public void score(Position shot) {


        // change 100 to aim size
        // Show score from shot and add it to the overall score
        if ((int) shot.getX() + michaelJordan.getAim().getAimSize() >= hoop.getTarget().getX() && (int) shot.getX() <= (hoop.getTarget().getWidth() + hoop.getTarget().getX())) {
            score = 3;
        }

        field.setScore(field.getScore() + score);
        clearShootOut();
        updateScoreDisplay();
        inputHandler.createKeyPressedEventsGame();
        inputHandler.removeKeyPressedEventsShootOut();
        field.initDraw();
    }


    public static void shoot(Game field) {

        ShootOut shootOut = new ShootOut(field);
        shootOut.init();
        shootOut.start();
    }

    public void clearShootOut() {
        background.delete();
        hoop.delete();
        ball.delete();
        michaelJordan.getAim().clearAim();
        inputHandler.removeKeyPressedEventsShootOut();
        field.getScoreboard().delete();
        field.getScoreDisplay().delete();
    }

    public void updateScoreDisplay() {
        // Text object cannot be updated and needs to be created with new value everytime score changes
        field.setScoreDisplay(new Text(Game.PADDING, Game.PADDING, String.valueOf(field.getScore())));
        field.getScoreDisplay().translate((Game.screenWidth / 2) - 100, 18);
        field.getScoreDisplay().grow(10, 10);
    }

}
