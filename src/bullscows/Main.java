package bullscows;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final int MAX_SIZE = 36;
    static final String digits = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        int turn = 0;
        int secretLength = 1;
        int symbolsLength = 1;

        System.out.println("Please, enter the secret code's length: ");
        String input = scanner.nextLine();

        try {
            secretLength = Integer.parseInt(input);
            if (secretLength > MAX_SIZE || secretLength < 1) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code: ");
        input = scanner.nextLine();

        try {
            symbolsLength = Integer.parseInt(input);
            if (symbolsLength > MAX_SIZE || symbolsLength < 1) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
            System.exit(0);
        }

        if (secretLength > symbolsLength) {
            System.out.println("Error: it's not possible to generate a code with a length of " + secretLength + " " +
                    "with " + symbolsLength + " unique symbols.");
            System.exit(0);
        }

        String randomSecret = getRandomSecret(secretLength, symbolsLength);
        printSecret(secretLength, symbolsLength);
        System.out.println("Okay, let's start a game!");
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
            if (index == i) {
                bulls++;
            } else if (index >= 0) {
                cows++;
            }
        }

        String bullStr = bulls == 1 ? "bull" : "bulls";
        String cowStr = cows == 1 ? "cow" : "cows";

        if (bulls != 0 && cows != 0) {
            System.out.println("Grade: " + bulls + " " + bullStr + " and " + cows + " " + cowStr);
        } else if (bulls != 0) {
            System.out.println("Grade: " + bulls + " " + bullStr);
        } else if (cows != 0) {
            System.out.println("Grade: " + cows + " " + cowStr);
        } else {
            System.out.println("Grade: None");
        }
    }

    static String getRandomSecret(int length, int symbols){
        StringBuilder result = new StringBuilder();
        int random;

        while(true){
            random  = (int) (Math.random() * symbols);
            char charAt = digits.charAt(random);
            if (result.length() == 0 && charAt == '0'){
                //if it starts with 0, try again.
                continue;
            } else if (!result.toString().contains(Character.valueOf(charAt).toString())){
                result.append(charAt);
            }
            if(result.length() == length){
                break;
            }
        }

        return result.toString();
    }

    static void printSecret(int length, int symbols) {
        StringBuilder sb = new StringBuilder();
        char c = digits.charAt(symbols - 1);
        sb.append("*".repeat(length));
        sb.append(" ");
        if (symbols < 10) {
            sb.append("(0-");
            sb.append(c);
            sb.append(").");
        } else if (symbols == 10) {
            sb.append("(0-9, a).");
        } else {
            sb.append("(0-9, a-");
            sb.append(c);
            sb.append(").");
        }
        System.out.println("The secret is prepared: " + sb);
    }
}
