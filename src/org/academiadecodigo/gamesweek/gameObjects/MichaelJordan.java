package org.academiadecodigo.gamesweek.gameObjects;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.positions.Position;
import org.academiadecodigo.gamesweek.positions.StartingPositions;
import org.academiadecodigo.gamesweek.shootout.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.concurrent.TimeUnit;

import static org.academiadecodigo.gamesweek.positions.StartingPositions.POSITION_0;

public class MichaelJordan extends Character {

    private Position shot = POSITION_0.getPosition();
    private Aim aim = new Aim();
    private Monstar[] monstars;
    private final double PLAYER_MOVEMENT = 7;
    private final double PLAYER_DIAGONAL = Math.sqrt(Math.pow(PLAYER_MOVEMENT,2)/2);
    private TimerClock timerClock = new TimerClock(0,10);

    public MichaelJordan(Picture picture, StartingPositions position, Direction direction, Monstar[] monstars){
        super(picture, position,direction);
        picture.grow(12.5,12.5);

        this.monstars=monstars;
    }

    public boolean hasCollisions(){

        for(int i=0;i<monstars.length;i++){
            if(this.hitsBorder() /*|| this.characterCollision(monstars[i])*/){
                return true;
            }
        }
        return false;
    }

    public Aim getAim() {
        return aim;
    }

    public Position aim(){
        // Reset shot position
        shot.translatePosition(-shot.getX(), -shot.getY());

        aim.show();

        timerClock.init();
        timerClock.start();

        while (shot.getX() == 0) {

            // Update timer display
            timerClock.updateDisplay();

            if(timerClock.getTimeLeft() > 0){

                aim.move();

                //Thread sleep to slow the aim;
                //Try catch to handle a possible exception;
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong");
                }
            }
            else {
                break;
            }
        }

        timerClock.stop();
        aim.clear();

        return shot;
    }

    //Move object in a direction - POS & GFX
    public void moveUp(){
        this.getPosition().translatePosition(0,-PLAYER_MOVEMENT);
        this.getMaxPosition().translatePosition(0,-PLAYER_MOVEMENT);
        this.getPicture().translate(0,-PLAYER_MOVEMENT);
    }
    public void moveUpRight(){
        this.getPosition().translatePosition(PLAYER_DIAGONAL,-PLAYER_DIAGONAL);
        this.getMaxPosition().translatePosition(PLAYER_DIAGONAL,-PLAYER_DIAGONAL);
        this.getPicture().translate(PLAYER_DIAGONAL,-PLAYER_DIAGONAL);
    }
    public void moveRight(){
        this.getPosition().translatePosition(PLAYER_MOVEMENT,0);
        this.getMaxPosition().translatePosition(PLAYER_MOVEMENT,0);
        this.getPicture().translate(PLAYER_MOVEMENT,0);
    }
    public void moveDownRight(){
        this.getPosition().translatePosition(PLAYER_DIAGONAL,PLAYER_DIAGONAL);
        this.getMaxPosition().translatePosition(PLAYER_DIAGONAL,PLAYER_DIAGONAL);
        this.getPicture().translate(PLAYER_DIAGONAL,PLAYER_DIAGONAL);
    }
    public void moveDown(){
        this.getPosition().translatePosition(0,PLAYER_MOVEMENT);
        this.getMaxPosition().translatePosition(0,PLAYER_MOVEMENT);
        this.getPicture().translate(0,PLAYER_MOVEMENT);
    }
    public void moveDownLeft(){
        this.getPosition().translatePosition(-PLAYER_DIAGONAL,PLAYER_DIAGONAL);
        this.getMaxPosition().translatePosition(-PLAYER_DIAGONAL,PLAYER_DIAGONAL);
        this.getPicture().translate(-PLAYER_DIAGONAL,PLAYER_DIAGONAL);
    }
    public void moveLeft(){
        this.getPosition().translatePosition(-PLAYER_MOVEMENT,0);
        this.getMaxPosition().translatePosition(-PLAYER_MOVEMENT,0);
        this.getPicture().translate(-PLAYER_MOVEMENT,0);

    }
    public void moveUpLeft(){
        this.getPosition().translatePosition(-PLAYER_DIAGONAL,-PLAYER_DIAGONAL);
        this.getMaxPosition().translatePosition(-PLAYER_DIAGONAL,-PLAYER_DIAGONAL);
        this.getPicture().translate(-PLAYER_DIAGONAL,-PLAYER_DIAGONAL);
    }

    public void shoot() {
        shot.translatePosition(aim.getPos().getX(), aim.getPos().getY());
    }

    public void setMonstars(Monstar[] monstars){
        this.monstars=monstars;
    }

}
