import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String[] greetings = {"Welcome to the Game HUB!", "Hello User, We love to have you here! ;)", "What's Cookin? Good-lookin. Wanna play a Game?", "Hola! ¿Que tal tío? ¿Quieres jugar nuestro videojuego?"};
            int ind = (int) (Math.random() * 4);

            System.out.println(greetings[ind]);
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
                break;
            }
            else {
                System.out.println("Invalid input. Select 1-4");
            }
        }
        String[][] farewells = {
            {"Farewell User, Come Back Next Time!", "Arrivederci amor, Let's play again sometime!!!"},
            {"Bye-Bye! ;)", "See you later, Alligator!!"}
        };
            int row = (int) (Math.random() * 2);
            int col = (int) (Math.random() * 2);
            System.out.println(farewells[row][col]);
    }
}
