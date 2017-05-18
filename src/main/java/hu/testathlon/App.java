package hu.testathlon;

import java.util.Scanner;

import hu.testathlon.data.log.DataLogger;
import hu.testathlon.data.log.FileLogger;
import hu.testathlon.service.Data;
import hu.testathlon.service.Console;
import hu.testathlon.service.TestAthlon;

/**
 * @author Peter_Fazekas
 */
class App {

    private static final String INPUT = "valaszok.txt";
    private static final String OUTPUT = "pontok.txt";
    private final DataLogger log;
    private final Console console;
    private final TestAthlon testAthlon;

    private App() {
        Data data = new Data(INPUT);
        log = new FileLogger(OUTPUT);
        testAthlon = new TestAthlon(data.getCorrectAnswer(), data.getTestResults());
        console = new Console(new Scanner(System.in));
    }

    public static void main(String[] args) {
        App app = new App();
        app.print();
    }

    private void print() {
        System.out.println("1. feladat: Az adatok beolvasása\n");
        System.out.println(String.format("2. feladat: A vetélkedőn %d versenyző indult.\n", testAthlon.getCompetitorNumber()));
        System.out.print("3. feladat: A versenyző azonosítója = ");
        String competitor = console.readLine();
        System.out.println(testAthlon.getAnswer(competitor));
        System.out.println("4. feladat:\n" + testAthlon.getResult(competitor));
        System.out.print("5. feladat: A feladat sorszáma = ");
        int taskNumber = console.readInt();
        System.out.println(testAthlon.getCorrectTaskAnswers(taskNumber));
        System.out.println("6. feladat: A versenyzők pontszámának meghatározása\n");
        System.out.println("7. feladat: A verseny legjobbjai:\n" + testAthlon.getWinners());
        log.printAll(testAthlon.getPoints());
    }
}
