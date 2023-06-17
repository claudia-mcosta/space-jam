package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class ShootOut {

    Picture background;


    Rectangle rectangle;

    public void init(){

        Picture background = new Picture(Game.PADDING, Game.PADDING, "resources/bleachers.jpeg");
        background.draw();
        Picture hoop = new Picture(10,10,"resources/hoop.png");
        //hoop.grow(-2000,-2000);
        //hoop.draw();
        Picture ball = new Picture(10,10,"resources/ball.png");
        //ball.grow(-380,-380);
        ball.draw();
        //ball.translate(-150,-100);



    }

    public static void main(String[] args)
    {
        ShootOut shootOut = new ShootOut();

        shootOut.init();

    }



}
