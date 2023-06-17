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

    private void moveMonstars(){

        for (int i=0; i<numAdversaries; i++){
            if(monstar[i].getCurrentSteps()>Monstar.MAX_STEPS) {
                monstar[i].newDirection();
                monstar[i].resetCurrentSteps();
            }
            monstar[i].moveRandom();
        }
    }

    public void init(){

        //Rectangle background = new Rectangle(PADDING,PADDING,screenWidth,screenHeight);
        //background.draw();

        Picture backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");
        backgroundImage.draw();

        Ellipse ball = new Ellipse(screenWidth/2,screenHeight/2,cellSize/2,cellSize/2);
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
