package hu.testathlon.service;

import java.util.Scanner;

/**
 * @author Peter_Fazekas
 */
public class Console {

    private final Scanner sc;

    public Console(Scanner sc) {
        this.sc = sc;
    }

    public String readLine() {
        return sc.nextLine();
    }

    public int readInt() {
        return sc.nextInt();
    }

}
