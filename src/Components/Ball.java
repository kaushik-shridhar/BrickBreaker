package Components;

import java.awt.*;

public class Ball {
    // setting the position and speed of the ball
    public int ballX = LaunchPad.padX - 20;
    public int ballY = LaunchPad.padY - 20;
    public int ballVelX = -2;
    public int ballVelY = -2;

    // method to draw the ball
    public void create(Graphics g) {
        g.fillOval(ballX, ballY, 20, 20);
    }
}