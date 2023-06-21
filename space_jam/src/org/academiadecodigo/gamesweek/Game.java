package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.*;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.gamesweek.shootout.ShootOut.shoot;

public class Game {

    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int CELL_SIZE=50;
    private Picture backgroundImage;
    private int delay;
    private Monstar[] monstar;
    private int numAdversaries; //5 max
    private Ball ball;
    private HoopPosition hoop;
    private MichaelJordan player;
    public static double BALL_SIZE=CELL_SIZE/2;
    private int stepSize = 10;
    public int score = 0;


    // public boolean playerHasBall = false;

    public static int SHOOTOUT_CELL_SIZE = 10;


    //CONSTRUCTOR
    public Game(double width, double height, int delay, int numAdversaries){
        this.screenWidth=width;
        this.screenHeight=height;
        this.delay=delay;
        this.numAdversaries = numAdversaries;
    }

    //Create the Monstars
    private void createAdversaries(){

        monstar = new Monstar[numAdversaries];

        for(int i=0; i<numAdversaries;i++){
            monstar[i] = MonstarFactory.createMonstar(i);
        }
    }

    //Checks the conditions for the Monstars to move
    private void moveMonstars(){

        for (int i=0; i<numAdversaries; i++){
            if(monstar[i].getCurrentSteps()>Monstar.MAX_STEPS) {
                monstar[i].chooseDirection();
                monstar[i].resetCurrentSteps();
            }

            while (monstar[i].hitsBorder())
                monstar[i].chooseDirection();

            monstar[i].move();
            monstar[i].takeAStep();

        }
    }

    //Initializes all the objects essential to the game
    public void init(){

        backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");

        double ballX = StartingPositions.POSITION_6.getPosition().getX();
        double ballY = StartingPositions.POSITION_6.getPosition().getY();
        double innerSquareSide = Math.sqrt(Math.pow(BALL_SIZE,2)/2);
        Position innerSquarePosition = new Position(ballX+(BALL_SIZE-innerSquareSide)/2,ballY+(BALL_SIZE-innerSquareSide)/2);

        ball = new Ball(new Picture(ballX, ballY,"resources/ball.png"),innerSquarePosition,innerSquareSide);

        double MJX = StartingPositions.POSITION_7.getPosition().getX();
        double MJY = StartingPositions.POSITION_7.getPosition().getY();

        this.player = new MichaelJordan(new Picture(MJX,MJY,"resources/MJ_small.png"),StartingPositions.POSITION_7.getPosition(),Direction.RIGHT);

        new Handler(player);

        int hoopSize = 40;

        Position hoopMaxPosition = new Position(StartingPositions.POSITION_8.getPosition().getX()+hoopSize,StartingPositions.POSITION_8.getPosition().getY()+hoopSize);
        hoop = new HoopPosition(StartingPositions.POSITION_8.getPosition(),hoopMaxPosition);

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
    }

    //Start and run game
    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);


            if(player.overlaps(hoop)){
                //Go to shootout
                clearField();
                shoot(this);
            }
            else {
                moveMonstars();

                if(ball.isFollowing()){
                    ball.moveBall();
                }
            }
        }
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore(){
        return score;
    }

}
