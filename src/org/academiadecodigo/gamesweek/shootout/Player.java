package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Position;
import java.util.concurrent.TimeUnit;

/* TO DO
 *
 * Player HAS-A shootOut doesn't seem right. Try to find a better way to organize methods player.aim(), player.shoot() and aim.move()
 */

public class Player {

    private Position shot;
    private Aim aim;
    private ShootOut shootOut;

    public Aim getAim() {
        return aim;
    }

    public void aim(Hoop hoop, ShootOut shootOut){
        this.shootOut = shootOut;
        this.aim = new Aim(hoop);

        new InputHandler(this);
        TimerClock timerClock = new TimerClock(10);

        timerClock.start();

        while (shot == null){

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
    }

    public void shoot() {

        shot = aim.getPos();
        shootOut.score(shot);

    }


}
