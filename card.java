//package thesis;
public class card
{
private String suit;
private  int value;

public card (int n, String s)
{
    value=n;
    suit=s;
    
}
public int getValue()
{
    return value;
}

public String getSuit()
{
    return suit;
}
public String toString()
{
    String retVal="";
    if (value <= 10)
    {
        retVal = Integer.toString(value);
    }
    else if(value == 11)
    {
        retVal = "Jack";
    }
    else if (value == 12)
    {
        retVal = "Queen";
    }
    else if (value == 13)
    {
        retVal = "King";
    }
    else if (value == 14)
    {
        retVal = "Ace";
    }

    retVal = retVal + " of " + suit;

    return retVal;
}
}