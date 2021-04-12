import Components.Ball;
import Components.LaunchPad;
import Components.MapGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameComponents extends JPanel implements KeyListener, ActionListener {
    // default width and height parameters for the panel
    public static int WIDTH;
    public static int HEIGHT;

    // set the state of the game
    private boolean moving = false;

    // initializing the score for the game
    private int totalBricks=21;
    private int score;

    // creating an instance of all the classes in the components package
    Ball ball;
    LaunchPad launchPad;
    MapGenerator map;

    // create object of timer class to create the animation
    Timer timer;

    // a flag to keep track if the player lost or won
    int flag = 0;


    public GameComponents() {
        // setting the dimensions of the panel same as the JFrame
        WIDTH = Main.WIDTH;
        HEIGHT = Main.HEIGHT;

        // creating objects of all the classes
        ball = new Ball();
        launchPad = new LaunchPad();
        map = new MapGenerator(3, 7);
        timer = new Timer(5, this);
    }

    public void paint(Graphics g) {
        super.paint(g); // clear the screen
        ball.create(g); // draw the ball on the screen
        launchPad.create(g); // draw the launchpad on the screen
        map.draw((Graphics2D)g); // draw the bricks on the screen

        // display the score on the screen
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("" + score, 30, 30);

        // condition to check if game over
        if (ball.ballY > 437) {
            if (flag == 1) {
                moving = false;
                g.setFont(new Font("SansSerif", Font.BOLD, 14));
                g.drawString("You won!", 250, 250);
                map = new MapGenerator(3, 7);
                score=0;
            } else {
                moving = false;
                g.setFont(new Font("SansSerif", Font.BOLD, 14));
                g.drawString("Game over! Press s to restart", 250, 250);
                map = new MapGenerator(3, 7);
                score = 0;
            }
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 's') {
            if (!moving) {
                moving = true;
                ball.ballX = LaunchPad.padX + 10;
                ball.ballY = LaunchPad.padY - 40;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'd') {
            launchPad.moveRight();
        } else if (e.getKeyChar() == 'a') {
            launchPad.moveLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (moving) {
            if (new Rectangle(ball.ballX, ball.ballY, 20, 20).intersects(new Rectangle(LaunchPad.padX, LaunchPad.padY, 100, 8))) {
                ball.ballVelY = -ball.ballVelY;
            }

            for (int i=0;i<map.map.length;i++) {
                for (int j=0;j<map.map[0].length;j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j*map.brickWidth+80;
                        int brickY = i*map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ball.ballX, ball.ballY, 20, 20);

                        if (ballRect.intersects(rect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (totalBricks == 0) {
                                flag = 1;
                            }

                            System.out.println(score);

                            if (ball.ballX+19<= rect.x || ball.ballX+1>= rect.x+ rect.width) {
                                ball.ballVelX = -ball.ballVelX;
                            } else {
                                ball.ballVelY = -ball.ballVelY;
                            }

                            break;
                        }
                    }
                }
            }

            ball.ballX += ball.ballVelX;
            ball.ballY += ball.ballVelY;
            if (ball.ballX >= 450) {
                ball.ballVelX = -ball.ballVelX;
            }
            if (ball.ballY >= 450) {
                ball.ballVelY = -ball.ballVelY;
            }
            if (ball.ballX <= 0) {
                ball.ballVelX = -ball.ballVelX;
            }
            if (ball.ballY <= 0) {
                ball.ballVelY = -ball.ballVelY;
            }
        }
        repaint();
    }
}