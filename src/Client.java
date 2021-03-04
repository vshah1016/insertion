import java.util.Scanner;

public class Client {
    final Scanner scanner = new Scanner(System.in);

    private int answer = 0;

    public String gatherInput() {
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    public boolean isRunAgain() {
        return !(answer == 0);
    }

    public int prompt() {
        System.out.println("0. Exit");
        System.out.println("1. Input Number");
        System.out.println("2. Reset Numbers");
        System.out.println("3. Print Numbers (Sorted)");
        System.out.println("4. Output mean");
        System.out.println("5. Output median");
        System.out.println("6. Output mode");
        System.out.println("7. Output Standard Deviation");
        System.out.print("Which one would you like to select? (0-7): ");

        answer = scanner.nextInt();
        if (answer > 7 || answer < 0) {
            answer = 0;
            System.out.println("Invalid, exiting.");
        }
        System.out.println();
        scanner.nextLine();
        return answer;
    }
}