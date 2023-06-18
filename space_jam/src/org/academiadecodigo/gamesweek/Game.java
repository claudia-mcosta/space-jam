package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int cellSize=50;
    private int delay;
    private Monstar[] monstar;
    private int numAdversaries; //5 max
    private Ellipse ball;
    private Position ballPosition;
    public static double BALL_SIZE=cellSize/2;
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
            Picture picture = new Picture(0,0,"resources/monstar"+(i+1)+".png");
            monstar[i] = new Monstar(StartingPositions.values()[i+1],picture);
        }
    }

    private void drawAdversaries(){

        for(int i=0;i<numAdversaries;i++){
            monstar[i].translateTo(StartingPositions.POSITION_0.getPosition(),StartingPositions.values()[i+1].getPosition());
            monstar[i].draw();
        }
    }

    private void moveUp(){
        ballPosition.translatePosition(0,-stepSize);
        ball.translate(0,-stepSize);
    }
    private void moveUpRight(){
        ballPosition.translatePosition(stepSize,-stepSize);
        ball.translate(stepSize,-stepSize);
    }
    private void moveRight(){
        ballPosition.translatePosition(stepSize,0);
        ball.translate(stepSize,0);
    }
    private void moveDownRight(){
        ballPosition.translatePosition(stepSize,stepSize);
        ball.translate(stepSize,stepSize);
    }
    private void moveDown(){
        ballPosition.translatePosition(0,stepSize);
        ball.translate(0,stepSize);
    }
    private void moveDownLeft(){
        ballPosition.translatePosition(-stepSize,stepSize);
        ball.translate(-stepSize,stepSize);
    }
    private void moveLeft(){
        ballPosition.translatePosition(-stepSize,0);
        ball.translate(-stepSize,0);
    }
    private void moveUpLeft(){
        ballPosition.translatePosition(-stepSize,-stepSize);
        ball.translate(-stepSize,-stepSize);
    }

    private void moveBall(Direction direction){

        switch (direction){
            case UP:
                moveUp();
                break;
            case UP_RIGHT:
                moveUpRight();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN_RIGHT:
                moveDownRight();
                break;
            case DOWN:
                moveDown();
                break;
            case DOWN_LEFT:
                moveDownLeft();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP_LEFT:
                moveUpLeft();
                break;
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

            monstar[i].moveRandom(ballPosition);

            if(!monstar[i].hasBall()){
                monstar[i].tryStealBall(ballPosition);
            }else{
                moveBall(monstar[i].getDirection());
            }
        }
    }

    public void init(){

        //Rectangle background = new Rectangle(PADDING,PADDING,screenWidth,screenHeight);
        //background.draw();

        Picture backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");
        backgroundImage.draw();

        ballPosition = new Position(StartingPositions.POSITION_6);
        ball = new Ellipse(ballPosition.getX(), ballPosition.getY(), BALL_SIZE, BALL_SIZE);
        ball.setColor(Color.ORANGE);
        ball.fill();

        Picture MJ = new Picture(screenWidth/3,screenHeight/2,"resources/MJ_small.png");
        MJ.grow(12.5,12.5);
        MJ.draw();

        createAdversaries();
        drawAdversaries();

    }

    public void start() throws InterruptedException {

        while (true){

            Thread.sleep(delay);

            moveMonstars();

        }

    }
}
