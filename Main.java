import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Card Games ---");
            System.out.println("1. Play BlackJack");
            System.out.println("2. Play Poker");
            System.out.println("3. Play War");
            System.out.println("4. Exit");
            System.out.print("Choose a game to play (1-4): ");

            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("1")) {
                new BlackJack();
                break;
            }
            else if (choice.equalsIgnoreCase("2")) {
                System.out.print("Enter number of players (2-6): ");
                int players = 2;
                players = input.nextInt();
                if (players < 2) {
                    System.out.println("You have inputed lower than the minimun 2 players, setting to 2 players.");
                    players = 2;
                }
                new Poker(players);
                break;
            }
            else if (choice.equalsIgnoreCase("3")) {
                new War();
                break;
            }
            else if (choice.equalsIgnoreCase("4")) {
                System.out.println("Exiting the Game. Goodbye");
            }
            else {
                System.out.println("Invalid input. Select 1-4");
            }
        }
        System.out.println("Thank you for Playing!!!");
    }
}
