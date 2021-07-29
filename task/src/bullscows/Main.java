package bullscows;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int turn = 0;
        int secretLength;

        while (true) {
            System.out.println("Please, enter the secret code's length: ");

            secretLength =  Integer.parseInt(scanner.nextLine());
            if (secretLength < 11) {
                break;
            } else {
                System.out.println("Error: can't generate a secret number with a length of " + secretLength +
                        " because there aren't enough unique digits.");
            }
        }
        System.out.println("Okay, let's start a game!");
        String randomSecret = getRandomSecret(secretLength);
        String guess = "";
        while (!guess.equals(randomSecret)) {
            turn++;
            System.out.println("Turn " + turn + ":");
            guess = scanner.nextLine();
            calculateScore(randomSecret,guess);
        }
        scanner.close();
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static void calculateScore(String randNum, String guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < guess.length(); i++) {
            int index = randNum.indexOf(guess.charAt(i));
            //System.out.println(guess.charAt(i) + " " + index);
            if (index == i) {
                bulls++;
            } else if (index >= 0) {
                cows++;
            }
        }

        String bull = bulls == 1 ? "bull" : "bulls";
        String cow = cows == 1 ? "cow" : "cows";

        if (bulls != 0 && cows != 0) {
            System.out.println("Grade: " + bulls + " " + bull + " and " + cows + " " + cow);
        } else if (bulls != 0) {
            System.out.println("Grade: " + bulls + " " + bull);
        } else if (cows != 0) {
            System.out.println("Grade: " + cows + " " + cow);
        } else {
            System.out.println("Grade: None");
        }
    }

    public static String getRandomSecret(int secretLength) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long pseudoRandomNumber = System.nanoTime();
            String pseudoRandNumStr = Long.valueOf(pseudoRandomNumber).toString();
            sb.setLength(0);
            for (int i = 0; i < pseudoRandNumStr.length() - 4; i++) {
                String digit = Character.valueOf(pseudoRandNumStr.charAt(i + 4)).toString();
                if (sb.length() == 0 && digit.equals("0")) {
                    continue;
                } else if (sb.indexOf(digit) < 0) {
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
