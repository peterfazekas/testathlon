package hu.testathlon.service;

/**
 * @author Peter_Fazekas
 */
class CheckTask {

    final String base;

    CheckTask(String base) {
        this.base = base;
    }

    boolean checkCertainTask(final int taskNumber, final String answer) {
        return answer.charAt(taskNumber) == base.charAt(taskNumber);
    }
}
