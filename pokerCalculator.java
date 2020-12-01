import java.util.*; 
// #include <algorithm>
//#include <iostream>
public class pokerCalculator 
{
    private static ArrayList<card> cardsonthetable=new ArrayList<card>();
    public pokerCalculator()
    {
        cardsonthetable=new ArrayList<card>();
    }

//TODO: 
// answer with cards
//FING TEST
//that is all dummy
    public int evaluatehand(ArrayList<card> board, hand personhand, String result)
    {
        
        
        ArrayList<card> Array=new ArrayList<card>(board);
        Array.add(personhand.getc1());
        Array.add(personhand.getc2());
for(int i=0; i<Array.size();i++)
{
    System.out.print("What evhand thinks: " + Array.get(i).toString());
}

        String answer="";
        if(Royal_Flush(Array, answer))
        { 
            result="royal flush";
            return 1;
        }
        else if(Straight(Array, answer) && flush(Array, answer))
        {
            result="straight flush";
            return 2;
        }
       else if(FourofaKind(Array, answer))
        {
            result="Four of a kind";
            return 3;
        }
        else if(Full_house(Array, answer))
        {
            result="Full House";
            return 4;
        }  
        else if(flush(Array, answer))
        {
            result="Flush";
            return 5;
        }
        else if(Straight(Array, answer))
        {
            result="straight";
            return 6;
        }
        else if(ThreeofaKind(Array, answer))
        {
            result="3 of a kind";
            return 7;
        }
        else if(TwoPair(Array, answer))
        { 
            result="two pair";
            return 8;
        }
        else if(Pair(Array, answer))
        {
            result="pair";
            return 9;
        }
       
        return 0;
        //run through and return numerical values for prio comparisons and do tie breaker
    }
    int clubstart, heartstart, spadestart, diamondstart;
    boolean check_for_a_value(ArrayList<card> Array,int number)
    {
        for (int i = 0; i < Array.size(); i++)
        {
            if (Array.get(i).getValue() == number)
            {
                return true;
            }
        }
        return false;
    }
    
