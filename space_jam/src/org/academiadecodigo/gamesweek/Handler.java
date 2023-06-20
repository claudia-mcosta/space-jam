package org.academiadecodigo.gamesweek;

import org.academiadecodigo.gamesweek.gameObjects.MichaelJordan;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Handler implements KeyboardHandler {

    public Keyboard keyboard;
    public MichaelJordan player;

    public Handler(MichaelJordan player){
        this.player = player;
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

       /* KeyboardEvent keyboardEventDiagonalDownRight = new KeyboardEvent();
        keyboardEventDiagonalDownRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDiagonalDownRight.setKey(KeyboardEvent.KEY_B);
        keyboard.addEventListener(keyboardEventDiagonalDownRight);

        KeyboardEvent keyboardEventDiagonalUpRight = new KeyboardEvent();
        keyboardEventDiagonalUpRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDiagonalUpRight.setKey(KeyboardEvent.KEY_H);
        keyboard.addEventListener(keyboardEventDiagonalUpRight);

        KeyboardEvent keyboardEventDiagonalUpLeft = new KeyboardEvent();
        keyboardEventDiagonalUpLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDiagonalUpLeft.setKey(KeyboardEvent.KEY_F);
        keyboard.addEventListener(keyboardEventDiagonalUpLeft);

        KeyboardEvent keyboardEventDiagonalDownLeft = new KeyboardEvent();
        keyboardEventDiagonalDownLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDiagonalDownLeft.setKey(KeyboardEvent.KEY_V);
        keyboard.addEventListener(keyboardEventDiagonalDownLeft);*/
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        Direction direction;

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.setDirection(Direction.RIGHT);
                if(!player.hitsBorder())
                    player.moveRight();
                break;
            case KeyboardEvent.KEY_LEFT:
                player.setDirection(Direction.LEFT);
                if(!player.hitsBorder())
                    player.moveLeft();
                break;
            case KeyboardEvent.KEY_DOWN:
                player.setDirection(Direction.DOWN);
                if(!player.hitsBorder())
                    player.moveDown();
                break;
            case KeyboardEvent.KEY_UP:
                player.setDirection(Direction.UP);
                if(!player.hitsBorder())
                    player.moveUp();
                break;
            case KeyboardEvent.KEY_Q:
                System.exit(1);
                break;
            /*case KeyboardEvent.KEY_B:
                player.moveDiagonalDownRight();
                break;
            case KeyboardEvent.KEY_H:
                player.moveDiagonalUpRight();
                break;
            case KeyboardEvent.KEY_F:
                player.moveDiagonalDownLeft();
                break;
            case KeyboardEvent.KEY_V:
                player.moveDiagonalUpLeft();
                break;*/
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        player.setDirection(Direction.NONE);


    }
}
