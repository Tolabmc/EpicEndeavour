import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        System.out.println("Welcome to the ISE quiz!");
        System.out.println("Select your game mode: ");
        System.out.println("Enter:");
        System.out.println("1 - For Random quiz");
        System.out.println("2 - For Increasing Difficulty Quiz");
        System.out.println("3 - For Timed quiz");
        System.out.println("Enter your choice here: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        Main main = new Main();
        main.quizChoice(choice);
        System.out.println("Choose another game mode");
        choice = scanner.nextInt();
        main.quizChoice(choice);
    }
    public void quizChoice(int choice) {
        if (choice == 1){
            RandomQuiz randomQuiz = new RandomQuiz();
            System.out.println("Random Quiz Selected");
        }
      /*  if (choice == 2){
            difficultyQuiz difficultyQuiz = new difficultyQuiz();
        }

       */
            if (choice == 3){
                TimedQuiz timedQuiz = new TimedQuiz();
                System.out.println("Timed Quiz Selected");
            }
            else {
                System.out.println("Invalid choice choose again");

            }
        }
    }
