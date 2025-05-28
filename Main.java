import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Card Game Suite ---");
            System.out.println("1. Play BlackJack");
            System.out.println("2. Play Poker");
            System.out.println("3. Play War");
            System.out.println("4. Exit");
            System.out.print("Choose a game to play (1-4): ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    new BlackJack();
                    break;
                case "2":
                    System.out.print("Enter number of players (2-6 recommended): ");
                    int players = 2;
                    try {
                        players = Integer.parseInt(input.nextLine());
                        if (players < 2) {
                            System.out.println("Minimum 2 players, setting to 2.");
                            players = 2;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, defaulting to 2 players.");
                    }
                    new Poker(players);
                    break;
                case "3":
                    new War();
                    break;
                case "4":
                    System.out.println("Exiting. Goodbye!");
                    input.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        }
    }
}
