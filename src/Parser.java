import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private final FileReader fileReader;
    private BufferedReader reader;
    private int maxX;
    private int maxY;

    public Parser(FileReader fileReader) throws FileNotFoundException {
        this.fileReader = fileReader;
    }

    public void parse() throws IOException {
        reader = new BufferedReader(fileReader);

        parseBoardEdges();

        while (reader.ready()) {
            var currentPosition = parsePosition();
            parseInstruction(currentPosition);
            System.out.println(currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getDirection().getOutput());
        }

    }

    private void parseBoardEdges() throws IOException {
        String[] edges = reader.readLine().split(" ");
        maxX = Integer.parseInt(edges[0]);
        maxY = Integer.parseInt(edges[1]);
    }



    private Position parsePosition() throws IOException {
        String[] position = reader.readLine().split(" ");

        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);

        checkOutOfBoundsPosition(x, y);

        var direction = toDirection(position[2]);

        return new Position(x, y, direction);
    }

    private void parseInstruction(Position position) throws IOException{
        String line = reader.readLine();

        for (char instruction : line.toCharArray()) {
            if (instruction == 'M'){
                position.move();
                checkOutOfBoundsPosition(position.getX(), position.getY());
            }
            else {
                position.rotate(instruction);
            }
        }
    }

    private Direction toDirection(String letter) {
        return switch (letter) {
            case "N" -> Direction.NORTH;
            case "S" -> Direction.SOUTH;
            case "W" -> Direction.WEST;
            case "E" -> Direction.EAST;
            default -> throw new InvalidFileFormatException(("The direction must be either N, S, W or E"));
        };
    }

    private void checkOutOfBoundsPosition(int x, int y) {
        if (x < 0 || x > maxX) {
            throw new OutOfBoundsException("x must be between 0 and " + maxX + "(currently: " + x + ")");
        }
        if (y < 0 || y > maxY) {
            throw new OutOfBoundsException("y must be between 0 and " + maxY + "(currently: " + y + ")");
        }
    }

}
