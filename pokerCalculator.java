import java.lang.reflect.Array;
import java.util.*;

// #include <algorithm>
//#include <iostream>
public class pokerCalculator {
    private static ArrayList<card> cardsonthetable = new ArrayList<card>();
    private static int value =0;
    private  String panswer ="";
    private  String canswer ="";
    private String answer ="";
    private ArrayList<card> used = new ArrayList<card>();

    public pokerCalculator() {
        cardsonthetable = new ArrayList<card>();
    }

    // TODO:
    // answer with cards
    public String GameResult(hand phand, hand chand, ArrayList<card> board)
    {
        //the returned result
        String gameres = "";
        String playerresmessage=" You with a ";
        String compresmessage=" Computer with a ";
        //stores the string of all player cards
        String playercards="";
        String compcards = "";
        int playerresult=evaluatehand(board, phand);
        
        //kickers are the tie breaker if the type of hand and cards in hand are equal, however only found in the players hand
        card pkicker=new card(0, "null");

        //these will tell us if the cards in hand were used in play. If not, they are eligible to be a kicker
        boolean p1c1used =false;
        boolean p1c2used=false;
        boolean c1c1used =false;
        boolean c1c2used=false;
        card ckicker=new card(0, "null");
        //these will be used for a tie in hand type, however if there is a pair or number higher we will compare these two
        int pnumber=0;
        int cnumber=0;
//getting the cards used for the player so we can calculate a winner based on what was used on top of the type of hand they have
        for(int i=0;i<used.size();i++)
        {
            if(used.get(i).getValue()>pnumber)
            {
                pnumber=used.get(i).getValue();
            }
            if(used.get(i)==phand.getc1())
            {
                p1c1used=true;
            }
            if(used.get(i)==phand.getc2())
            {
                p1c2used=true;
            }
            playercards=playercards+ used.get(i).toString()+ " ";
        }
        used.clear();

        if(!p1c1used)
        {
            if(!p1c2used)
            {
                if(phand.getc1().getValue()>phand.getc2().getValue())
                {
                    pkicker=phand.getc1();
                }
                pkicker=phand.getc2();
            }
            pkicker=phand.getc1();
        }

        

        panswer=answer;
        int compresult=evaluatehand(board, chand);

        for(int i=0;i<used.size();i++)
        {
            if(used.get(i).getValue()>cnumber)
            {
                cnumber=used.get(i).getValue();
            }
            if(used.get(i)==chand.getc1())
            {
                c1c1used=true;
            }
            if(used.get(i)==chand.getc2())
            {
                c1c2used=true;
            }
            compcards=compcards+ used.get(i).toString()+" ";
        }
        if(!c1c1used)
        {
            if(!c1c2used)
            {
                if(chand.getc1().getValue()>chand.getc2().getValue())
                {
                    ckicker=chand.getc1();
                }
                ckicker=chand.getc2();
            }
            ckicker=chand.getc1();
        }
        used.clear();
        canswer=answer;
        playerresmessage=playerresmessage+panswer+" with " +playercards  ;
        compresmessage=compresmessage+canswer+" with "+ compcards ;
        
        
      
        if(playerresult<compresult)
        {
            gameres="WINNER: " +playerresmessage +" beating The " +compresmessage;
        }       
        else if (compresult<playerresult)
        {
            gameres="WINNER: The" + compresmessage +" beating " +playerresmessage;
        }
        else{
            if(playerresult==3 ||playerresult==4 ||playerresult==7 ||playerresult==8 ||playerresult==9 )
            {
                if(pnumber>cnumber)
                {
                    gameres="WINNER: " +playerresmessage +" beating The " +compresmessage;
                    return gameres;
                }
                else{
                    gameres="WINNER: The" + compresmessage +" beating " +playerresmessage;
                    return gameres;
                }
            }
            //if no hands then we get the highest card in their hands
            if(playerresult==0)
            {
                int phigh=0;
                card phighcard;
                card chighcard;
                card plowcard;
                card clowcard;
                int chigh=0;
                if(phand.getc1().getValue()>phand.getc2().getValue())
                {
                    phigh=phand.getc1().getValue();
                    phighcard=phand.getc1();
                }
                else{
                    phigh = phand.getc2().getValue();
                    phighcard=phand.getc2();
                }
                
                if(chand.getc1().getValue()>chand.getc2().getValue())
                {
                    chigh=chand.getc1().getValue();
                    chighcard=chand.getc1();
                }
                else{
                   chigh = chand.getc2().getValue();
                   chighcard=chand.getc2();
                }
                String pcard=phighcard.toString();
                String ccard=chighcard.toString();
                if(phigh>chigh)
                {
                    
                    gameres="WINNER: " +playerresmessage + " with a "+ pcard +" high card, " +" beating The computer with a " + ccard;
                
                }
                else{
                    gameres="WINNER: " +compresmessage + " with a "+ ccard +" high card, " +" beating You with a " +pcard;
                }
                
                return gameres;
            }
            if(pkicker.getValue()!=0 || ckicker.getValue()!=0)
            {
                if(pkicker.getValue()>ckicker.getValue())
                {
                    gameres="WINNER: " +playerresmessage + " with a "+ pkicker.toString() +" kicker, " +" beating The " +compresmessage + " with a "+ ckicker.toString() +" kicker, " ;
                }
                else
                {
                    gameres="WINNER: The" +compresmessage + " with a "+ ckicker.toString() +" kicker, "  +" beating  " +playerresmessage + " with a "+ pkicker.toString() +" kicker, " ;
                }
            }

            gameres="Chop Pot with " +playerresmessage +" and The" +compresmessage;
        }
        return gameres;
    }
    public int evaluatehand(ArrayList<card> board, hand personhand) {

        ArrayList<card> Array = new ArrayList<card>();
        for (int i = 0; i < board.size(); i++) {
            Array.add(board.get(i));
        }
        Array.add(personhand.getc1());
        Array.add(personhand.getc2());

        for (int i = 0; i < Array.size(); i++) {
           // System.out.print(" " +Array.get(i).toString());
        }
        System.out.println();

        
        if (Royal_Flush(Array, answer)) {
            answer = "Royal Flush";
            return 1;
        } else if (Straight(Array, answer) && flush(Array, answer)) {
            answer = "Straight Flush";
            return 2;
        } else if (FourofaKind(Array, answer)) {
            answer = "Four of a kind";
            return 3;
        } else if (Full_house(Array, answer)) {
            answer = "Full House";
            return 4;
        } else if (flush(Array, answer)) {
            answer = "Flush";
            return 5;
        } else if (Straight(Array, answer)) {
            answer = "straight";
            return 6;
        } else if (ThreeofaKind(Array, answer)) {
            answer = "3 of a kind";
            return 7;
        } else if (TwoPair(Array, answer)) {
            answer = "two pair";
            return 8;
        } else if (Pair(Array, answer)) {
            answer = "pair";
            return 9;
        }

        return 0;
        // run through and return numerical values for prio comparisons and do tie
        // breaker
    }

