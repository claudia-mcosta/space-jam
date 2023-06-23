package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.*;
import org.academiadecodigo.gamesweek.gameObjects.Character;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.gamesweek.shootout.InputHandler;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.gamesweek.shootout.ShootOut.shoot;

public class Game {
    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int CELL_SIZE=50;
    private Picture backgroundImage;
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
                monstar[i].chooseDirection(monstarBall(),ball, leftHoop);
                monstar[i].resetCurrentSteps();
            }

            while (monstar[i].hitsBorder())
                monstar[i].chooseDirection(monstarBall(),ball, leftHoop);

            monstar[i].move();
            monstar[i].takeAStep();
            monstar[i].ballCollision(ball);

        }
    }

    //Initializes all the objects essential to the game
    public void init(){

        backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");

        Position ballPosition = new Position(StartingPositions.POSITION_6);
        ball = new Ball(new Picture(ballPosition.getX(), ballPosition.getY(),"resources/ball.png"));

        Position MJPosition = new Position(StartingPositions.POSITION_7);
        this.player = new MichaelJordan(new Picture(MJPosition.getX(), MJPosition.getY(),"resources/MJ_small.png"),StartingPositions.POSITION_7,Direction.RIGHT);

        this.inputHandler = new InputHandler(player,ball);

        inputHandler.createKeyPressedEventsGame();
        inputHandler.createKeyReleasedEvents();

        int hoopSize = 40;

        Position hoopMaxPosition = new Position(StartingPositions.POSITION_8.getPosition().getX()+hoopSize,StartingPositions.POSITION_8.getPosition().getY()+hoopSize);
        rightHoop = new HoopPosition(StartingPositions.POSITION_8.getPosition(),hoopMaxPosition);
        leftHoop = new HoopPosition(StartingPositions.POSITION_11.getPosition(),hoopMaxPosition);

        createAdversaries();

        initDraw();
    }

    //Draws all the pictures on canvas
    public void initDraw(){
        backgroundImage.draw();
        ball.draw();
        player.draw();
        player.translateFrom(player.getPosition(), StartingPositions.POSITION_7.getPosition());
        player.setPosition(StartingPositions.POSITION_7.getPosition());


        for(int i=0; i<monstar.length;i++){
            Position picturePosition = new Position(monstar[i].getPicture().getX(),monstar[i].getPicture().getY());
            monstar[i].draw();
            monstar[i].translateFrom(picturePosition,StartingPositions.values()[i+1].getPosition());
            monstar[i].setPosition(StartingPositions.values()[i+1].getPosition());

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
    }

    //Start and run game
    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);

            if(player.overlaps(rightHoop)){
                //Go to shootout
                clearField();
                shoot(this);
            }
            else {
                //moveMonstars();
            }
        }
    }
}
