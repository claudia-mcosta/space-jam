package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.gamesweek.Position;

/* TO DO
 *
 * Player HAS-A shootOut doesn't seem right. Try to find a better way to organize methods player.aim(), player.shoot() and aim.move()
 */

public class Player {

    private Position shot;
    private Aim aim;
    private ShootOut shootOut;


    public void aim(Hoop hoop, ShootOut shootOut){
        this.shootOut = shootOut;
        this.aim = new Aim(hoop);

        new InputHandler(this);

        while (shot == null) {
            aim.move();
        }

    }

    public void shoot() {

        shot = aim.getPos();
        shootOut.score(shot);

    }


}
