package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.shootout.ShootOut;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Game g = new Game(1250,750,50, 5);
        //ShootOut shootOut = new ShootOut();

        g.init();
        g.start();
    }
}