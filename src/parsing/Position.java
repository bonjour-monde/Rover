package parsing;

import exceptions.InvalidFileFormatException;

public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void rotate(char rotation) {
        int shift = switch (rotation) {
            case 'R' -> 1;
            case 'L' -> -1;
            default -> throw new InvalidFileFormatException("Rotation must be either R or L");
        };
        direction = Direction.values()[(direction.ordinal() + shift + 4) % 4];
    }

    public void move() {
        var movement = direction.getMovement();

        x += movement[0];
        y += movement[1];
    }
}
