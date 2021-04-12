package Components;

import java.awt.*;

public class MapGenerator {
    public int[][] map; // an array to store the location of the bricks

    // default brick dimensions
    public int brickWidth = 50;
    public int brickHeight = 30;

    // constructor to create the array of positions of the bricks
    public MapGenerator(int row, int col) {
        map = new int[row][col];
        for (int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                map[i][j] = 1;
            }
        }
    }

    // create the graphics for the bricks
    public void draw(Graphics2D g) {
        for (int i=0;i<map.length;i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.RED);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.white);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}