// BlackJack.java
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends Base {
    private int bet;
    private int money = 1000;
    private Scanner input = new Scanner(System.in);

    public BlackJack() {
        super.dealCards(2, 2, false);  // Dealer and player get 2 cards each
        System.out.println("Welcome to BlackJack!!!");
        System.out.println("You have $" + money);
        System.out.print("How much do you want to bet? (int): ");
        bet = input.nextInt();
        input.nextLine();  // consume newline

        if (bet > money) {
            System.out.println("You do not have enough money. Setting bet to $10.");
            bet = 10;
        }

        playGame();
    }

    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;

    private void playGame() {
        dealerHand = playerS.get(0).getHand();
        playerHand = playerS.get(1).getHand();

        System.out.println("Dealer shows: " + dealerHand.get(0));
        System.out.println("Your cards: " + playerHand);

        while (true) {
            System.out.println("Your current score: " + getScore(playerHand));
            System.out.print("Do you want to Hit or Stay (H or S)? ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("H")) {
                hit(playerHand);
                if (getScore(playerHand) > 21) {
                    System.out.println("You busted! Score: " + getScore(playerHand));
                    money -= bet;
                    System.out.println("You lose! Money left: $" + money);
                    return;
                }
            } else if (choice.equalsIgnoreCase("S")) {
                break;
            } else {
                System.out.println("Invalid input. Please type H or S.");
            }
        }

        // Dealer plays: dealer hits on < 17
        while (getScore(dealerHand) < 17) {
            hit(dealerHand);
        }
        System.out.println("Dealer's hand: " + dealerHand + " Score: " + getScore(dealerHand));

        int playerScore = getScore(playerHand);
        int dealerScore = getScore(dealerHand);

        if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("You Win!");
            money += bet;
        } else if (playerScore == dealerScore) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer Wins!");
            money -= bet;
        }

        System.out.println("Your money: $" + money);
    }

    private int getScore(ArrayList<Card> hand) {
        int sum = 0;
        int aceCount = 0;
        for (Card c : hand) {
            int num = c.getNumber();
            if (num > 10) num = 10;  // Face cards count as 10
            if (num == 1) aceCount++;
            sum += num;
        }
        // Handle Aces: count as 11 if it doesn't bust
        while (aceCount > 0 && sum + 10 <= 21) {
            sum += 10;
            aceCount--;
        }
        return sum;
    }

    private void hit(ArrayList<Card> hand) {
        if (!cards.isEmpty()) {
            Card newCard = cards.remove(cards.size() - 1);
            hand.add(newCard);
            System.out.println("You drew: " + newCard);
        }
    }
}