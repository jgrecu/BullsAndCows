package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        //String randNum = "9305";
        //String guess = scanner.next();

        int secretLength = scanner.nextInt();
        if (secretLength > 0 && secretLength < 11) {
            String randomNum = getRandomSecret(secretLength);
            System.out.println("The random secret number is " + randomNum + ".");
        } else {
            System.out.println("Error: can't generate a secret number with a length of " + secretLength +
                    " because there aren't enough unique digits.");
        }
        scanner.close();

    }

    public static void calculateScore(String randNum, String guess) {
        int bulls = 0;
        int cows = 0;
        String answer = "";

        for (int i = 0; i < guess.length(); i++) {
            int index = randNum.indexOf(guess.charAt(i));
            if (index == i) {
                bulls++;
            } else if (index >= 0) {
                cows++;
            }
        }

        if (bulls != 0 && cows != 0) {
            answer = bulls + " bull(s) and " + cows + " cow(s)";
        } else if (bulls != 0) {
            answer = bulls + " bull(s)";
        } else if (cows != 0) {
            answer = cows + " cow(s)";
        } else {
            answer = "None";
        }
        System.out.println("Grade: " + answer + ". The secret code is " + randNum + ".");
    }

    public static String getRandomSecret(int secretLength) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long pseudoRandomNumber = System.nanoTime();
            String pRN = Long.valueOf(pseudoRandomNumber).toString();
            sb.setLength(0);
            for (int i = 0; i < pRN.length() - 4; i++) {
                String digit = Character.valueOf(pRN.charAt(i + 4)).toString();
                if (sb.indexOf(digit) < 0) {
                    sb.append(digit);
                }
                if (sb.length() == secretLength) {
                    break;
                }
            }
            if (sb.length() == secretLength) {
                break;
            }
        }
        return sb.toString();
    }
}
