import java.util.Random;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

//when get a correct letter, find # of letters in the word and increment correctCount by that #

public class myHangman {
    public static int numGuesses = 10;
    public static int correctCount = 0;
    public static String correctGuess = "";
    public static String guess;
    public static int repeatCnt;
    public static int leftCount;
    public static String[] displayWord;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = {"awkward", "bagpipes", "dwarves", "gypsy", "sphinx"};
        String currentWord = words[new Random().nextInt(words.length)];
        boolean solved = false;
        displayWord = new String[currentWord.length()];

        System.out.println("Welcome! Lets play Hangman\n");
        System.out.println(currentWord + "\n");
        initialDisplay(currentWord);

        do {
            System.out.println("\n\nGuess a letter");
            guess = scanner.nextLine();
            boolean isCorrect = currentWord.contains(guess);
            printMessages(isCorrect, currentWord);
            solved = gameOver(numGuesses, correctCount, currentWord);
        } while(numGuesses > 0 && !solved);

    }

    //method displays underscores /
    public static void initialDisplay(String currWord) {
        for(int x = 0; x < currWord.length(); x++) {
            displayWord[x] = "_";
            System.out.print(displayWord[x] + " ");
        }
    }

    //prints messages and updates number of guesses and correct guess counts
    public static void printMessages(boolean ifCorrect, String currWord) {
        if(ifCorrect) {
            System.out.println("Correct! NICE");
            System.out.println("\nNumber of guesses left: " + numGuesses);
            check(currWord);
            System.out.println("Letters left in word: " + leftCount);
            tempDisplays(currWord);
        } else {
            System.out.println("BOO! Wrong!");
            System.out.println("\nNumber of guesses left: " + --numGuesses);
            System.out.println("Letters left in word: " + leftCount);
            tempDisplays(currWord);
        }
    }

    public static void check(String currWord) {
        if(!correctGuess.contains(guess)) {
            correctGuess += guess;
            updateCorrectCnt((currWord));
        }
    }

    public static void tempDisplays(String currWord) {
        //get indexes of guessed letter in word
        int index = currWord.indexOf(guess);
        while (index >= 0) {
            displayWord[index] = guess;
            index = currWord.indexOf(guess, index + 1);
        }

        //display
        for(int x = 0; x < currWord.length(); x++) {
            System.out.print(displayWord[x] + " ");
        }
    }

    public static void updateCorrectCnt(String currWord) {
        //get number of times letter is in word
        repeatCnt = StringUtils.countMatches(currWord, guess);
        //updates correctCount with number
        correctCount += repeatCnt;
        //leftCount is length of the word minus number of correct letters they've guessed (for display)
        leftCount = currWord.length() - correctCount;
    }

    // Build a method that checks if lives are depleted or all letters are guessed and returns a boolean.
    public static boolean gameOver(int numberGuesses, int correctCnt, String currWord) {
        if(correctCnt == currWord.length()) {
            System.out.println("\nYOU WIN! You've guessed all the letters in the word " + currWord);
            return true;
        } else if(numberGuesses == 0){
            System.out.println("\n" + numberGuesses + " GUESSES LEFT, GAME OVER!");
            return true;
        }
        return false;
    }

}


