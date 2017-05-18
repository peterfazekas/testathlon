package hu.testathlon.service;

/**
 * @author Peter_Fazekas
 */
class PointCalculation extends CheckTask {

    private static final int[] POINTS = {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6} ;

    public PointCalculation(final String base) {
        super(base);
    }

    public int calculate(final String answer) {
        int points = 0;
        for (int i = 0; i < base.length(); i++) {
            points += checkCertainTask(i, answer) ?  POINTS[i] : 0;
        }
        return points;
    }

}
