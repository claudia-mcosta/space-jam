package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.Direction;
import org.academiadecodigo.gamesweek.gameObjects.Ball;
import org.academiadecodigo.gamesweek.gameObjects.MichaelJordan;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/* NON MVP IMPROVEMENTS:
 *
 * Start aim movement with I key
 *
 */

public class InputHandler implements KeyboardHandler {

    Keyboard keyboard;
    KeyboardEvent[] events;
    MichaelJordan michaelJordan;
    private Ball ball;
    public boolean gameStart=false;
    private boolean leftPressed=false;
    private boolean upPressed=false;
    private boolean rightPressed=false;
    private boolean downPressed=false;

    public InputHandler(MichaelJordan michaelJordan, Ball ball){
        this.michaelJordan = michaelJordan;
        keyboard = new Keyboard(this);
        this.ball=ball;

        events = new KeyboardEvent[12];
        createEvents();
    }

    public boolean getGameStart(){
        return gameStart;
    }

    public void resetGameStart(){
        this.gameStart=false;
    }
    private void createEvents() {

        for (int i = 0; i < events.length; i++) {
            events[i] = new KeyboardEvent();
        }
    }

    public void createKeyPressedEventsGame(){

        events[0].setKey(KeyboardEvent.KEY_RIGHT);
        events[1].setKey(KeyboardEvent.KEY_LEFT);
        events[2].setKey(KeyboardEvent.KEY_UP);
        events[3].setKey(KeyboardEvent.KEY_DOWN);
        events[4].setKey(KeyboardEvent.KEY_ESC);

        for(int i = 0; i < 5 ;i++){
            events[i].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(events[i]);
        }
    }

    public void removeKeyPressedEventsGame(){

            for (int i = 0; i < 5; i++) {
                keyboard.removeEventListener(events[i]);
            }
    }

    public void createKeyPressedEventsShootOut(){

        events[5].setKey(KeyboardEvent.KEY_I);
        events[6].setKey(KeyboardEvent.KEY_Q);
         events[7].setKey(KeyboardEvent.KEY_SPACE);

        for(int i = 5; i < 8;i++){
            events[i].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(events[i]);
        }
    }

    public void removeKeyPressedEventsShootOut()
    {
            for (int i = 5; i < 8; i++)
            {
                keyboard.removeEventListener(events[i]);
            }
    }


    public void createKeyReleasedEvents() {
        events[8].setKey(KeyboardEvent.KEY_RIGHT);
        events[9].setKey(KeyboardEvent.KEY_LEFT);
        events[10].setKey(KeyboardEvent.KEY_UP);
        events[11].setKey(KeyboardEvent.KEY_DOWN);

        for(int i = 8; i < events.length;i++){
            events[i].setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyboard.addEventListener(events[i]);
        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent){

        switch (keyboardEvent.getKey()){

            case KeyboardEvent.KEY_I:
                // Start shootOut ? If there are game instructions before shootOut, this can clear the message and start the aim movement.
                break;
            case KeyboardEvent.KEY_SPACE:
                // Lock target to shoot
                michaelJordan.shoot();
                break;
            case KeyboardEvent.KEY_ESC:
                // Exit game with ESC key instead of closing window with mouse.
                System.exit(1);
                break;
            case KeyboardEvent.KEY_RIGHT:
                rightPressed=true;
                michaelJordan.setDirection(Direction.RIGHT);
                if(upPressed){
                    michaelJordan.setDirection(Direction.UP_RIGHT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveUpRight();
                }else if(downPressed) {
                    michaelJordan.setDirection(Direction.DOWN_RIGHT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveDownRight();
                }else if(leftPressed){
                    michaelJordan.setDirection(Direction.NONE);
                }else {
                    michaelJordan.setDirection(Direction.RIGHT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveRight();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                leftPressed=true;
                michaelJordan.setDirection(Direction.LEFT);
                if(upPressed){
                    michaelJordan.setDirection(Direction.UP_LEFT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveUpLeft();
                }else if(downPressed){
                    michaelJordan.setDirection(Direction.DOWN_LEFT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveDownLeft();
                }else if(rightPressed){
                    michaelJordan.setDirection(Direction.NONE);
                }else {
                    michaelJordan.setDirection(Direction.LEFT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveLeft();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                downPressed=true;
                michaelJordan.setDirection(Direction.DOWN);
                if(leftPressed){
                    michaelJordan.setDirection(Direction.DOWN_LEFT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveDownLeft();
                }else if(rightPressed){
                    michaelJordan.setDirection(Direction.DOWN_RIGHT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveDownRight();
                }else if(upPressed){
                    michaelJordan.setDirection(Direction.NONE);
                }else {
                    michaelJordan.setDirection(Direction.DOWN);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveDown();
                }
                break;
            case KeyboardEvent.KEY_UP:
                upPressed=true;
                michaelJordan.setDirection(Direction.UP);
                if(rightPressed){
                    michaelJordan.setDirection(Direction.UP_RIGHT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveUpRight();
                }else if(leftPressed){
                    michaelJordan.setDirection(Direction.UP_LEFT);
                    if(!michaelJordan.hasCollisions())
                        michaelJordan.moveUpLeft();
                }else if(downPressed){
                    michaelJordan.setDirection(Direction.NONE);
                }else {
                    michaelJordan.setDirection(Direction.UP);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveUp();
                }
                break;
        }
        michaelJordan.ballCollision(ball);

        gameStart=true;

    }


    public void keyReleased (KeyboardEvent keyboardEvent){

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                rightPressed = false;
                if (upPressed) {
                    michaelJordan.setDirection(Direction.UP);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveUp();
                } else if (downPressed) {
                    michaelJordan.setDirection(Direction.DOWN);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveDown();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                leftPressed = false;
                if (upPressed) {
                    michaelJordan.setDirection(Direction.UP);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveUp();
                } else if (downPressed) {
                    michaelJordan.setDirection(Direction.DOWN);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveDown();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                downPressed = false;
                if (leftPressed) {
                    michaelJordan.setDirection(Direction.LEFT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveLeft();
                } else if (rightPressed) {
                    michaelJordan.setDirection(Direction.RIGHT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveRight();
                }
                break;
            case KeyboardEvent.KEY_UP:
                upPressed = false;
                if (leftPressed) {
                    michaelJordan.setDirection(Direction.LEFT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveLeft();
                } else if (rightPressed) {
                    michaelJordan.setDirection(Direction.RIGHT);
                    if (!michaelJordan.hasCollisions())
                        michaelJordan.moveRight();
                }
                break;
        }

        michaelJordan.ballCollision(ball);

        michaelJordan.setDirection(Direction.NONE);
    }


}
