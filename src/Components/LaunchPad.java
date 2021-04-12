package Components;

import java.awt.*;

public class LaunchPad {
    // position of the launchpad
    public static int padX = 400;
    public static int padY = 450;

    // dimensions of the launchpad
    public static int padWidth = 70;
    public static int padHeight = 10;

    // method to draw the launchpad
    public void create(Graphics g) {
        g.fillRect(padX, padY, padWidth, padHeight);
    }

    // function to move the launch pad to the right
    public void moveRight() {
        if (padX >= 400) {
            padX = 400;
        } else {
            padX += 40;
        }
    }

    // function to move the launch pad to the left
    public void moveLeft() {
        if (padX <= 10) {
            padX = 10;
        } else {
            padX -= 40;
        }
    }
}