package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.Ball;
import org.academiadecodigo.gamesweek.gameObjects.MichaelJordan;
import org.academiadecodigo.gamesweek.gameObjects.Monstar;
import org.academiadecodigo.gamesweek.gameObjects.MonstarFactory;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int CELL_SIZE=50;
    private int delay;
    private Monstar[] monstar;
    private int numAdversaries; //5 max
    private Ball ball;
    private MichaelJordan player;
    private Position ballPosition;
    public static double BALL_SIZE=CELL_SIZE/2;
    private int stepSize = 10;
    private int probOfChange = 5; //0-10 where 10 it never changes direction and 0 it changes every time

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
                monstar[i].chooseDirection(probOfChange);
                monstar[i].resetCurrentSteps();
            }

            while (monstar[i].hitsBorder())
                monstar[i].chooseDirection(probOfChange);

            monstar[i].move(ballPosition);
            monstar[i].takeAStep();

        }
    }

    public void init(){

        Picture backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");
        backgroundImage.draw();

        ball = new Ball(new Picture(StartingPositions.POSITION_6.getPosition().getX(),StartingPositions.POSITION_6.getPosition().getY(),"resources/ball.png"));

        Picture MJ = new Picture(StartingPositions.POSITION_7.getPosition().getX(),StartingPositions.POSITION_7.getPosition().getY(),"resources/MJ_small.png");
        this.player = new MichaelJordan(MJ,Direction.RIGHT);

        new Handler(player);

        createAdversaries();

    }

    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);

            moveMonstars();

            if(player.isAtHoop()){
                System.out.println("I'm at the hoop");
                System.out.println(player.getDirection());
                //Go to shootout
            }
            else {
                System.out.println("Not at the hoop");
                System.out.println(player.getDirection());
            }
        }

    }
}
