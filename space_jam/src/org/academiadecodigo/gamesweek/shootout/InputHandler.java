package org.academiadecodigo.gamesweek.shootout;

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
    Player player;


    public InputHandler(Player player){
        this.player = player;
        keyboard = new Keyboard(this);
        createEvents();
    }

    private void createEvents(){
        events = new KeyboardEvent[3];

        for(int i = 0; i < events.length; i++){
            events[i] = new KeyboardEvent();
        }

        events[0].setKey(KeyboardEvent.KEY_I);
        events[1].setKey(KeyboardEvent.KEY_SPACE);
        events[2].setKey(KeyboardEvent.KEY_ESC);

        for(int i = 0; i < events.length; i++){
            events[i].setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
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
                player.shoot();
                break;
            case KeyboardEvent.KEY_ESC:
                // Exit game with ESC key instead of closing window with mouse.
                System.exit(1);
                break;
        }
    }


    public void keyReleased(KeyboardEvent keyboardEvent){

    }


}
