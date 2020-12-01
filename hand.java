import java.util.*; 
public class hand {
    private card c1;
    private card c2;
    private static ArrayList<card> cardsonthetable=new ArrayList<card>();
    public hand(card card1, card card2) 
    {
        c1=card1;
        c2=card2;
        cardsonthetable.add(c1);
        cardsonthetable.add(c2);
    }

    public static void processflop(card card1, card card2, card card3)
    {
        card flop1= card1;
        card flop2= card2;
        card flop3= card3;
        cardsonthetable.add(flop1);
        cardsonthetable.add(flop2);
        cardsonthetable.add(flop3);
        //call the calculate function to see chances of winning hand
    }
    public card getc1()
    {
        return c1;
    }
    public card getc2()
    {
        return c2;
    }
    public void getHand()
    {
        System.out.println(c1.toString() + " and " + c2.toString() );
    }
}