import java.util.ArrayList;
import java.util.Scanner;

public class Poker extends Base {
    private int players;
    private int bigBlind = 100;
    private int smallBlind = 50;
    private int pot = 0;
    private int turn = 0;
    private boolean displayCards = false;
    private Scanner input = new Scanner(System.in);

    public Poker(int p) {
        players = p;
        super.dealCards(2, players, false);
        System.out.println("Big Blind is $" + bigBlind);
        System.out.println("Small Blind is $" + smallBlind);
        playGame();
    }

    private void playGame() {
        while (true) {
            Player currentPlayer = playerS.get(turn);
            if (currentPlayer.isFolded()) {
                turn = (turn + 1) % players;
                continue;
            }

            System.out.println(currentPlayer.getName() + "'s turn.");
            System.out.println("Your hand: " + currentPlayer.getHand());
            System.out.println("Pot: $" + pot);
            System.out.println("Options: Fold, Bet, Call, Check");
            String action = input.nextLine().toLowerCase();

            switch (action) {
                case "fold":
                    currentPlayer.fold();
                    System.out.println(currentPlayer.getName() + " folds.");
                    break;
                case "bet":
                    System.out.print("Enter bet amount: ");
                    int betAmount = input.nextInt();
                    input.nextLine();
                    if (betAmount > currentPlayer.getMoney()) {
                        System.out.println("Not enough money.");
                    } else {
                        currentPlayer.placeBet(betAmount);
                        pot += betAmount;
                        System.out.println(currentPlayer.getName() + " bets $" + betAmount);
                    }
                    break;
                case "call":
                    // For simplicity, just add bigBlind amount to pot
                    int callAmount = bigBlind;
                    if (callAmount > currentPlayer.getMoney()) {
                        System.out.println("Not enough money to call.");
                    } else {
                        currentPlayer.placeBet(callAmount);
                        pot += callAmount;
                        System.out.println(currentPlayer.getName() + " calls $" + callAmount);
                    }
                    break;
                case "check":
                    System.out.println(currentPlayer.getName() + " checks.");
                    break;
                default:
                    System.out.println("Invalid action. Please choose Fold, Bet, Call, or Check.");
                    continue;
            }

            // Simple rotation
            turn = (turn + 1) % players;

            if (allPlayersFoldedOrOneLeft()) {
                System.out.println("Round over!");
                displayWinner();
                break;
            }
        }
    }

    private boolean allPlayersFoldedOrOneLeft() {
        int activePlayers = 0;
        for (Player p : playerS) {
            if (!p.isFolded()) activePlayers++;
        }
        return activePlayers <= 1;
    }

    private void displayWinner() {
        for (Player p : playerS) {
            if (!p.isFolded()) {
                System.out.println(p.getName() + " wins the pot of $" + pot + "!");
                p.addWinnings(pot);
                pot = 0;
                return;
            }
        }
        System.out.println("No winner this round.");
    }
}