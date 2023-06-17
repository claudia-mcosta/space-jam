package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Game;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.util.ArrayList;

public class ShootOut {

    private Canvas canvas;
    private Picture background;
    private Picture hoop;
    private Picture ball;

    public ShootOut() {
        this.canvas = Canvas.getInstance();
        this.background = new Picture(Game.PADDING,Game.PADDING,"space_jam\\resources\\bleachers.jpeg");
        this.hoop = new Picture(Game.PADDING,Game.PADDING,"space_jam\\resources\\hoop.png");
        this.ball = new Picture(Game.PADDING,Game.PADDING,"space_jam\\resources\\ball.png");
    }

    public void init(){

        // Test to hide objects that are outside the canvas bounds. NOT WORKING!!
        //JFrame frame = new JFrame("FrameDemo");
        //frame.pack();
        //frame.setVisible(true);
        Rectangle shape = new Rectangle(Game.PADDING, Game.PADDING, 1250, 750);
        canvas.show(shape);

        // Reposition, resize and show background image (change 1250 and 750 to Game.getWidth() and Game.getHeight())
        background.translate((double) (1250 - background.getWidth()) / 2, (double) (750 - background.getHeight()) / 2);
        background.grow((double) (1250 - background.getWidth()) / 2, (double) (750 - background.getHeight()) / 2);
        background.draw();

        // Center and draw hoop (change 1250 and 750 to Game.getWidth() and Game.getHeight())
        hoop.translate((double) (1250 - hoop.getWidth()) / 2, (double) (750 - hoop.getHeight()) / 2);
        hoop.draw();

        // Reposition, resize and show background image
        ball.translate((double) 1250 / 2,500);
        // ball.grow(-120,-120);
        canvas.show(ball);



    }

    public static void main(String[] args)
    {
        ShootOut shootOut = new ShootOut();

        shootOut.init();

    }



}
