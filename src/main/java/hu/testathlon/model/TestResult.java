package hu.testathlon.model;

/**
 * @author Peter_Fazekas
 */
public class TestResult {

    private final String code;
    private final String answers;
    private final int point;

    public TestResult(final String code, final String answers, final int point) {
        this.code = code;
        this.answers = answers;
        this.point = point;
    }

    public String getCode() {
        return code;
    }

    public String getAnswers() {
        return answers;
    }

    public Integer getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return code + " " + point;
    }

}
