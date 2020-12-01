import java.util.Scanner;

public class HeadsUpPoker {

    public static void main(String[] args) {
        deck bigDeck = new deck();
        boolean gameover = false;
        String forceQuit = "";
        Scanner in = new Scanner(System.in);
        while (gameover == false && forceQuit != "quit") {
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
            bigDeck.reset();
            pokerCalculator calc = new pokerCalculator();
            String playerResult = "";
            String compResult = "";

            System.out.print(calc.evaluatehand(bigDeck.getBoard(), bigDeck.getchand(), compResult) + compResult);
            System.out.print(calc.evaluatehand(bigDeck.getBoard(), bigDeck.getphand(), playerResult) + playerResult);

        }
        in.close();
    }
}