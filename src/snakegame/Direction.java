package snakegame;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public boolean isOpposite(Direction direction) {
        return this == UP && direction == DOWN ||
                this == DOWN && direction == UP ||
                this == LEFT && direction == RIGHT ||
                this == RIGHT && direction == LEFT;
    }
}