    int clubstart, heartstart, spadestart, diamondstart;

    boolean check_for_a_value(ArrayList<card> Array, int number) {
        for (int i = 0; i < Array.size(); i++) {
            if (Array.get(i).getValue() == number) {
                return true;
            }
        }
        return false;
    }

    boolean check_for_suit(ArrayList<card> Array, String suit) {
        for (int i = 0; i < Array.size(); i++) {
            if (Array.get(i).getSuit() == suit) {
                return true;
            }
        }
        return false;
    }

    boolean check_for_specific_card(ArrayList<card> Array, String suit, int number) {
        for (int i = 0; i < Array.size(); i++) {
            if (Array.get(i).getSuit() == suit && Array.get(i).getValue() == number) {
                return true;
            }
        }
        return false;
    }

    void sort(ArrayList<card> Array) {
        clubstart = 0;
        int iter = 0;
        card swap;
        for (int i = 1; i < Array.size(); i++) {
            if (Array.get(i).getSuit() == "Clubs") {
                swap = Array.get(iter);
                Array.set(iter, Array.get(i));
                Array.set(i, swap);
                iter++;
            }
        }

        diamondstart = iter;
        int clubiter = 0;

        for (int i = 1; i < diamondstart; i++) {
            if (Array.get(i).getValue() > Array.get(clubiter).getValue()) {
                swap = Array.get(clubiter);
                Array.set(clubiter, Array.get(i));
                Array.set(i, swap);
                clubiter++;
            }

        }
        for (int i = iter + 1; i < Array.size(); i++) {
            if (Array.get(i).getSuit() == "Hearts") {
                swap = Array.get(iter);
                Array.set(iter, Array.get(i));
                Array.set(i, swap);
                iter++;
            }
        }
        heartstart = iter;
        int diamondsiter = diamondstart;

        for (int i = diamondsiter + 1; i < heartstart; i++) {
            if (Array.get(i).getValue() > Array.get(diamondsiter).getValue()) {
                swap = Array.get(diamondsiter);
                Array.set(diamondsiter, Array.get(i));
                Array.set(i, swap);
                diamondsiter++;
            }

        }
        for (int i = iter + 1; i < Array.size(); i++) {
            if (Array.get(i).getSuit() == "Spades") {
                swap = Array.get(iter);
                Array.set(iter, Array.get(i));
                Array.set(i, swap);
                iter++;
            }
        }
        spadestart = iter;
        int heartsiter = heartstart;

        for (int i = heartsiter + 1; i < spadestart; i++) {
            if (Array.get(i).getValue() > Array.get(heartsiter).getValue()) {
                swap = Array.get(heartsiter);
                Array.set(heartsiter, Array.get(i));
                Array.set(i, swap);
                heartsiter++;
            }

        }
        int spadesiter = iter;

        for (int i = 1; i < Array.size(); i++) {
            if (Array.get(i).getValue() > Array.get(spadesiter).getValue()) {
                swap = Array.get(spadesiter);
                Array.set(spadesiter, Array.get(i));
                Array.set(i, swap);
                spadesiter++;
            }

        }

    }