    boolean check_for_suit(ArrayList<card> Array, String suit)
    {
        for (int i = 0; i < Array.size(); i++)
        {
            if (Array.get(i).getSuit() == suit)
            {
                return true;
            }
        }
        return false;
    }
    boolean check_for_specific_card(ArrayList<card> Array, String suit,int number)
    {
        for (int i = 0; i < Array.size(); i++)
        {
            if (Array.get(i).getSuit() == suit && Array.get(i).getValue()==number)
            {
                return true;
            }
        }
        return false;
    }
    void sort(ArrayList<card> Array)
    {
        clubstart = 0;
        int iter = 0;
        card swap;
        for (int i = 1; i < Array.size(); i++)
        {
            if (Array.get(i).getSuit() == "Clubs")
            {
                swap = Array.get(iter);
                Array.set(iter, Array.get(i));
                Array.set(i,swap);
                iter++;
            }
        }
    
        diamondstart = iter;
        int clubiter = 0;
    
        for (int i = 1; i < diamondstart; i++)
        {
            if (Array.get(i).getValue() > Array.get(clubiter).getValue())
            {
                swap = Array.get(clubiter);
                Array.set(clubiter, Array.get(i));
                Array.set(i,swap);
                clubiter++;
            }
    
        }
        for (int i = iter + 1; i < Array.size(); i++)
        {
            if (Array.get(i).getSuit() == "Hearts")
            {
                swap = Array.get(iter);
                Array.set(iter, Array.get(i));
                Array.set(i,swap);
                iter++;
            }
        }
        heartstart = iter;
        int diamondsiter = diamondstart;
    
        for (int i = diamondsiter + 1; i < heartstart; i++)
        {
            if (Array.get(i).getValue() > Array.get(diamondsiter).getValue())
            {
                swap = Array.get(diamondsiter);
                Array.set(diamondsiter, Array.get(i));
                Array.set(i,swap);
                diamondsiter++;
            }
    
        }
        for (int i = iter + 1; i < Array.size(); i++)
        {
            if (Array.get(i).getSuit() == "Spades")
            {
                swap = Array.get(iter);
                Array.set(iter, Array.get(i));
                Array.set(i,swap);
                iter++;
            } 
        }
        spadestart = iter;
        int heartsiter = heartstart;
    
        for (int i = heartsiter + 1; i < spadestart; i++)
        {
            if (Array.get(i).getValue() > Array.get(heartsiter).getValue())
            {
                swap = Array.get(heartsiter);
                Array.set(heartsiter, Array.get(i));
                Array.set(i,swap);
                heartsiter++;
            }
    
        }
        int spadesiter = iter;
    
        for (int i = 1; i < Array.size(); i++)
        {
            if (Array.get(i).getValue() > Array.get(spadesiter).getValue())
            {
                swap = Array.get(spadesiter);
                Array.set(spadesiter, Array.get(i));
                Array.set(i,swap);
                spadesiter++;
            }
    
        }
    
    
    }
    //check if you have a royal flush
    boolean Royal_Flush(ArrayList<card> Array,String answer)
    {
        int clubs = 0;
        int hearts = 0;
        int diamonds = 0;
        int spades = 0;
        // check for right values
        if (check_for_a_value(Array, 14) && check_for_a_value(Array, 13) && check_for_a_value(Array, 12) && check_for_a_value(Array, 11) && check_for_a_value(Array, 10) == true)
        {
            //check if there are five of the same suit
            for (int i = 0; i < Array.size(); i++)
            {
                if (Array.get(i).getSuit() == "Clubs")
                {
                    clubs++;
                }
                if (Array.get(i).getSuit() == "Hearts")
                {
                    hearts++;
                }
                if (Array.get(i).getSuit() == "Diamonds")
                {
                    diamonds++;
                }
                if (Array.get(i).getSuit() == "Spades")
                {
                    spades++;
                }
            }
            if (diamonds >= 5)
            {
                if (check_for_specific_card(Array, "Diamonds", 14) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 13) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 12) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 11) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Diamonds", 10) == false)
                {
                    return false;
                }
                answer = "Your best hand is a royal flush with the Ace,Jack,Queen,King, and 10 of Diamonds";
                return true;
            }
            else if (clubs >= 5)
            {
                if (check_for_specific_card(Array, "Clubs", 14) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 13) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 12) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 11) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Clubs", 10) == false)
                {
                    return false;
                }
                answer = "Your best hand is a royal flush with the Ace,Jack,Queen,King, and 10 of Clubs";
                return true;
            }
            else if (spades >= 5)
            {
                if (check_for_specific_card(Array, "Spades", 14) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 13) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 12) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 11) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Spades", 10) == false)
                {
                    return false;
                }
                answer = "Your best hand is a royal flush with the Ace,Jack,Queen,King, and 10 of Spades";
                return true;
            }
            else if (hearts >= 5)
            {
                if (check_for_specific_card(Array, "Hearts", 14) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 13) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 12) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 11) == false)
                {
                    return false;
                }
                if (check_for_specific_card(Array, "Hearts", 10) == false)
                {
                    return false;
                }
                answer = "Your best hand is a royal flush with the Ace,Jack,Queen,King, and 10 of Hearts";
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    //check if you have a Straight
    boolean Full_house(ArrayList<card> Array, String answer)
    {
        //check for pair and three of a kind
        if (ThreeofaKind(Array,answer) == true && Pair(Array,answer) == true)
        {
            answer = "you have a full house";
            return true;
        }
        return false;
    }
    
    boolean flush(ArrayList<card> Array, String answer)
    {
        int hearts=0;
        int spades=0;
        int diamonds=0;
        int clubs=0;
        //check for 5 distinct suits
        for (int i = 0; i < Array.size(); i++)
        {
            if (Array.get(i).getSuit() == "Hearts")
            {
                hearts++;
            }
            if (Array.get(i).getSuit() == "Spades")
            {
                spades++;
            }
            if (Array.get(i).getSuit() == "Clubs")
            {
                clubs++;
            }
            if (Array.get(i).getSuit() == "Diamonds")
            {
                diamonds++;
            }
        }
        if (hearts >= 5)
        {
            /*
            int index = 0;
            boolean done = false;
            String first;
            String second;
            String third;
            String fourth;
            String fifth;
            //fill first
            while (done == true)
            {
                if (Array[index].setSuit == "Hearts")
                {
                    first = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill second
            while (done == true)
            {
                if (Array[index].setSuit == "Hearts")
                {
                    second = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill third
            while (done == true)
            {
                if (Array[index].setSuit == "Hearts")
                {
                    third = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fourth
            while (done == true)
            {
                if (Array[index].setSuit == "Hearts")
                {
                    fourth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fifth
            while (done == true)
            {
                if (Array[index].setSuit == "Hearts")
                {
                    fifth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            answer = "you have a flush consisting of ";
            answer += first;
            answer += ",";
            answer += second;
            answer += ",";
            answer += third;
            answer += ",";
            answer += fourth;
            answer += ", and a";
            answer += fifth;
            return true;
        }
        //check for spades
        if (spades >= 5)
        {
            int index = 0;
            boolean done = false;
            String first;
            String second;
            String third;
            String fourth;
            String fifth;
            //fill first
            while (done == true)
            {
                if (Array[index].setSuit == "Spades")
                {
                    first = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill second
            while (done == true)
            {
                if (Array[index].setSuit == "Spades")
                {
                    second = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill third
            while (done == true)
            {
                if (Array[index].setSuit == "Spades")
                {
                    third = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fourth
            while (done == true)
            {
                if (Array[index].setSuit == "Spades")
                {
                    fourth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fifth
            while (done == true)
            {
                if (Array[index].setSuit == "Spades")
                {
                    fifth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            answer = "you have a flush consisting of ";
            answer += first;
            answer += ",";
            answer += second;
            answer += ",";
            answer += third;
            answer += ",";
            answer += fourth;
            answer += ", and a";
            answer += fifth;
    
            return true;
        }
        //clubs
        if (clubs >= 5)
        {
            int index = 0;
            boolean done = false;
            String first;
            String second;
            String third;
            String fourth;
            String fifth;
            //fill first
            while (done == true)
            {
                if (Array[index].setSuit == "Clubs")
                {
                    first = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill second
            while (done == true)
            {
                if (Array[index].setSuit == "Clubs")
                {
                    second = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill third
            while (done == true)
            {
                if (Array[index].setSuit == "Clubs")
                {
                    third = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fourth
            while (done == true)
            {
                if (Array[index].setSuit == "Clubs")
                {
                    fourth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fifth
            while (done == true)
            {
                if (Array[index].setSuit == "Clubs")
                {
                    fifth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            answer = "you have a flush consisting of ";
            answer += first;
            answer += ",";
            answer += second;
            answer += ",";
            answer += third;
            answer += ",";
            answer += fourth;
            answer += ", and a";
            answer += fifth;
    
            return true;
        }
        //diamonds
        if (diamonds >= 5)
        {
            int index = 0;
            boolean done = false;
            String first;
            String second;
            String third;
            String fourth;
            String fifth;
            //fill first
            while (done == true)
            {
                if (Array[index].setSuit == "Diamonds")
                {
                    first = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill second
            while (done == true)
            {
                if (Array[index].setSuit == "Diamonds")
                {
                    second = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill third
            while (done == true)
            {
                if (Array[index].setSuit == "Diamonds")
                {
                    third = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fourth
            while (done == true)
            {
                if (Array[index].setSuit == "Diamonds")
                {
                    fourth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            //fill fifth
            while (done == true)
            {
                if (Array[index].setSuit == "Diamonds")
                {
                    fifth = Array[index].toString();
                }
                index++;
                done = true;
            }
            done = false;
    
            answer = "you have a flush consisting of ";
            answer += first;
            answer += ",";
            answer += second;
            answer += ",";
            answer += third;
            answer += ",";
            answer += fourth;
            answer += ", and a";
            answer += fifth;
    */
            return true;
        }
        return false;
    }

    boolean FourofaKind(ArrayList<card> Array, String answer)
    {
        int ct=0;
        int num = 0;
        int cardnum = 0;
        while (num != Array.size())
        {
            cardnum = Array.get(num).getValue();
            for (int i = 1; i < Array.size(); i++)
            {
                if (Array.get(i).getValue() == Array.get(num).getValue())
                {
                    ct++;
                }
            }
            if (ct == 4)
            {
                answer = "You have 4 " + cardnum;
                return true;
            }
            num++;
        }
        return false;
    }
    
    boolean ThreeofaKind(ArrayList<card> Array, String answer)
    {
        int ct=0;
        int num = 0;
        int cardnum = 0;
        int firstTOK = 0;
        while (num != Array.size())
        {
            cardnum = Array.get(num).getValue();
            for (int i = 1; i < Array.size(); i++)
            {
                if (Array.get(i).getValue() == Array.get(num).getValue())
                {
                    ct++;
                }
            }
            if (ct == 3)
            {
    
                if (firstTOK != 0)
                {
                    if (firstTOK > cardnum)
                    {
                        answer = "You have 3 " + firstTOK;
                        return true;
                    }
                    else
                    {
                        answer = "You have 3 " + cardnum;
                        return true;
                    }
                }
                else {
                    firstTOK = cardnum;
                }
            }
            num++;
        }
        return false;
    }
    boolean Pair(ArrayList<card> Array, String answer)
    {
        int ct=0;
        int num = 0;
        int cardnum = 0;
        while (num != Array.size())
        {
            cardnum = Array.get(num).getValue();
            for (int i = 1; i < Array.size(); i++)
            {
                if (Array.get(i).getValue() == Array.get(num).getValue())
                {
                    ct++;
                }
            }
            if (ct == 2)
            {
                answer = "You have a pair of " + cardnum;
                return true;
            }
            num++;
        }
        return false;
    }
    boolean TwoPair(ArrayList<card> Array, String answer)
    {
        int ct=0;
        int num = 0;
        int cardnum = 0;
        int firstPair = 0;
        int secondPair = 0;
        while (num != Array.size())
        {
            cardnum = Array.get(num).getValue();
            for (int i = 1; i < Array.size(); i++)
            {
                if (Array.get(i).getValue() == Array.get(num).getValue())
                {
                    ct++;
                }
            }
            if (ct == 3)
            {
    
                if (firstPair != 0)
                {
                    if (secondPair != 0)
                    {
                        if (firstPair > secondPair)
                        {
                            if (secondPair > cardnum)
                            {
                                answer = "you have a pair of " + firstPair;
                                answer += " and a pair of " + secondPair;
                                answer += "kicker";
                                return true;
                            }
                            else
                            {
                                answer = "you have a pair of " + firstPair;
                                answer += " and a pair of " + cardnum;
                                answer += "kicker";
                                return true;
                            }
    
                        }
                        else if (firstPair > cardnum)
                        {
                            if (secondPair > cardnum)
                            {
                                answer = "you have a pair of " + firstPair;
                                answer += " and a pair of " + secondPair;
                                answer += "kicker";
                                return true;
                            }
                            else {
                                answer = "you have a pair of " + firstPair;
                                answer += " and a pair of " + cardnum;
                                answer += "kicker";
                                return true;
                            }
                        }
                        else {
                            answer = "you have a pair of " + cardnum;
                            answer += " and a pair of " + secondPair;
                            answer += "kicker";
                            return true;
                        }
    
                    }
                    secondPair = cardnum;
                }
                else {
                    firstPair = cardnum;
                }
    
            }
            num++;
        }
        return false;
    }
    //check if you have a Straight
    boolean Straight(ArrayList<card> Array, String answer)
    {
        //check to see if staright is possible
        int diamondstraight = heartstart - diamondstart;
        int heartstraight = spadestart - heartstart;
        int spadestraight = 6 - heartstart;
        int ct = 0;
        int beginning = 0;
        if (diamondstart > 4)
        {
            for (int i = 0; i < diamondstart; i++)
            {
                if (Array.get(i).getValue() == Array.get(i + 1).getValue() + 1)
                {
                    //Straight found
                    if (ct == 5)
                    {
                        int straighthand = Array.get(beginning).getValue();
                        answer = "Your hand is" + straighthand;
                        //answer += ", " + straighthand - 1;
                        //answer += ", " + straighthand - 2;
                       // answer += ", " + straighthand - 3;
                       // answer += ", " + straighthand - 4;
                       // answer += " of " + Array[beginning].getSuit();
                        return true;
                    }
                    ct++;
                }
                else {
                    beginning = i;
                }
            }
    
        }
        else if (diamondstraight > 4)
        {
            for (int i = diamondstart; i < heartstart; i++)
            {
                if (Array.get(i).getValue() == Array.get(i + 1).getValue() + 1)
                {
                    if (ct == 5)
                    {
                        int straighthand = Array.get(beginning).getValue();
                        answer = "Your hand is" + straighthand;
                       // answer += ", " + straighthand - 1;
                       // answer += ", " + straighthand - 2;
                       // answer += ", " + straighthand - 3;
                       // answer += ", " + straighthand - 4;
                       // answer += " of " + Array[beginning].getSuit();
                        return true;
                    }
                    ct++;
                    
                }
                else {
                    beginning = i;
                }
            }
        }
        else if (heartstraight > 4)
        {
            for (int i = heartstart; i < spadestart; i++)
            {
                if (Array.get(i).getValue() == Array.get(i + 1).getValue() + 1)
                {
                    if (ct == 5)
                    {
                        int straighthand = Array.get(beginning).getValue();
                        answer = "Your hand is" + straighthand;
                        //answer += ", " + straighthand - 1;
                       // answer += ", " + straighthand - 2;
                       // answer += ", " + straighthand - 3;
                       // answer += ", " + straighthand - 4;
                       // answer += " of " + Array[beginning].getSuit();
                        return true;
                    }
                    ct++;
                }
                else {
                    beginning = i;
                }
            }
        }
        else if (spadestraight > 4)
        {
            for (int i = spadestart; i < Array.size()-1; i++)
            {
                if (Array.get(i).getValue() == Array.get(i + 1).getValue() + 1)
                {
                    if (ct == 5)
                    {
                        int straighthand = Array.get(beginning).getValue();
                        answer = "Your hand is" + straighthand;
                       // answer += ", " + straighthand - 1;
                      //  answer += ", " + straighthand - 2;
                       // answer += ", " + straighthand - 3;
                      //  answer += ", " + straighthand - 4;
                     //   answer += " of " + Array[beginning].getSuit();
                        return true;
                    }
                    ct++;
                }
                else {
                    beginning = i;
                }
            }
        }
        return false;
    }
}