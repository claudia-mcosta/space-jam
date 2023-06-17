package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int PADDING=10;
    public static int screenWidth;
    public static int screenHeight;
    private int delay;

    private Picture test;

    //CONSTRUCTOR
    public Game(int width, int height, int delay){
        this.screenWidth=width;
        this.screenHeight=height;
        this.delay=delay;
    }

    public void init(){

        Rectangle background = new Rectangle(PADDING,PADDING,screenWidth,screenHeight);
        background.draw();

        Picture backgroundImage = new Picture(PADDING,PADDING,"resources/pixelCourt.png");
        backgroundImage.draw();

        Ellipse ball = new Ellipse(screenWidth/2-17,screenHeight/2-17,50,50);
        ball.setColor(Color.ORANGE);
        ball.fill();

        Player player = new Player(new Picture(screenWidth/3,screenHeight/2, "resources/MJ_small.png"));

        new Handler(player);
    }

    public void start(){

    }
}
