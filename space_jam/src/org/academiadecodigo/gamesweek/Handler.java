package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.Ball;
import org.academiadecodigo.gamesweek.gameObjects.MichaelJordan;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.concurrent.TimeUnit;

public class Handler implements KeyboardHandler {

    public Keyboard keyboard;
    public MichaelJordan player;
    private Ball ball;
    private boolean leftPressed=false;
    private boolean upPressed=false;
    private boolean rightPressed=false;
    private boolean downPressed=false;

    public Handler(MichaelJordan player, Ball ball){
        this.player = player;
        this.ball = ball;
        keyboard = new Keyboard(this);
        createKeyboardEvents();
    }

    public void createKeyboardEvents(){
        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        KeyboardEvent keyboardEventRightReleased = new KeyboardEvent();
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventRightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboardEventRightReleased.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(keyboardEventRight);
        keyboard.addEventListener(keyboardEventRightReleased);

        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        KeyboardEvent keyboardEventLeftReleased = new KeyboardEvent();
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventLeftReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboardEventLeftReleased.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(keyboardEventLeft);
        keyboard.addEventListener(keyboardEventLeftReleased);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        KeyboardEvent keyboardEventUpReleased = new KeyboardEvent();
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventUpReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboardEventUp.setKey(KeyboardEvent.KEY_UP);
        keyboardEventUpReleased.setKey(KeyboardEvent.KEY_UP);
        keyboard.addEventListener(keyboardEventUp);
        keyboard.addEventListener(keyboardEventUpReleased);

        KeyboardEvent keyboardEventDown = new KeyboardEvent();
        KeyboardEvent keyboardEventDownReleased = new KeyboardEvent();
        keyboardEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDownReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboardEventDown.setKey(KeyboardEvent.KEY_DOWN);
        keyboardEventDownReleased.setKey(KeyboardEvent.KEY_DOWN);
        keyboard.addEventListener(keyboardEventDown);
        keyboard.addEventListener(keyboardEventDownReleased);

        KeyboardEvent keyboardEventQ = new KeyboardEvent();
        keyboardEventQ.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventQ.setKey(KeyboardEvent.KEY_Q);
        keyboard.addEventListener(keyboardEventQ);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                rightPressed=true;
                player.setDirection(Direction.RIGHT);
                if(upPressed){
                    player.setDirection(Direction.UP_RIGHT);
                    if(!player.hitsBorder())
                        player.moveUpRight();
                }else if(downPressed) {
                    player.setDirection(Direction.DOWN_RIGHT);
                    if (!player.hitsBorder())
                        player.moveDownRight();
                }else if(leftPressed){
                    player.setDirection(Direction.NONE);
                }else {
                    player.setDirection(Direction.RIGHT);
                    if (!player.hitsBorder())
                        player.moveRight();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                leftPressed=true;
                player.setDirection(Direction.LEFT);
                if(upPressed){
                    player.setDirection(Direction.UP_LEFT);
                    if(!player.hitsBorder())
                        player.moveUpLeft();
                }else if(downPressed){
                    player.setDirection(Direction.DOWN_LEFT);
                    if(!player.hitsBorder())
                        player.moveDownLeft();
                }else if(rightPressed){
                    player.setDirection(Direction.NONE);
                }else {
                    player.setDirection(Direction.LEFT);
                    if (!player.hitsBorder())
                        player.moveLeft();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                downPressed=true;
                player.setDirection(Direction.DOWN);
                if(leftPressed){
                    player.setDirection(Direction.DOWN_LEFT);
                    if(!player.hitsBorder())
                        player.moveDownLeft();
                }else if(rightPressed){
                    player.setDirection(Direction.DOWN_RIGHT);
                    if(!player.hitsBorder())
                        player.moveDownRight();
                }else if(upPressed){
                    player.setDirection(Direction.NONE);
                }else {
                    player.setDirection(Direction.DOWN);
                    if (!player.hitsBorder())
                        player.moveDown();
                }
                break;
            case KeyboardEvent.KEY_UP:
                upPressed=true;
                player.setDirection(Direction.UP);
                if(rightPressed){
                    player.setDirection(Direction.UP_RIGHT);
                    if(!player.hitsBorder())
                        player.moveUpRight();
                }else if(leftPressed){
                    player.setDirection(Direction.UP_LEFT);
                    if(!player.hitsBorder())
                        player.moveUpLeft();
                }else if(downPressed){
                    player.setDirection(Direction.NONE);
                }else {
                    player.setDirection(Direction.UP);
                    if (!player.hitsBorder())
                        player.moveUp();
                }
                break;
            case KeyboardEvent.KEY_Q:
                System.exit(1);
                break;
        }

        player.tryStealBall(ball);
        if(ball.isFollowing()){
            ball.moveBall();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        /*switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.setDirection(Direction.RIGHT);
                if (!player.hitsBorder())
                    player.moveRight();
                break;
            case KeyboardEvent.KEY_LEFT:
                player.setDirection(Direction.LEFT);
                if (!player.hitsBorder())
                    player.moveLeft();
                break;
            case KeyboardEvent.KEY_DOWN:
                downPressed=true;
                player.setDirection(Direction.DOWN);
                if (!player.hitsBorder())
                    player.moveDown();
                break;
            case KeyboardEvent.KEY_UP:
                upPressed=true;
                player.setDirection(Direction.UP);
                if (!player.hitsBorder())
                    player.moveUp();
                break;
            case KeyboardEvent.KEY_Q:
                System.exit(1);
                break;
        }*/


        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                rightPressed=false;
                if(upPressed){
                    player.setDirection(Direction.UP);
                    if (!player.hitsBorder())
                        player.moveUp();
                }else if(downPressed){
                    player.setDirection(Direction.DOWN);
                    if (!player.hitsBorder())
                        player.moveDown();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                leftPressed=false;
                if(upPressed){
                    player.setDirection(Direction.UP);
                    if (!player.hitsBorder())
                        player.moveUp();
                }else if(downPressed){
                    player.setDirection(Direction.DOWN);
                    if (!player.hitsBorder())
                        player.moveDown();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                downPressed=false;
                if(leftPressed){
                    player.setDirection(Direction.LEFT);
                    if (!player.hitsBorder())
                        player.moveLeft();
                }else if(rightPressed){
                    player.setDirection(Direction.RIGHT);
                    if (!player.hitsBorder())
                        player.moveRight();
                }
                break;
            case KeyboardEvent.KEY_UP:
                upPressed=false;
                if(leftPressed){
                    player.setDirection(Direction.LEFT);
                    if (!player.hitsBorder())
                        player.moveLeft();
                }else if(rightPressed){
                    player.setDirection(Direction.RIGHT);
                    if (!player.hitsBorder())
                        player.moveRight();
                }
                break;
        }
        player.tryStealBall(ball);
        if(ball.isFollowing()){
            ball.moveBall();
        }

        player.setDirection(Direction.NONE);

    }
}
