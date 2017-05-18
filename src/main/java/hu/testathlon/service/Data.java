package hu.testathlon.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.testathlon.data.parse.DataParser;
import hu.testathlon.data.read.DataReader;
import hu.testathlon.data.read.TestResultsReader;
import hu.testathlon.model.TestResult;

/**
 * @author Peter_Fazekas
 */
public class Data {

    private static final String SEPARATOR = " ";

    private final DataParser data;
    private final PointCalculation pointCalculation;

    public Data(final String fileName) {
        DataReader file = new TestResultsReader(fileName);
        data = new DataParser(file.read());
        pointCalculation = new PointCalculation(getCorrectAnswer());
    }

    public String getCorrectAnswer() {
        return data.getCorrectAnswer();
    }

    public List<TestResult> getTestResults() {
        return data.getTestResults().stream().map(this::createTestResult).collect(Collectors.toList());
    }

    private TestResult createTestResult(final String line) {
        String[] items = line.split(SEPARATOR);
        String code = items[0];
        String answer = items[1];
        int point = pointCalculation.calculate(answer);
        return new TestResult(code, answer, point);
    }
}
