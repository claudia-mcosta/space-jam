package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.*;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int CELL_SIZE=50;
    private Picture backgroundImage;
    private Picture ballPicture;
    private Picture MJPicture;
    private int delay;
    private Monstar[] monstar;
    private int numAdversaries; //5 max
    private Ball ball;
    private Hoop hoop;
    private MichaelJordan player;
    private Position ballPosition;
    public static double BALL_SIZE=CELL_SIZE/2;
    private int stepSize = 10;


    //public boolean playerHasBall = false;


    //CONSTRUCTOR
    public Game(double width, double height, int delay, int numAdversaries){
        this.screenWidth=width;
        this.screenHeight=height;
        this.delay=delay;
        this.numAdversaries = numAdversaries;
    }

    private void createAdversaries(){

        monstar = new Monstar[numAdversaries];

        for(int i=0; i<numAdversaries;i++){
            monstar[i] = MonstarFactory.createMonstar(i);
        }
    }

    private void moveMonstars(){

        for (int i=0; i<numAdversaries; i++){
            if(monstar[i].getCurrentSteps()>Monstar.MAX_STEPS) {
                monstar[i].chooseDirection();
                monstar[i].resetCurrentSteps();
            }

            while (monstar[i].hitsBorder())
                monstar[i].chooseDirection();

            monstar[i].move(ballPosition);
            monstar[i].takeAStep();

        }
    }

    public void init(){

        backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");

        double ballX = StartingPositions.POSITION_6.getPosition().getX();
        double ballY = StartingPositions.POSITION_6.getPosition().getY();

        ball = new Ball(new Picture(ballX, ballY,"resources/ball.png"));

        double MJX = StartingPositions.POSITION_7.getPosition().getX();
        double MJY = StartingPositions.POSITION_7.getPosition().getY();

        this.player = new MichaelJordan(new Picture(MJX,MJY,"resources/MJ_small.png"),StartingPositions.POSITION_7.getPosition(),Direction.RIGHT);

        new Handler(player);

        int hoopSize = 40;

        Position hoopMaxPosition = new Position(StartingPositions.POSITION_8.getPosition().getX()+hoopSize,StartingPositions.POSITION_8.getPosition().getY()+hoopSize);
        hoop = new Hoop(StartingPositions.POSITION_8.getPosition(),hoopMaxPosition);

        createAdversaries();

        initDraw();

    }

    private void initDraw(){
        backgroundImage.draw();
        ball.draw();
        player.draw();

        for(int i=0; i<monstar.length;i++){
            monstar[i].translateTo(monstar[i].getPosition(),StartingPositions.values()[i+1].getPosition());
            monstar[i].draw();
        }

    }

    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);


            if(player.overlaps(hoop)){
                //Go to shootout

                ball.getPicture().delete();
            }
            else {
                moveMonstars();
                ball.getPicture().draw();
            }
        }

    }
}
