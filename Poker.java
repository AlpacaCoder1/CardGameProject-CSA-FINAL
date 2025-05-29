import java.util.ArrayList;
import java.util.Scanner;

public class Poker extends Base {
    private int players;
    private int bigBlind = 100;
    private int smallBlind = 50;
    private int pot = 0;
    private int turn = 0;
    private Scanner input = new Scanner(System.in);

    private ArrayList<Card> communityCards = new ArrayList<>();

    public Poker(int p) {
        players = p;
        super.dealCards(2, players, false);
        System.out.println("Big Blind is $" + bigBlind);
        System.out.println("Small Blind is $" + smallBlind);
        playGame();
    }

    private void playGame() {
        bettingRound();

        dealFlop();
        System.out.println("Flop: " + communityCards);
        bettingRound();

        dealTurn();
        System.out.println("Turn: " + communityCards);
        bettingRound();

        dealRiver();
        System.out.println("River: " + communityCards);
        bettingRound();

        showdown();
    }

    private void dealFlop() {
        burnCard();

        for (int i = 0; i < 3; i++) {
            if (!cards.isEmpty()) {
                communityCards.add(cards.remove(cards.size() - 1));
            }
        }
    }

    private void dealTurn() {
        burnCard();
        if (!cards.isEmpty()) {
            communityCards.add(cards.remove(cards.size() - 1));
        }
    }

    private void dealRiver() {
        burnCard();
        if (!cards.isEmpty()) {
            communityCards.add(cards.remove(cards.size() - 1));
        }
    }

    private void burnCard() {
        if (!cards.isEmpty()) {
            cards.remove(cards.size() - 1);
        }
    }

    private void bettingRound() {
        System.out.println("Pot is: $" + pot);
        for (int i = 0; i < players; i++) {
            Player currentPlayer = playerS.get(i);
            if (currentPlayer.isFolded()) continue;

            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            System.out.println("Your hand: " + currentPlayer.getHand());
            System.out.println("Community Cards: " + communityCards);
            System.out.println("Your Money: $" + currentPlayer.getMoney());
            System.out.println("Options: Fold, Bet, Call, Check");
            String action = input.nextLine().trim().toLowerCase();

            switch (action) {
                case "fold":
                    currentPlayer.fold();
                    System.out.println(currentPlayer.getName() + " folds.");
                    break;
                case "bet":
                    System.out.print("Enter bet amount: ");
                    int betAmount = input.nextInt();
                    input.nextLine(); // consume newline
                    if (betAmount > currentPlayer.getMoney()) {
                        System.out.println("Not enough money.");
                        i--; // retry this player's turn
                    } else {
                        currentPlayer.placeBet(betAmount);
                        pot += betAmount;
                        System.out.println(currentPlayer.getName() + " bets $" + betAmount);
                    }
                    break;
                case "call":
                    int callAmount = bigBlind; // Simplified call amount
                    if (callAmount > currentPlayer.getMoney()) {
                        System.out.println("Not enough money to call.");
                        i--; // retry
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
                    i--; // retry this player's turn
            }
        }
    }

    private void showdown() {
        System.out.println("\nShowdown!");
        System.out.println("Community Cards: " + communityCards);
        for (Player p : playerS) {
            if (!p.isFolded()) {
                System.out.println(p.getName() + "'s hand: " + p.getHand());
                // For now, just show hands; you can add hand evaluation here
            } else {
                System.out.println(p.getName() + " folded.");
            }
        }
        System.out.print("Please input who won (Player #): ");
        String winner = input.next();
        for (Player p : playerS) {
            if (winner.equalsIgnoreCase(p.getName())) {
                p.addWinnings(pot);
                pot = 0;
                System.out.println(p.getName() + " wins the pot of $" + pot + "!");
                break;
            }
        }
    }
} 