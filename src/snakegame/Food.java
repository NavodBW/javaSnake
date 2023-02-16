package snakegame;

import java.awt.*;

public class Food {
    private Point position;

    public Food(int x, int y) {
        position = new Point(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(position.x * Game.SCALE, position.y * Game.SCALE, Game.SCALE, Game.SCALE);
    }

    public void randomizePosition(int width, int height) {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        position.setLocation(x, y);
    }

    public boolean isEaten(Point snakePosition) {
        return snakePosition.equals(position);
    }
}
