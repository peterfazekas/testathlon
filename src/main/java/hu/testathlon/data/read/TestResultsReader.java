package hu.testathlon.data.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Peter_Fazekas
 */
public class TestResultsReader implements DataReader {

    private static final String PATH = "src\\main\\resources\\";

    private final String fileName;

    public TestResultsReader(final String fileName) {
        this.fileName = PATH + fileName;
    }

    @Override
    public List<String> read() {
        List<String> lines = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
