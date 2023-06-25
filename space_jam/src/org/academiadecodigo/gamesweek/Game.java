package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.*;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.gamesweek.shootout.Hoop;
import org.academiadecodigo.gamesweek.shootout.InputHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int CELL_SIZE=50;
    private Picture backgroundImage;
    private Picture scoreboard;
    public static int delay;
    private Monstar[] monstar;
    private int numAdversaries; //5 max
    private Ball ball;
    private HoopPosition rightHoop;
    private HoopPosition leftHoop;
    private MichaelJordan player;
    public static double BALL_SIZE=CELL_SIZE/2;
    private int stepSize = 10;
    private InputHandler inputHandler;
    private int score;
    private Picture[] scoreDisplay = new Picture[2]; // Text scoreDisplay;
    private Picture[][] scoreNumbers = new Picture[10][10];
    private ShootOut shootOut;


    //public boolean playerHasBall = false;

    public static int SHOOTOUT_CELL_SIZE = 10;


    //CONSTRUCTOR
    public Game(double width, double height, int delay, int numAdversaries){
        this.screenWidth=width;
        this.screenHeight=height;
        this.delay=delay;
        this.numAdversaries = numAdversaries;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public MichaelJordan getPlayer(){return player;}

    //Create the Monstars
    private void createAdversaries(){

        monstar = new Monstar[numAdversaries];

        for(int i=0; i<numAdversaries;i++){
            monstar[i] = MonstarFactory.createMonstar(i);
        }
        player.setMonstars(monstar);
    }

    private boolean monstarBall(){

        for(Monstar monstar:monstar){
            if(monstar.hasBall())
                return true;
        }
        return false;
    }

    //Checks the conditions for the Monstars to move
    private void moveMonstars(){

        for (int i=0; i<numAdversaries; i++){
            if(monstar[i].getCurrentSteps()>Monstar.MAX_STEPS) {
                //monstar[i].chooseObjectDirection(monstarBall(),ball, leftHoop);
                monstar[i].chooseRandomDirection(monstarBall(),ball, leftHoop);
                monstar[i].resetCurrentSteps();
            }

            while (monstar[i].hitsBorder())
                monstar[i].chooseRandomDirection(monstarBall(),ball, leftHoop);

                monstar[i].move();
                monstar[i].takeAStep();
                monstar[i].ballCollision(ball);
        }
    }

    //Initializes all the objects essential to the game
    public void init(){

        backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");

        scoreboard = new Picture(Game.PADDING, Game.PADDING,"resources/scoreboard.png");
        scoreboard.translate((Game.screenWidth - scoreboard.getWidth()) / 2, 0);
        //scoreDisplay =  new Text(Game.PADDING, Game.PADDING, String.valueOf(score));
        //scoreDisplay.translate((Game.screenWidth / 2) - 100, 18);
        //scoreDisplay.grow(10, 10);

        for (int i = 0; i < scoreNumbers.length; i++) {
            for (int j = 0; j < scoreNumbers[i].length; j++) {
                scoreNumbers[i][j] = new Picture(Game.PADDING, Game.PADDING, "resources/score" + j + ".png");
                scoreNumbers[i][j].grow(-3,-5);
                if (i == 0) {
                    scoreNumbers[i][j].translate((Game.screenWidth / 2) - 101, 8 - 5);
                } else {
                    scoreNumbers[i][j].translate((Game.screenWidth / 2) - 129, 8 - 5);
                }
            }
        }

        scoreDisplay[0] = scoreNumbers[0][0];
        scoreDisplay[1] = scoreNumbers[1][0];

        Position ballPosition = new Position(StartingPositions.POSITION_6);
        ball = new Ball(new Picture(ballPosition.getX(), ballPosition.getY(),"resources/ball.png"));

        Position MJPosition = new Position(StartingPositions.POSITION_7);
        this.player = new MichaelJordan(new Picture(MJPosition.getX(), MJPosition.getY(),"resources/MJ_small.png"),StartingPositions.POSITION_7,Direction.RIGHT, monstar);

        this.inputHandler = new InputHandler(player,ball);

        inputHandler.createKeyPressedEventsGame();
        inputHandler.createKeyReleasedEvents();

        int hoopSize = 40;

        Position hoopMaxPosition = new Position(StartingPositions.POSITION_8.getPosition().getX()+hoopSize,StartingPositions.POSITION_8.getPosition().getY()+hoopSize);
        rightHoop = new HoopPosition(StartingPositions.POSITION_8.getPosition(),hoopMaxPosition);
        leftHoop = new HoopPosition(StartingPositions.POSITION_11.getPosition(),hoopMaxPosition);

        createAdversaries();

        initDraw();

        shootOut = new ShootOut();
    }

    //Draws all the pictures on canvas
    public void initDraw(){
        backgroundImage.draw();

        scoreboard.draw();
        scoreDisplay[0].draw();
        scoreDisplay[1].draw();

        ball.reCenter();
        ball.draw();

        player.reCenter(StartingPositions.POSITION_7,player.getWidth(),player.getHeight());
        player.draw();

        for(int i=0; i<monstar.length;i++){
            monstar[i].reCenter(StartingPositions.values()[i+1],monstar[i].getWidth(),monstar[i].getHeight());
            monstar[i].draw();
        }
    }

    private void clearField(){
        ball.getPicture().delete();
        player.getPicture().delete();

        for(int i=0; i<monstar.length;i++){
            monstar[i].getPicture().delete();
        }
        backgroundImage.delete();
        inputHandler.removeKeyPressedEventsGame();

        scoreboard.delete();
        scoreDisplay[0].delete();
        scoreDisplay[1].delete();

    }

    //Start and run game
    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);

            if(inputHandler.gameStart) {

                if (player.overlaps(rightHoop) && player.hasBall()) {
                    //Go to shootout

                    //initDraw();

                    clearField();
                    shootOut.shoot();
                } else {
                    moveMonstars();
                }
            }
        }
    }

    public class ShootOut {

        private Picture background;
        private Hoop hoop;
        private Picture ball;

        private ShootOut() {
            this.background = new Picture(PADDING, PADDING,"resources/shootoutBackgroundDark.png");
            this.hoop = new Hoop(new Picture(PADDING, PADDING, "resources/hoop.png"));
            this.ball = new Picture(PADDING, PADDING,"resources/ball_shootout.png");
        }

        private void init(){

            // Reposition, resize and show background image (change 1250 and 750 to Game.getWidth() and Game.getHeight())
            background.translate((Game.screenWidth - background.getWidth()) / 2, (Game.screenHeight - background.getHeight()) / 2);
            background.grow((Game.screenWidth - background.getWidth()) / 2, (Game.screenHeight - background.getHeight()) / 2);
            background.draw();

            // Draw hoop image and target frame
            hoop.draw();

            // Reposition, resize and show ball image - Improve ball translate to be more dynamic in relation to screen size
            // ball.translate((Game.screenWidth / 2,500);
            // ball.draw();

            scoreboard.draw();
            scoreDisplay[0].draw();
            scoreDisplay[1].draw();

            inputHandler.createKeyPressedEventsShootOut();

        }

        private void start() {
            calculateScore(player.aim());
        }

        private void calculateScore(Position shot) {

            int points = 0;

            // Calculate points from shot and add it to the overall score
            if ((shot.getX() + player.getAim().getAimCenter()) >= hoop.getTarget().getX() && (shot.getX() + player.getAim().getAimCenter()) <= hoop.getTarget().getMaxX()) {
                points = 2;
            }

            score += points;

            clearShootOut();
            updateScoreDisplay();
            initDraw();
        }


        private void shoot() {
            init();
            start();
        }

        private void updateScoreDisplay() {
            /*
            // Text object cannot be updated and needs to be created with new value everytime score changes
            scoreDisplay = new Text(PADDING, PADDING, String.valueOf(score));
            scoreDisplay.translate((screenWidth / 2) - 100, 18);
            scoreDisplay.grow(10, 10);
            */

            if (score > 99) score = 0;

            int scoreUnit = Math.abs(score % 10);
            scoreDisplay[0] = scoreNumbers[0][scoreUnit];

            if (score >= 10) {
                int scoreDecimal = Math.abs((score / 10) % 10);
                scoreDisplay[1] = scoreNumbers[1][scoreDecimal];
            }

        }

        private void clearShootOut() {
            background.delete();
            hoop.delete();
            ball.delete();
            scoreboard.delete();
            scoreDisplay[0].delete();
            scoreDisplay[1].delete();
            inputHandler.removeKeyPressedEventsShootOut();
            inputHandler.createKeyPressedEventsGame();
            inputHandler.resetGameStart();
        }

    }

}
