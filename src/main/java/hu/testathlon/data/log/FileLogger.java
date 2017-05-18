package hu.testathlon.data.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Peter_Fazekas
 */
public class FileLogger implements DataLogger {

    private static final String PATH = "src\\main\\resources\\";

    private final String fileName;

    public FileLogger(final String fileName) {
        this.fileName = PATH + fileName;
    }

    @Override
    public void printAll(final List<String> text) {
        try (PrintWriter log = new PrintWriter(new FileWriter(fileName))){
            text.forEach(log::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