    // check if you have a royal flush
    boolean Royal_Flush(ArrayList<card> Array, String answer) {
        int clubs = 0;
        int hearts = 0;
        int diamonds = 0;
        int spades = 0;
        // check for right values
        if (check_for_a_value(Array, 14) && check_for_a_value(Array, 13) && check_for_a_value(Array, 12)
                && check_for_a_value(Array, 11) && check_for_a_value(Array, 10) == true) {
            // check if there are five of the same suit
            for (int i = 0; i < Array.size(); i++) {
                if (Array.get(i).getSuit() == "Clubs") {
                    clubs++;
                }
                if (Array.get(i).getSuit() == "Hearts") {
                    hearts++;
                }
                if (Array.get(i).getSuit() == "Diamonds") {
                    diamonds++;
                }
                if (Array.get(i).getSuit() == "Spades") {
                    spades++;
                }
            }
            if (diamonds >= 5) {
                if (check_for_specific_card(Array, "Diamonds", 14) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 13) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 12) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 11) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 10) == false) {
                    return false;
                }
                used.add(new card(10, "Diamonds"));
                used.add(new card(11, "Diamonds"));
                used.add(new card(12, "Diamonds"));
                used.add(new card(13, "Diamonds"));
                used.add(new card(14, "Diamonds"));
                return true;
            } else if (clubs >= 5) {
                if (check_for_specific_card(Array, "Clubs", 14) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 13) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 12) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 11) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 10) == false) {
                    return false;
                }
                used.add(new card(10, "Clubs"));
                used.add(new card(11, "Clubs"));
                used.add(new card(12, "Clubs"));
                used.add(new card(13, "Clubs"));
                used.add(new card(14, "Clubs"));
                return true;
            } else if (spades >= 5) {
                if (check_for_specific_card(Array, "Spades", 14) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 13) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 12) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 11) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 10) == false) {
                    return false;
                }
                used.add(new card(10, "Spades"));
                used.add(new card(11, "Spades"));
                used.add(new card(12, "Spades"));
                used.add(new card(13, "Spades"));
                used.add(new card(14, "Spades"));
                return true;
            } else if (hearts >= 5) {
                if (check_for_specific_card(Array, "Hearts", 14) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 13) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 12) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 11) == false) {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 10) == false) {
                    return false;
                }
                used.add(new card(10, "Hearts"));
                used.add(new card(11, "Hearts"));
                used.add(new card(12, "Hearts"));
                used.add(new card(13, "Hearts"));
                used.add(new card(14, "Hearts"));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // check if you have a Straight
    boolean Full_house(ArrayList<card> Array, String answer) {
        // check for pair and three of a kind
        if (ThreeofaKind(Array, answer) == true)
        {
            if( Pair(Array, answer) == true) {
            answer = "Full house";
        
            value=0;
            return true;
            }
        }
        value =0;
        return false;
    }

    boolean flush(ArrayList<card> Array, String answer) {
        int hearts = 0;
        int spades = 0;
        int diamonds = 0;
        int clubs = 0;
        // check for 5 distinct suits
        for (int i = 0; i < Array.size(); i++) {
            if (Array.get(i).getSuit() == "Hearts") {
                hearts++;
            }
            if (Array.get(i).getSuit() == "Spades") {
               
                spades++;
            }
            if (Array.get(i).getSuit() == "Clubs") {
               
                clubs++;
            }
            if (Array.get(i).getSuit() == "Diamonds") {
                diamonds++;
            }
        }
        if (hearts >= 5)
        {
            for (int j=0;j<Array.size();j++)
            if(Array.get(j).getSuit()=="Hearts")
            {
                used.add(Array.get(j));
            }
            return true;
        }
        else if(diamonds>=5 ) {
            for (int j=0;j<Array.size();j++)
            if(Array.get(j).getSuit()=="Diamonds")
            {
                used.add(Array.get(j));
            }
            return true;
            
        }
        else if (clubs >= 5)
        {
            for (int j=0;j<Array.size();j++)
            if(Array.get(j).getSuit()=="Clubs")
            {
                used.add(Array.get(j));
            }
            return true;
        }
        else if (spades >= 5)
        {
            for (int j=0;j<Array.size();j++)
            if(Array.get(j).getSuit()=="Spades")
            {
                used.add(Array.get(j));
            }
            return true;
        }
        return false;
    }

    boolean FourofaKind(ArrayList<card> Array, String answer) {
        int ct = 0;
        int num = 0;
        int cardnum = 0;
        while (num < Array.size()) {
            cardnum = Array.get(num).getValue();
            for (int i = 1; i < Array.size(); i++) {
                if (Array.get(i).getValue() == Array.get(num).getValue()) {
                    ct++;
                }
            }             
            if (ct == 4) {
                used.add(Array.get(num));
                for (int i = 0; i < Array.size(); i++) {
                    if (Array.get(i).getValue() == Array.get(num).getValue()) {
                        used.add(Array.get(i));
                    }
                }      
                answer = "You have 4 " + cardnum;
                return true;
            }
            num++;
            ct=0;
        }
        return false;
    }

    boolean ThreeofaKind(ArrayList<card> Array, String answer) {
        int ct = 0;
        int num = 0;
        int cardnum = 0;
        int firstTOK = 0;
        while (num < Array.size()) {
            cardnum = Array.get(num).getValue();
            for (int i = 0; i < Array.size(); i++) 
            {
                if (Array.get(i).getValue() == cardnum) 
                {
                    ct++;
                }
            
            if (ct >= 3) {
                
                if (firstTOK != 0) {
                   
                    if (firstTOK > cardnum) {
                        answer = "You have 3 " + firstTOK;
                        value=firstTOK;
                        for(int j=0;j<Array.size();j++)
                        {
                            if (Array.get(j).getValue() == cardnum) 
                                 {
                                   used.add(Array.get(j));
                                }
                        }
                        return true;
                    } else {
                        answer = "You have 3 " + cardnum;
                        value=cardnum;
                        for(int j=0;j<Array.size();j++)
                        {
                            if (Array.get(j).getValue() == cardnum) 
                                 {
                                    used.add(Array.get(j));
                                }
                        }
                        return true;
                    }
                } else {
                    firstTOK = cardnum;
                }
            }
            
        }
        ct=0;
            num++;
            
        }
        return false;
    }

    boolean Pair(ArrayList<card> Array, String answer) {
        int ct = 0;
        int num = 0;
        int cardnum = 0;
        int pairindex=0;
        int pairindex1=0;
        int pairindex2=0;
        int firstPair=0;
        while (num < Array.size()) {
            cardnum = Array.get(num).getValue();
            for (int i = num+1; i < Array.size(); i++) {
                if (Array.get(i).getValue() == Array.get(num).getValue()) {
                   pairindex=i;
                    ct++;
                }
            }
            if (ct == 1 && cardnum!=value)
            {
                if(cardnum>firstPair)
                {
                    firstPair=cardnum;
                    pairindex1=num;
                    pairindex2=pairindex;
                }
                
            }
            num++;
            ct=0;
        }
        if(firstPair>0)
        {
                 used.add(Array.get(pairindex1));
                used.add(Array.get(pairindex2));
            answer = "You have a pair of " + firstPair;
                return true;
        }
        return false;
    }

    boolean TwoPair(ArrayList<card> Array, String answer) {
        int ct = 0;
        int num = 0;
        int cardnum = 0;
        int firstPair = 0;
        int secondPair = 0;
        while (num < Array.size()-1) {
            cardnum = Array.get(num).getValue();
            for (int i = num+1; i < Array.size(); i++) {
                if (Array.get(i).getValue() == cardnum) {
                    
                    if(firstPair!=0)
                    {
                        
                        ct++;
                        
                        secondPair=cardnum;
                        break;
                    }
                    else{
                        firstPair=cardnum;
                        ct++;
                        break;
                    }
                    
                }     
                //System.out.println(" SecondPair is "+secondPair + " firstpair is "+firstPair + "ct is" +ct);
                if(secondPair!=0)
                        {
                            for (int j = 0; j < Array.size(); j++) 
                                {
                                    if(Array.get(j).getValue() == firstPair || Array.get(j).getValue() == secondPair)
                                    {
                                       used.add(Array.get(j));
                                    }
                                }
                            return true;
                        }                                                                
            }
           
            num++;
        }
        return false;
    }

    // check if you have a Straight
    boolean Straight(ArrayList<card> Array, String answer) {
       // int ct = 0;
        int num = 0;
        int cardnum = 0;
        
        while (num < Array.size()) {
           boolean ontrack=true;
            cardnum = Array.get(num).getValue();
            int straight=0;
            while(ontrack){
                ArrayList<Integer> iterstraight = new ArrayList<Integer>();
                iterstraight.add(num);
                ontrack=false;
            for (int i = 0; i < Array.size(); i++) {
                if (Array.get(i).getValue() == cardnum-1) {
                    straight++;
                    cardnum--;
                    iterstraight.add(i);
                    ontrack=true;
                }
           
            if(straight>=4)
            {
                for(int j=0;j<iterstraight.size();j++)
                {
                   used.add(Array.get(j));
                }

                return true;
            }
        }
        }
            num++;
        }
    return false;
    }
}