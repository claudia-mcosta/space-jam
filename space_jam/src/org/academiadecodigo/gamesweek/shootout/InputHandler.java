package org.academiadecodigo.gamesweek.shootout;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class InputHandler  implements KeyboardHandler {

    Keyboard keyboard;

    KeyboardEvent[] events;

    public InputHandler(){
        keyboard = new Keyboard(this);
        createEvents();
    }



    private void createEvents(){
        events = new KeyboardEvent[6];

        for(int i = 0; i < events.length; i++){
            events[i] = new KeyboardEvent();
        }

        events[0].setKey(KeyboardEvent.KEY_I);
        events[1].setKey(KeyboardEvent.KEY_UP);
        events[2].setKey(KeyboardEvent.KEY_DOWN);
        events[3].setKey(KeyboardEvent.KEY_LEFT);
        events[4].setKey(KeyboardEvent.KEY_RIGHT);
        events[5].setKey(KeyboardEvent.KEY_SPACE);

        for(int i = 0; i < events.length; i++){
            events[i].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(events[i]);
        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent){
        switch (keyboardEvent.getKey()){

            case KeyboardEvent.KEY_I:

            case KeyboardEvent.KEY_SPACE:


            case KeyboardEvent.KEY_DOWN:


            case KeyboardEvent.KEY_UP:



            case KeyboardEvent.KEY_LEFT:



            case KeyboardEvent.KEY_RIGHT:



        }
    }


    public void keyReleased(KeyboardEvent keyboardEvent){

    }


}
