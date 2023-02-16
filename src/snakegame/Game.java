package snakegame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SCALE = 20;

    private Snake snake;
    private Food food;
    private boolean running;
    private Thread thread;
    private int score;

    public Game() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        start();
    }

    private void requestFocusOnComponent() {
        if (isFocusable() && !isFocusOwner()) {
            requestFocus();
        }
    }


    public void start() {
        snake = new Snake(Color.WHITE, new Point(WIDTH / 2, HEIGHT / 2));
        food = new Food(0, 0);
        food.randomizePosition(WIDTH, HEIGHT);
        running = true;
        requestFocusOnComponent();
        thread = new Thread(this);
        thread.start();
    }

    private void update() {
        snake.update();
        if (snake.checkCollision()) {
            running = false;
            return;
        }
        if (food.isEaten(snake.getHead())) {
            snake.grow();
            food.randomizePosition(WIDTH, HEIGHT);
            score++;
        }
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        food.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("UP key pressed.");
                snake.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
        }
        repaint();


    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
