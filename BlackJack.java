import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends Base {
    private int bet;
    private int money = 1000;
    private Scanner input = new Scanner(System.in);

    private ArrayList<Card> dealerHand = null;
    private ArrayList<Card> playerHand = null;

    public BlackJack() {
        System.out.println("Welcome to BlackJack!!!");
        System.out.println("You have $" + money);

        while (money > 0) {
            askForBet();
            startRound();

            if (money <= 0) {
                System.out.println("You have no money. GAME OVER!!!");
                break;
            }
        }
    }

    private void askForBet() {
        System.out.print("How much do you want to bet? (int): ");
        while (true) {
            if (input.hasNextInt()) {
                bet = input.nextInt();
                input.nextLine();
                if (bet > money) {
                    System.out.println("You do not have enough money. Setting bet to $10.");
                    bet = 10;
                    break;
                }
                if (bet <= 0) {
                    System.out.println("You sneaky little cat, you really thought that you would work, didn't you? For that, I'm setting the bet to 10");
                    bet = 10;
                    break;
                }
                break;
            } else {
                System.out.print("Invalid input. Please enter a valid int");
                input.nextLine();
            }
        }
    }

    private void startRound() {
        dealCards(2, 2, false);
        dealerHand = playerS.get(0).getHand();
        playerHand = playerS.get(1).getHand();

        System.out.println("\nDealer shows: " + dealerHand.get(0));
        System.out.println("Your cards: " + playerHand);

        while (true) {
            int playerScore = getScore(playerHand);
            System.out.println("Your current score: " + playerScore);

            if (playerScore > 21) {
                System.out.println("You busted! Score: " + playerScore);
                money -= bet;
                System.out.println("You lose! Money left: $" + money);
                return;
            }

            System.out.print("Do you want to Hit or Stay (H or S)? ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("H")) {
                hit(playerHand);
                System.out.println("Your cards: " + playerHand);
            } else if (choice.equalsIgnoreCase("S")) {
                break;
            } else {
                System.out.println("Invalid input. Please type H or S.");
            }
        }

        while (getScore(dealerHand) < 17) {
            hit(dealerHand);
        }

        System.out.println("Dealer's hand: " + dealerHand);
        int dealerScore = getScore(dealerHand);
        int playerScore = getScore(playerHand);
        System.out.println("Dealer's Score: " + dealerScore);
        System.out.println("Your Score: " + playerScore);

        if (dealerScore > 21) {
            System.out.println("Dealer busted! You win!");
            money += bet;
        } else if (playerScore > dealerScore) {
            System.out.println("You win!");
            money += bet;
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
            money -= bet;
        }
        System.out.println("You have $" + money + "\n");
    }

    private int getScore(ArrayList<Card> hand) {
        int sum = 0;
        for (Card c : hand) {
            int num = c.getNumber();
            if (num > 10) num = 10;  // Face cards count as 10
            // Ace always counts as 1 now
            sum += num;
        }
        return sum;
    }

    private void hit(ArrayList<Card> hand) {
        if (!cards.isEmpty()) {
            Card newCard = cards.remove(cards.size() - 1);
            hand.add(newCard);
            System.out.println("You drew: " + newCard);
        } else {
            System.out.println("No more cards in the deck!");
        }
    }
}
