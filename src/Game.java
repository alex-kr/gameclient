import com.sun.glass.events.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Main class for the game
 */

public class Game extends JFrame{
    boolean isRunning = true;
    int fps = 30;
    int windowWidth = 300;
    int windowHeight = 300;

    BufferedImage backBuffer;
    Insets insets;
    InputHandler input;

    int x = 0;
    int y = 0;

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
        System.exit(0);
    }

    /**
     * This method starts the game and runs it in a loop
     */
    public void run() {
        initialize();
        while(isRunning) {
            long time = System.currentTimeMillis();
            update();
            draw();
            //  delay for each frame  -   time it took for one frame
            time = (1000 / fps) - (System.currentTimeMillis() - time);
            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch(Exception e){}
            }
        }
        setVisible(false);
    }

    /**
     * This method sets up everything need for the game to run
     */
    void initialize() {
        setTitle("Game");
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        insets = getInsets();
        setSize(insets.left + windowWidth + insets.right,
                insets.top + windowHeight + insets.bottom);

        backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        input = new InputHandler(this);
    }

    /**
     * This method checks for input, move things
     * around and check for win conditions, etc
     */
    void update() {
        if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
            x += 5;
        }
        if (input.isKeyDown(KeyEvent.VK_LEFT)) {
            x -= 5;
        }
        if (input.isKeyDown(KeyEvent.VK_UP)) {
            y -= 5;
        }
        if (input.isKeyDown(KeyEvent.VK_DOWN)) {
            y += 5;
        }
    }

    /**
     * This method draws everything
     */
    void draw() {
        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, windowWidth, windowHeight);

        bbg.setColor(Color.BLACK);
        bbg.drawOval(x, y, 20, 20);

        g.drawImage(backBuffer, insets.left, insets.top, this);
    }

}
