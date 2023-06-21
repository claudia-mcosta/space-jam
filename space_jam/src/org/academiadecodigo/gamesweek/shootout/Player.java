package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.positions.Position;

import java.util.concurrent.TimeUnit;

/* TO DO
 *
 * Player HAS-A shootOut doesn't seem right. Try to find a better way to organize methods player.aim(), player.shoot() and aim.move()
 */

public class Player {

    private Position shot;
    private Aim aim;
    private ShootOut shootOut;
    private int numShots;



    public void aim(Hoop hoop, ShootOut shootOut){
        this.shootOut = shootOut;
        this.aim = new Aim(hoop);
        this.numShots=0;

        new InputHandler(this);

        while (shot == null) {

            if(numShots==2){

            }
            aim.move();

            //Thread sleep to slow the aim;
            //Try catch to handle a possible exception;
            try{
                TimeUnit.MILLISECONDS.sleep(15);
            } catch(InterruptedException e) {
                System.out.println("Something went wrong");
            }
        }

    }

    public void shoot() {

        shot = aim.getPos();
        System.out.println(shot.toString());
        shootOut.score(shot);
        numShots++;
    }


}
