package bullscows;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final int MAX_SIZE = 36;
    static final String digits = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        int turn = 0;
        int secretLength;
        int symbolsLength;

        while (true) {
            System.out.println("Please, enter the secret code's length: ");

            secretLength =  Integer.parseInt(scanner.nextLine());
            if (secretLength <= MAX_SIZE) {
                break;
            } else {
                System.out.println("Error: can't generate a secret number with a length of " + secretLength +
                        " because there aren't enough unique digits.");
            }
        }
        while (true) {
            System.out.println("Input the number of possible symbols in the code: ");

            symbolsLength =  Integer.parseInt(scanner.nextLine());
            if (symbolsLength <= MAX_SIZE) {
                break;
            } else {
                System.out.println("Error: can't generate a secret number with a length of " + symbolsLength +
                        " because there aren't enough unique digits.");
            }
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
