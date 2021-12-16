import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] arg) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();
        String word;
        if (players.equals("1"))
        {
            Scanner scanner = new Scanner(new File("C:/Users/talg5/IdeaProjects/Hangman/words.txt"));

            //Add all the words to one big list
            List<String> words = new ArrayList<>();
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
            //Define difficulty and pick random word from the words list
            System.out.println("Choose Difficulty:");
            System.out.println("Press 1 for words in length 4 at most ");
            System.out.println("Press 2 for words in length 4 at least");
            String difficulty = keyboard.nextLine();

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
            if (difficulty.equals("1"))
                while (word.length() > 4)
                    word = words.get(rand.nextInt(words.size()));
        }
        else{
            System.out.println("player 1, please enter your word:");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2, Good Luck!");
        }

        List<Character> playerGuesses = new ArrayList<>();


        //Get the player guess until winning
        Integer wrongCount = 0;
        while (true) {

            printHangedMan(wrongCount);
            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }
            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                System.out.println("Wrong guess");
                wrongCount++;
            }
            printHangedMan(wrongCount);
            if (printWordState(word, playerGuesses))
                break;
            System.out.println("Please enter your guess for the word:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("Try again");
                wrongCount++;
            }

        }

    }


    //Print the current state of the word
    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else
                System.out.print("-");
        }
        System.out.println();

        return (word.length() == correctCount);
    }

    //Get the player guess for the word
    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }


    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |      |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3)
                System.out.println("/");
            else
                System.out.println("");
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6)
                System.out.println("\\");
            else
                System.out.println("");
        }

    }
}