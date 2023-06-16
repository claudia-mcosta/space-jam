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

        Ellipse ball = new Ellipse(screenWidth/2,screenHeight/2,50,50);
        ball.setColor(Color.ORANGE);
        ball.fill();

        test = new Picture(0,0,"/home/luis/Desktop/AC/SpaceJam/space_jam/resources/pngegg.png");

        Picture backgroundImage = new Picture(0,0,"/home/luis/Desktop/AC/SpaceJam/space_jam/resources/pixelCourt.png");
        backgroundImage.grow(-800,-300);
        backgroundImage.translate(10,10);

    }

    public void start(){

    }
}
