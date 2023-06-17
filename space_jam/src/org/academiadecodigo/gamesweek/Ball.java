package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Ball {

    private Ellipse ball;

    public Ball(Ellipse ball){
        this.ball = ball;
        ball.fill();
        ball.setColor(Color.ORANGE);
    }

    public void movementBall(){
        ball.translate(10,0);
    }




}
