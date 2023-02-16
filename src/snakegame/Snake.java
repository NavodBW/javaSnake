package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Point> body;
    private Color color;
    private Direction direction;

    public Snake(Color color, Point head) {
        body = new ArrayList<Point>();
        body.add(head);
        this.color = color;

        direction = Direction.RIGHT;
    }

    public void setDirection(Direction direction) {
        if (!this.direction.isOpposite(direction)) {
            this.direction = direction;
        }
    }

    public void update() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, body.get(i - 1));
        }
        body.set(0, getNextHead());
    }

    private Point getNextHead() {
        Point head = new Point(body.get(0));
        switch (direction) {
            case UP:
                head.y -= 1;
                break;
            case DOWN:
                head.y += 1;
                break;
            case LEFT:
                head.x -= 1;
                break;
            case RIGHT:
                head.x += 1;
                break;
        }
        return head;
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
    }

    public boolean checkCollision() {
        Point head = body.get(0);
        if (head.x < 0 || head.x >= Game.WIDTH || head.y < 0 || head.y >= Game.HEIGHT) {
            return true;
        }
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Point point : body) {
            g.fillRect(point.x * Game.SCALE, point.y * Game.SCALE, Game.SCALE, Game.SCALE);
        }
    }

    public Point getHead() {
        return body.get(0);
    }

    public int getLength() {
        return body.size();
    }

}
