package graphics;

import javax.swing.*;

public class GameFrame extends JFrame {

    private final int height;
    private final int width;
    private final boolean visible;

    public GameFrame(int width, int height, boolean visible) {
        this.height = height;
        this.width = width;
        this.visible = visible;
    }

    public void configure(){
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(visible);
    }
}
