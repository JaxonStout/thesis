
import java.util.*;

public class deck {
    // the entire deck
    private ArrayList<card> deckofcards = new ArrayList<card>();
    // every card put in play-hands, burners, the board, etc to keep track so the
    // cards can be put pack when done
    private ArrayList<card> cardsinuse = new ArrayList<card>();
    // stricly whats on the board, so can be compared with hands to find out the
    // best hand
    private pokerCalculator p = new pokerCalculator();
    private ArrayList<card> board = new ArrayList<card>();
    private hand playerHand = new hand(null, null);
    private hand compHand = new hand(null, null);

    public deck() {
        for (int i = 2; i < 15; i++) {
            deckofcards.add(new card(i, "Hearts"));
        }
        for (int i = 2; i < 15; i++) {
            deckofcards.add(new card(i, "Spades"));
        }

        for (int i = 2; i < 15; i++) {
            deckofcards.add(new card(i, "Clubs"));
        }

        for (int i = 2; i < 15; i++) {
            deckofcards.add(new card(i, "Diamonds"));
        }
    }

    public int getLength() {
        return deckofcards.size();
    }

    public void shuffle() {
        int decksize = deckofcards.size();
        Random rand = new Random();
        ArrayList<card> tempDeck = new ArrayList<card>();
        for (int i = 0; i < decksize; i++) {
            int randomspot = rand.nextInt(deckofcards.size());
            tempDeck.add(deckofcards.remove(randomspot));
        }
        deckofcards = tempDeck;
    }

    public void deal() {
        // deal cards alternating players
        boolean x = true;
        ArrayList<card> drawhands = new ArrayList<card>();
        for (int i = 0; i < 4; i++) {
            card dealt = deckofcards.remove(0);
            cardsinuse.add(dealt);
            drawhands.add(dealt);
        }
        playerHand = new hand(drawhands.get(0), drawhands.get(2));
        compHand = new hand(drawhands.get(1), drawhands.get(3));

        // update GUI
    }

    public void flop() {
        // burn a card
        cardsinuse.add(deckofcards.remove(0));
        for (int i = 0; i < 3; i++) {
            card flop = deckofcards.remove(0);
            board.add(flop);
            cardsinuse.add(flop);
        }
    }

    public void river() {
        cardsinuse.add(deckofcards.remove(0));
        card river = deckofcards.remove(0);
        board.add(river);
        cardsinuse.add(river);
    }

    public void turn() {
        cardsinuse.add(deckofcards.remove(0));
        card turn = deckofcards.remove(0);
        board.add(turn);
        cardsinuse.add(turn);
    }

    public ArrayList<card> getBoard() {
        return board;
    }

    public int evaluatecalc(ArrayList<card> board, hand personhand, String result) {

        return p.evaluatehand(board, personhand);
    }

    public void printHeadsUp() {
        System.out.println("Computer Hand:");
        compHand.getHand();
        System.out.println("Board:");
        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i).toString() + " ");
        }
        System.out.println("Your Hand:");
        playerHand.getHand();
    }

    public void printDeck() {
        System.out.print(deckofcards.size());
    }

    public hand getphand() {
        return playerHand;
    }

    public hand getchand() {
        return compHand;

    }

    public void reset() {
        for (int i = 0; i < cardsinuse.size(); i++) {
            deckofcards.add(cardsinuse.remove(0));
        }
        cardsinuse.clear();
        board.clear();

        shuffle();
    }
    /**
     * public static boolean equal(deck testDeck) { boolean retval = true;
     * 
     * if(testDeck.deckofcards.size() != deckofcards.size()) { retval = false; }
     * else { for(int i = 0; i < deckofcards.size(); i++) {
     * if(deckofcards.get(i).isequal(testDeck.deckofcards.get(i)) == false) { retval
     * = false; break; } }
     * 
     * }
     * 
     * return retval; }
     */

}