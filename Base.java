import java.util.ArrayList;

public class Base {
    protected ArrayList<Card> cards = new ArrayList<>();
    protected ArrayList<Player> playerS = new ArrayList<>();

    public Base() {
    }

    public void shuffleCards() {
        cards.clear();
        String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
        for (String suit : suits) {
            for (int r = 1; r <= 13; r++) {
                cards.add(new Card(r, suit));
            }
        }

        // Shuffle by swapping random cards 52 times
        for (int i = 0; i < 52; i++) {
            int x = (int) (Math.random() * cards.size());
            int y = (int) (Math.random() * cards.size());
            Card temp = cards.get(x);
            cards.set(x, cards.get(y));
            cards.set(y, temp);
        }
    }

    /**
     * Deal cards to players.
     * @param num number of cards per player (ignored if wantEqual=true)
     * @param players number of players
     * @param wantEqual if true, deal equal cards (num overridden)
     */
    public void dealCards(int num, int players, boolean wantEqual) {
        shuffleCards();
        playerS.clear();
        if (wantEqual) {
            num = cards.size() / players;
        }
        for (int i = 0; i < players; i++) {
            playerS.add(new Player("Player " + (i + 1), num, cards));
        }
    }

    public ArrayList<Card> getBase() {
        return cards;
    }

    public ArrayList<Player> getPlayers() {
        return playerS;
    }
}