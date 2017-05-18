package hu.testathlon.data.parse;

import java.util.List;

/**
 * @author Peter_Fazekas
 */
public class DataParser {

    private final List<String> testResults;
    private final String correctAnswer;

    public DataParser(final List<String> testResults) {
        correctAnswer = testResults.remove(0);
        this.testResults = testResults;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getTestResults() {
        return testResults;
    }
}
