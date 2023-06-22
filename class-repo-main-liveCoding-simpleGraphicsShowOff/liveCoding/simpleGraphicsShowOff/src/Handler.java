import org.academiadecodigo.simplegraphics.graphics.Rectangle;
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

    public void createKeyboardEvents() {
        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventSpace = new KeyboardEvent();
        keyboardEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboardEventSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(keyboardEventSpace);
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

           case KeyboardEvent.KEY_SPACE:
               System.exit(1);
               break;
       }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
