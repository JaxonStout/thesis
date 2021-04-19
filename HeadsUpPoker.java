import java.util.Scanner;
import java.util.*;

public class HeadsUpPoker {
    
    public static void main(String[] args) {
        deck bigDeck = new deck();
        boolean gameover = false;
        String forceQuit = "";
        Scanner in = new Scanner(System.in);
        
        while (gameover == false && forceQuit != "quit")   {
            bigDeck.shuffle();

            bigDeck.deal();
            in.nextLine();

            bigDeck.printHeadsUp();
            bigDeck.flop();

            in.nextLine();

            bigDeck.printHeadsUp();
            bigDeck.river();
            in.nextLine();

            bigDeck.printHeadsUp();
            bigDeck.turn();
            in.nextLine();
            bigDeck.printHeadsUp();
            

            pokerCalculator calc = new pokerCalculator();
            String playerResult = "";
            String compResult = "";
            ArrayList<card> board = bigDeck.getBoard();
/*
            card p1test1 = new card(14, "Hearts");
            card p1test2 = new card(12, "Diamonds");
            card p2test1 = new card(7, "Hearts");

            card p2test2 = new card(10, "Hearts");

            card b1 = new card(12, "Hearts");
            card b2 = new card(7, "Clubs");
            card b3 = new card(10, "Spades");
            card b4 = new card(10, "Diamonds");
            card b5 = new card(5, "Hearts");
            ArrayList<card> testboard = new ArrayList<card>();
            testboard.add(b1);
            testboard.add(b2);
            testboard.add(b3);
            testboard.add(b4);
            testboard.add(b5);

            hand testphand= new hand(p1test1, p1test2);
            hand testchand = new hand (p2test1, p2test2);
            */
            System.out.print(calc.GameResult(bigDeck.getphand(), bigDeck.getchand(), bigDeck.getBoard()));
            //System.out.print(calc.evaluatehand(board, bigDeck.getchand(), compResult) + compResult);
            //System.out.print(calc.evaluatehand(board, bigDeck.getphand(), playerResult) + playerResult);
            
            bigDeck.reset();
        }
        in.close();
    }

}