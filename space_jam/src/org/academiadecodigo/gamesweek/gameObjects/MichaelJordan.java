package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.gameObjects.Character;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.gamesweek.shootout.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.concurrent.TimeUnit;

public class MichaelJordan extends Character {

    private Position shot;
    private Aim aim;
    private ShootOut shootOut;
    private int numShots;

    public MichaelJordan(Picture picture, StartingPositions position, Direction direction){
        super(picture, position,direction);
        picture.grow(12.5,12.5);
    }

    public Aim getAim() {
        return aim;
    }

    public void aim(Hoop hoop, ShootOut shootOut){
        this.shootOut = shootOut;
        this.shot = new Position(-1, -1);
        this.aim = new Aim(hoop);
        this.numShots = 0;

        //new InputHandler(this);
        TimerClock timerClock = new TimerClock(10);

        timerClock.start();

        while (shot.getX() == -1){

            //long timeLeft = timerClock.getEndTime() - timerClock.getTimeSinceStartInSeconds();
            //Timer for 10 seconds;
            if(timerClock.getTimeLeft() > 0){
                aim.move();
                System.out.println(timerClock.getTimeLeft() - 1);

                //Thread sleep to slow the aim;
                //Try catch to handle a possible exception;
                try {
                    TimeUnit.MILLISECONDS.sleep(15);
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong");
                }
            }
            else {
                timerClock.stop();
                System.out.println("time finished.");
                break;
            }
        }

        shootOut.score(shot);
    }

    public void shoot() {

        shot = aim.getPos();

    }


}
