package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.*;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.gamesweek.shootout.Hoop;
import org.academiadecodigo.gamesweek.shootout.InputHandler;
import org.academiadecodigo.simplegraphics.graphics.Text;
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
    private Text scoreDisplay;
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

            if(!monstar[i].characterCollision(player)) {
                monstar[i].move();
                monstar[i].takeAStep();
                monstar[i].ballCollision(ball);
            }else {
                monstar[i].setDirection(monstar[i].getDirection().returnOppositeDirection());
                monstar[i].move();
                monstar[i].takeAStep();
                monstar[i].ballCollision(ball);

            }
        }
    }

    //Initializes all the objects essential to the game
    public void init(){

        backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");

        scoreboard = new Picture(Game.PADDING, Game.PADDING,"resources/scoreboard.png");
        scoreboard.translate((Game.screenWidth - scoreboard.getWidth()) / 2, 0);
        scoreDisplay =  new Text(Game.PADDING, Game.PADDING, String.valueOf(score));
        scoreDisplay.translate((Game.screenWidth / 2) - 100, 18);
        scoreDisplay.grow(10, 10);

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
        scoreDisplay.draw();

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
        //inputHandler.removeKeyPressedEventsGame();

        scoreboard.delete();
        scoreDisplay.delete();

    }

    //Start and run game
    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);

            if(player.overlaps(rightHoop)){
                //Go to shootout

                //initDraw();

                clearField();
                shootOut.shoot();
            }
            else {
                moveMonstars();
            }
        }
    }

    public class ShootOut {

        private Picture background;
        private Hoop hoop;
        private Picture ball;

        private ShootOut() {
            this.background = new Picture(PADDING, PADDING,"resources/bleachers.jpeg");
            this.hoop = new Hoop(screenWidth / 2, screenHeight / 2);
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
            scoreDisplay.draw();

            inputHandler.createKeyPressedEventsShootOut();

        }

        private void start() {
            calculateScore(player.aim());
        }

        private void calculateScore(Position shot) {

            int points = 0;

            // Calculate points from shot and add it to the overall score
            if (shot.getX() + ((double) player.getAim().getAimSize() / 2) >= hoop.getTarget().getX() && shot.getX() + ((double) player.getAim().getAimSize() / 2) <= (hoop.getTarget().getWidth() + hoop.getTarget().getX())) {
                points = 3;
            }

            score += points;
            updateScoreDisplay();

            clearShootOut();
            initDraw();
        }


        private void shoot() {
            init();
            start();
        }

        private void clearShootOut() {
            background.delete();
            hoop.delete();
            ball.delete();
            scoreboard.delete();
            scoreDisplay.delete();
            inputHandler.removeKeyPressedEventsShootOut();
        }

        private void updateScoreDisplay() {
            // Text object cannot be updated and needs to be created with new value everytime score changes
            scoreDisplay = new Text(PADDING, PADDING, String.valueOf(score));
            scoreDisplay.translate((screenWidth / 2) - 100, 18);
            scoreDisplay.grow(10, 10);
        }

    }

}
