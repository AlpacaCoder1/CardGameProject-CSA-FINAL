// Player.java
import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private boolean folded = false; // for Poker fold status
    private ArrayList<Card> hand = new ArrayList<>();

    public Player(String n, int numCards, ArrayList<Card> deck) {
        name = n;
        money = 1000;

        // Deal cards from deck to player (remove from deck)
        for (int i = 0; i < numCards; i++) {
            if (!deck.isEmpty()) {
                hand.add(deck.remove(deck.size() - 1));
            }
        }
    }

    public Player() {
        // Default constructor (optional)
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getMoney() {
        return money;
    }

    public void placeBet(int bet) {
        if (bet <= money) {
            money -= bet;
        } else {
            System.out.println(name + " does not have enough money to bet $" + bet);
        }
    }

    public void addWinnings(int amount) {
        money += amount;
    }

    public boolean isFolded() {
        return folded;
    }

    public void fold() {
        folded = true;
    }

    public String toString() {
        return name + " | Cards: " + hand + " | Money: $" + money + (folded ? " [Folded]" : "");
    }
}
