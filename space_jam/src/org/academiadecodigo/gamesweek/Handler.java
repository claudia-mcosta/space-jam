package org.academiadecodigo.gamesweek;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Handler implements KeyboardHandler {

    public Keyboard keyboard;
    public Player player;

    public Handler(Player player){
        this.player = player;
        keyboard = new Keyboard(this);
        createKeyboardEvents();
    }

    public void createKeyboardEvents(){
        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventUp.setKey(KeyboardEvent.KEY_UP);
        keyboard.addEventListener(keyboardEventUp);

        KeyboardEvent keyboardEventDown = new KeyboardEvent();
        keyboardEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDown.setKey(KeyboardEvent.KEY_DOWN);
        keyboard.addEventListener(keyboardEventDown);

        KeyboardEvent keyboardEventQ = new KeyboardEvent();
        keyboardEventQ.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventQ.setKey(KeyboardEvent.KEY_Q);
        keyboard.addEventListener(keyboardEventQ);

        KeyboardEvent keyboardEventDiagonalDownRight = new KeyboardEvent();
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
        keyboard.addEventListener(keyboardEventDiagonalDownLeft);



    }




    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {



        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.moveRight();
                break;

            case KeyboardEvent.KEY_LEFT:
                player.moveLeft();
                break;

            case KeyboardEvent.KEY_DOWN:
                player.moveDown();
                break;

            case KeyboardEvent.KEY_UP:
                player.moveUp();
                break;

            case KeyboardEvent.KEY_Q:
                System.exit(1);
                break;

            case KeyboardEvent.KEY_B:
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
                break;


        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
