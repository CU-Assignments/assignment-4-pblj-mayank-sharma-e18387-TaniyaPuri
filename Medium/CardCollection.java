import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class Main {
    private static HashMap<String, List<Card>> cardMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    searchCardsBySymbol();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Value (e.g., Ace, 2, King): ");
        String value = scanner.nextLine();

        Card card = new Card(symbol, value);

        if (!cardMap.containsKey(symbol)) {
            cardMap.put(symbol, new ArrayList<>());
        }
        cardMap.get(symbol).add(card);

        System.out.println("Card added successfully!");
    }

    private static void searchCardsBySymbol() {
        System.out.print("Enter Symbol to search (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        if (cardMap.containsKey(symbol)) {
            List<Card> cards = cardMap.get(symbol);
            System.out.println("Cards found for symbol '" + symbol + "':");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for symbol '" + symbol + "'.");
        }
    }
}
