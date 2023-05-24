package org.regsys.regbook;

import java.util.Scanner;

public class AskQuestion {
    static Scanner scanner = new Scanner(System.in);

    public static String ask(String question){
        System.out.print(question + " (tak/nie): ");
        return scanner.nextLine();
    }
}
