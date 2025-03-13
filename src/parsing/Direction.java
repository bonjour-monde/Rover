package parsing;

public enum Direction {
    NORTH("N", new int[]{0, 1}),
    EAST("E", new int[]{1, 0}),
    SOUTH("S", new int[]{0, -1}),
    WEST("W", new int[]{-1, 0});

    private final String output;
    private final int[] movement;

    Direction(String output, int[] movement) {
        this.output = output;
        this.movement = movement;
    }

    public String getOutput() {
        return output;
    }

    public int[] getMovement() {
        return movement;
    }
}
