package hu.testathlon.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import hu.testathlon.model.TestResult;

/**
 * @author Peter_Fazekas
 */
public class TestAthlon extends CheckTask{

    private static final String EMPTY = "";
    private static final String CORRECT = "+";
    private static final String WRONG = " ";
    private static final String TAB = "\t";
    private static final String NEW_LINE = "\r\n";
    private static final double PERCENT = 100.0;

    private final String base;
    private final List<TestResult> answers;

    public TestAthlon(final String base, final List<TestResult> answers) {
        super(base);
        this.base = base;
        this.answers = answers;
    }

    /**
     * 2. feldaat: Jelenítse meg a képernyőn a mintának megfelelően, hogy hány versenyző vett részt a tesztversenyen!
     * @return - a résztvevők száma
     */
    public int getCompetitorNumber() {
        return answers.size();
    }

    /**
     * 3. feladat: Kérje be egy versenyző azonosítóját, és jelenítse meg a mintának megfelelően a hozzá eltárolt válaszokat!
     * Feltételezheti, hogy a fájlban létező azonosítót adnak meg.
     * @param code
     * @return
     */
    public String getAnswer(final String code) {
        String answer = findAnswer(code);
        return (!answer.isEmpty() ? answer + TAB + "(a versenyző válasza)" : EMPTY) + NEW_LINE;
    }

    private String findAnswer(final String code) {
        return answers.stream()
                .filter(i -> i.getCode().equals(code))
                .map(TestResult::getAnswers)
                .findFirst()
                .orElse(EMPTY);
    }

    /**
     * 4. feladat: Írassa ki a képernyőre a helyes megoldást! A helyes megoldás alatti sorba „+” jelet tegyen,
     * ha az adott feladatot az előző feladatban kiválasztott versenyző eltalálta, egyébként egy szóközt!
     * @param code
     * @return
     */
    public String getResult(final String code) {
        return base + TAB + "(a helyes megoldás)" + NEW_LINE + checkResult(findAnswer(code)) + TAB + "(a versenyző helyes válaszai)" + NEW_LINE;
    }

    private String checkResult(final String answer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < base.length(); i++) {
            sb.append(checkCertainTask(i, answer) ?  CORRECT : WRONG);
        }
        return sb.toString();
    }

    /**
     * 5. feladat: Kérje be egy feladat sorszámát, majd határozza meg, hogy hány versenyző adott a feladatra helyes megoldást,
     * és ez a versenyzők hány százaléka! A százalékos eredményt két tizedesjeggyel írassa ki!
     * @param taskNumber
     * @return
     */
    public String getCorrectTaskAnswers(final int taskNumber) {
        long correctTaskAnswers = checkCorrectTaskAnswers(taskNumber - 1);
        return String.format("A feladatra %d fő, a versenyzők %5.2f%%-a adott helyes választ.%n",
                correctTaskAnswers, correctTaskAnswers * PERCENT / getCompetitorNumber());
    }

    private long checkCorrectTaskAnswers(final int taskNumber) {
        return answers.stream()
                .map(TestResult::getAnswers)
                .filter(i -> checkCertainTask(taskNumber, i))
                .count();
    }

    /**
     * 6. feladat: A verseny feladatai nem egyenlő nehézségűek: az 1-5. feladat 3 pontot, a 6-10. feladat 4 pontot,
     * a 11-13. feladat 5 pontot, míg a 14. feladat 6 pontot ér.
     * Határozza meg az egyes versenyzők pontszámát, és a listát írassa ki a pontok.txt nevű állományba!
     * Az állomány minden sora egy versenyző kódját, majd szóközzel elválasztva az általa elért pontszámot tartalmazza!
     * @return
     */
    public List<String> getPoints() {
        return answers.stream().map(TestResult::toString).collect(Collectors.toList());
    }

    /**
     * 7. feladat: A versenyen a három legmagasabb pontszámot elérő összes versenyzőt díjazzák.
     * Például 5 indulónál előfordulhat, hogy 3 első és 2 második díjat adnak ki. Így megtörténhet az is,
     * hogy nem kerül sor mindegyik díj kiadására.
     * Írassa ki a mintának megfelelően a képernyőre a díjazottak kódját és pontszámát pontszám szerint csökkenő sorrendben!
     * @return
     */
    public String getWinners() {
        StringBuilder sb = new StringBuilder();
        List<Integer> bestPoints = getBestPoints();
        for(int rank = 0; rank < 3; rank++) {
            sb.append(printBestCompetitor(rank, getBestCompetitor(bestPoints.get(rank))));
        }
        return sb.toString();
    }

    private List<Integer> getBestPoints() {
        return answers.stream().map(TestResult::getPoint).collect(Collectors.toSet()).stream().sorted(
                Comparator.reverseOrder()).collect(Collectors.toList()).subList(0, 3);
    }

    private String printBestCompetitor(final int rank, final List<TestResult> competitors) {
        StringBuilder sb = new StringBuilder();
        competitors.stream().map(i -> printDetails(rank, i)).forEach(sb::append);
        return sb.toString();
    }

    private String printDetails(final int rank, final TestResult competitor) {
        return (rank + 1) + ". díj (" + competitor.getPoint() + " pont): " + competitor.getCode() + NEW_LINE;
    }

    private List<TestResult> getBestCompetitor(final int point) {
        return answers.stream().filter(i -> i.getPoint() == point).collect(Collectors.toList());
    }

}
