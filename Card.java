public class Card
    {
        private String suit;
        private int number;
        public Card(int num, String s)
        {
          suit = s;
          number = num;
        }
        public String toString() {
            return number + " of " + suit;
        }
        public int getNumber() {
          return number;
        }
    }


