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

        KeyboardEvent keyboardEventDiagonal = new KeyboardEvent();
        keyboardEventDiagonal.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventDiagonal.setKey(KeyboardEvent.KEY_UP);
        keyboardEventDiagonal.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(keyboardEventDiagonal);

    }




    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {



        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:

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
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
