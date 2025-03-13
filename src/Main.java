import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("One single argument required");
            return;
        }

        var fileName = args[0];

        try (var fileReader = new FileReader(fileName)) {
            var parser = new Parser(fileReader);
            parser.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}