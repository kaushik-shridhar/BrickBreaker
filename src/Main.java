import javax.swing.*;

public class Main {
    // creating an object of the JFrame class to create the GUI
    JFrame f = new JFrame("Brick Breaker");

    // default height and width of the window
    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    // creating an instance of the class that joins all other classes
    GameComponents components;

    public void prepareGUI() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // end the program when the window is closed
        f.setVisible(true); // make the window visible
        f.setSize(WIDTH, HEIGHT); // default size of the window
        f.setResizable(false); // make the window non resizable

        components = new GameComponents();
        f.add(components); // add the game components to the frame
        f.addKeyListener(components);
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.prepareGUI();
    }
}