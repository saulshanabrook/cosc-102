/*
 *
 *  GuessingGame.java
 *
 *
 * Author: Saul Shanabrook
 *
 * Hours spent in total:
 *
 * I took some of the code from the Javanotes version of the game
 * at http://math.hws.edu/javanotes/c4/s2.html.
 *
 * I also used the official Java docs for Enum and `format` refernce.
 * Info on casting `int` to `Enum` from http://stackoverflow.com/a/8762387/907060
 * How to import `println` http://stackoverflow.com/questions/7277757/why-cant-i-import-static-java-lang-system-out-println#comment8762951_7277780
 * Get min/max of list http://stackoverflow.com/a/1486010/907060
 * random number method from http://stackoverflow.com/a/363692/907060
 * passing an array literal http://stackoverflow.com/a/7547772/907060
 */

import java.util.Scanner;  // needed for getting console input
import static java.lang.System.out;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GuessingGame {

    static final Scanner scan = new Scanner(System.in);
    static final Random rand = new Random();

    static enum gameChoice {USER_GUESSES, COMP_GUESSES, NONE}
    static gameChoice currentGameChoice;

    static int lowestPossible;
    static int highestPossible;
    static boolean guessIsRight;
    static Integer currentGuess;
    static Integer currentNumber;
    static int currentNumberTries;
    static int currentHighGuess;
    static int currentLowGuess;


    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }


    public static int getInteger() {
        while (!scan.hasNextInt()) {
            out.println("error: please enter an integer");
            scan.nextLine();
        }
        int n = scan.nextInt();
        scan.nextLine();
        return n;
    }

    public static int getInteger(int min, int max) {
        while(true){
            int userSubmission = getInteger();
            if (userSubmission >= min && userSubmission <= max) {
                return userSubmission;
            }
            out.format("error: integer must be between %d and %d.%n", min, max);
        }
    }

    public static int chooseOption(String[] options) {
        for (int i = 0; i < options.length; i++) {
            out.println((i + 1) + ") " + options[i]);
        }
        return getInteger(1, options.length + 1) - 1;
    }

    public static int halfwayBetween(int low, int high) {
        return low + ((high - low) / 2);
    }

    /************************************************************************/

    private static void chooseGame() {
        out.println("Would you like to play a number guessing game with me?");

        int choiceInt = chooseOption(
                new String[] {
                        "Yes, and I want to guess your number.",
                        "Yes, and I want you to guess my number.",
                        "Nope, I am done, get me out of here."
                }
        );
        currentGameChoice = gameChoice.values()[choiceInt];
    }

    /************************************************************************/

    private static void resetPreviousGame() {
        out.println("Enter high and low numbers for guessing range.");
        List<Integer> possibleRange = Arrays.asList(getInteger(), getInteger());
        lowestPossible = Collections.min(possibleRange);
        highestPossible = Collections.max(possibleRange);

        guessIsRight = false;
        currentGuess = null;
        currentNumber = randInt(lowestPossible, highestPossible);
        currentNumberTries = 0;
        currentLowGuess = lowestPossible;
        currentHighGuess = highestPossible;
    }


    private static void guessNumber() {
        switch (currentGameChoice){
            case USER_GUESSES:
                out.println("Enter your guess.");
                currentGuess = getInteger();
                break;
            case COMP_GUESSES:
                currentGuess = halfwayBetween(currentLowGuess, currentHighGuess);
        }
    }

    private static void checkGuess() {
        switch (currentGameChoice){
            case USER_GUESSES:
                guessIsRight = currentGuess == currentNumber;
                break;
            case COMP_GUESSES:
                out.println("I guess " + currentGuess);
                out.println("Am I right?");
                guessIsRight = chooseOption(new String[] {"Yes", "No"}) == 0;

        }
    }

    private static void showGuessStats() {
        out.format("That took %d tries.%n", currentNumberTries);
    }


    private static void doHint() {
        switch (currentGameChoice){
            case USER_GUESSES:
                boolean tooLow = currentGuess < currentNumber;
                out.println("That guess is too " + (tooLow ? "low." : "high."));
                break;
            case COMP_GUESSES:
                out.println("That guess is too:");
                boolean tooHigh = chooseOption(new String[] {"low.", "high."}) != 0;
                if (tooHigh) {
                    currentHighGuess = currentGuess;
                } else {
                    currentLowGuess = currentGuess;
                }
        }
    }

    private static void playGame() {
        resetPreviousGame();

        while (true) {
            currentNumberTries++;
            guessNumber();
            checkGuess();
            if (guessIsRight) {
                showGuessStats();
                return;
            }
            doHint();
        }
    }

    /************************************************************************/

    public static void main(String[] args) {
        chooseGame();
        while (currentGameChoice != gameChoice.NONE) {
            playGame();
            chooseGame();
        }

    }

}

