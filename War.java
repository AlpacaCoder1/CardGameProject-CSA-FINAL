import java.util.ArrayList;
import java.util.Scanner;

public class War extends Base {
    private int p1d = 0;
    private int p2d = 0;
    private Scanner input = new Scanner(System.in);

    public War() {
        dealCards(0, 2, true);
        playGame();
    }

    private void playGame() {
        int turn = 0;
        Player p1 = playerS.get(0);
        Player p2 = playerS.get(1);

        while (turn < 26 && !p1.getHand().isEmpty() && !p2.getHand().isEmpty()) {
            System.out.println("Play next turn? Yes or No (Y/N)");
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                Card c1 = p1.getHand().remove(0);
                Card c2 = p2.getHand().remove(0);

                System.out.println("Player 1 plays: " + c1);
                System.out.println("Player 2 plays: " + c2);

                if (c1.getNumber() > c2.getNumber()) {
                    System.out.println("Player 1 wins the round!");
                    p1d++;
                } else if (c1.getNumber() < c2.getNumber()) {
                    System.out.println("Player 2 wins the round!");
                    p2d++;
                } else {
                    System.out.println("It's a tie!");
                }

                turn++;
                System.out.println("Score: Player 1 - " + p1d + ", Player 2 - " + p2d);
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("You have stopped playing");
                break;
            }
            else {
                System.out.println("Invalid Input. Please Input Y or N.");
            }
        }

        if (p1d > p2d) {
            System.out.println("Player 1 wins the game!");
        } else if (p2d > p1d) {
            System.out.println("Player 2 wins the game!");
        } else {
            System.out.println("The game is a tie!");
        }
    }
}