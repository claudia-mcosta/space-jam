package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int PADDING=10;
    public static double screenWidth;
    public static double screenHeight;
    public static int cellSize=50;
    private int delay;
    private Monstar[] monstars;
    private int numAdversaries=5;


    //CONSTRUCTOR
    public Game(double width, double height, int delay){
        this.screenWidth=width;
        this.screenHeight=height;
        this.delay=delay;
    }

    private void createAdversaries(){

        monstars = new Monstar[numAdversaries];

        for(int i=0; i<numAdversaries;i++){
            monstars[i] = new Monstar();
            monstars[i].setPicture(new Picture(0,0,"resources/monstar"+(i+1)+".png"));
        }
    }

    private void drawAdversaries(){

        for(int i=0;i<numAdversaries;i++){
            monstars[i].translate(StartingPositions.values()[i].getPosition());
            monstars[i].draw();
        }
    }

    public void init(){


        Rectangle background = new Rectangle(PADDING,PADDING,screenWidth,screenHeight);
        background.draw();

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

    public void start(){

    }
}
