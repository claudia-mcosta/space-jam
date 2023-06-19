package org.academiadecodigo.gamesweek;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Game g = new Game(1250,750,100, 2);

        g.init();
        g.start();
    }
}