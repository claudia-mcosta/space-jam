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
    private int monstarScore;
    private Picture[] scoreDisplay = new Picture[2]; // Text scoreDisplay;
    private Picture[][] scoreNumbers = new Picture[10][10];
    private Picture[] monstarScoreDisplay = new Picture[2]; // Text scoreDisplay;
    private Picture[][] monstarScoreNumbers = new Picture[10][10];
    private ShootOut shootOut;


    //public boolean playerHasBall = false;

    public static int SHOOTOUT_CELL_SIZE = 10;


    //CONSTRUCTOR
    public Game(double width, double height, int delay, int numAdversaries){
        this.screenWidth=width;
        this.screenHeight=height;
        this.delay=delay;
        this.numAdversaries = numAdversaries;
        this.score=0;
        this.monstarScore=0;
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

                if(monstar[i].hasBall()){
                    //if monstar has ball go to the left
                    for (int j=0;j< monstar.length;j++){
                        monstar[j].setDirection(Direction.LEFT);
                    }
                }
        }
    }

    //Initializes all the objects essential to the game
    public void init(){

        backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");

        scoreboard = new Picture(Game.PADDING, Game.PADDING,"resources/scoreboard.png");
        scoreboard.translate((Game.screenWidth - scoreboard.getWidth()) / 2, 0);

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

        for (int i = 0; i < monstarScoreNumbers.length; i++) {
            for (int j = 0; j < monstarScoreNumbers[i].length; j++) {
                monstarScoreNumbers[i][j] = new Picture(Game.PADDING, Game.PADDING, "resources/score" + j + ".png");
                monstarScoreNumbers[i][j].grow(-3,-5);
                if (i == 0) {
                    monstarScoreNumbers[i][j].translate(725, 8 - 5);
                } else {
                    monstarScoreNumbers[i][j].translate(697, 8 - 5);
                }
            }
        }

        scoreDisplay[0] = scoreNumbers[0][0];
        scoreDisplay[1] = scoreNumbers[1][0];

        monstarScoreDisplay[0] = monstarScoreNumbers[0][0];
        monstarScoreDisplay[1] = monstarScoreNumbers[1][0];

        Position ballPosition = new Position(StartingPositions.POSITION_6);
        ball = new Ball(new Picture(ballPosition.getX(), ballPosition.getY(),"resources/ball.png"));

        Position MJPosition = new Position(StartingPositions.POSITION_7);
        this.player = new MichaelJordan(new Picture(MJPosition.getX(), MJPosition.getY(),"resources/MJ_small.png"),StartingPositions.POSITION_7,Direction.RIGHT, monstar);

        this.inputHandler = new InputHandler(player,ball);

        inputHandler.createKeyPressedEventsGame();
        inputHandler.createKeyReleasedEvents();

        int hoopSize = 50;

        Position rightHoopMaxPosition = new Position(StartingPositions.POSITION_8.getPosition().getX()+hoopSize,StartingPositions.POSITION_8.getPosition().getY()+hoopSize);
        Position leftHoopMaxPosition = new Position(StartingPositions.POSITION_11.getPosition().getX()+hoopSize,StartingPositions.POSITION_11.getPosition().getY()+hoopSize);
        rightHoop = new HoopPosition(StartingPositions.POSITION_8.getPosition(),rightHoopMaxPosition);
        leftHoop = new HoopPosition(StartingPositions.POSITION_11.getPosition(),leftHoopMaxPosition);

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

        monstarScoreDisplay[0].draw();
        monstarScoreDisplay[1].draw();

        ball.reCenter();
        ball.draw();

        player.reCenter(StartingPositions.POSITION_7,player.getWidth(),player.getHeight());
        player.draw();

        for(int i=0; i<monstar.length;i++){
            monstar[i].reCenter(StartingPositions.values()[i+1],monstar[i].getWidth(),monstar[i].getHeight());
            monstar[i].draw();
        }
    }

    public void resetObjectPositions(){
        ball.reCenter();
        player.reCenter(StartingPositions.POSITION_7,player.getWidth(),player.getHeight());
        for(int i=0; i<monstar.length;i++){
            monstar[i].reCenter(StartingPositions.values()[i+1],monstar[i].getWidth(),monstar[i].getHeight());
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
        monstarScoreDisplay[0].delete();
        monstarScoreDisplay[1].delete();

    }

    //Start and run game
    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);

            if(inputHandler.getGameStart()) {

                if (player.overlaps(rightHoop) && player.hasBall()) {
                    //Go to shootout

                    //initDraw();

                    clearField();
                    shootOut.shoot();
                } else {
                    moveMonstars();

                    for(int i=0;i< monstar.length;i++){
                        if(monstar[i].overlaps(leftHoop) && monstar[i].hasBall()){
                            monstarScore += 2;
                            //point for monstars
                            //Update display
                            System.out.println("monstar score = "+monstarScore);
                            shootOut.updateMonstarScoreDisplay();
                            resetObjectPositions();
                        }
                    }
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
            Picture picture = new Picture(PADDING,PADDING,"resources/hoop.png");
            this.hoop = new Hoop(picture);
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
            monstarScoreDisplay[0].draw();
            monstarScoreDisplay[1].draw();

            inputHandler.createKeyPressedEventsShootOut();

        }

        private void start() {
            calculateScore(player.aim());
        }

        private void calculateScore(Position shot) {

            int points = 0;

            int rimStart = 144;
            int rimEnd = 234;

            //System.out.println("Shout x= "+(shot.getX()+player.getAim().getAimCenter()));
            //System.out.println("Rim limits: "+(hoop.getTarget().getX()+144)+" : "+(hoop.getTarget().getX()+234));
            // Calculate points from shot and add it to the overall score
            //if ((shot.getX() + player.getAim().getAimCenter()) >= hoop.getTarget().getX() && (shot.getX() + player.getAim().getAimCenter()) <= hoop.getTarget().getMaxX()) {
            if ((shot.getX() + player.getAim().getAimCenter()) >= hoop.getTarget().getX()+rimStart && (shot.getX() + player.getAim().getAimCenter()) <= hoop.getTarget().getX()+rimEnd) {
                    points = 2;
                //System.out.println("Point!");
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

            if (score > 99) {
                // Reset score
                score = 0;
            }

            int scoreUnit = Math.abs(score % 10);
            int scoreDecimal = Math.abs((score / 10) % 10);

            scoreDisplay[0] = scoreNumbers[0][scoreUnit];
            scoreDisplay[1] = scoreNumbers[1][scoreDecimal];
        }

        private void updateMonstarScoreDisplay() {
            /*
            // Text object cannot be updated and needs to be created with new value everytime score changes
            scoreDisplay = new Text(PADDING, PADDING, String.valueOf(score));
            scoreDisplay.translate((screenWidth / 2) - 100, 18);
            scoreDisplay.grow(10, 10);
            */

            if (monstarScore > 99) {
                // Reset score
                monstarScore = 0;
            }

            int scoreUnit = Math.abs(monstarScore % 10);
            int scoreDecimal = Math.abs((monstarScore / 10) % 10);

            monstarScoreDisplay[0] = monstarScoreNumbers[0][scoreUnit];
            monstarScoreDisplay[1] = monstarScoreNumbers[1][scoreDecimal];

            monstarScoreDisplay[0].draw();
            monstarScoreDisplay[1].draw();
        }

        private void clearShootOut() {
            background.delete();
            hoop.delete();
            ball.delete();
            scoreboard.delete();
            scoreDisplay[0].delete();
            scoreDisplay[1].delete();
            monstarScoreDisplay[0].delete();
            monstarScoreDisplay[1].delete();
            inputHandler.removeKeyPressedEventsShootOut();
            inputHandler.createKeyPressedEventsGame();
            inputHandler.resetGameStart();
        }

    }

}
