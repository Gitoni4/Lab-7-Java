import java.util.Random;

public class Token
{
    private int tokenValue;
    private int isSelected;

    public Token(int m)
    {
        Random rand = new Random();
        tokenValue = rand.nextInt(m);
        isSelected = 0;
    }

    public Token(int newTokenValue, int m)
    {
        if (newTokenValue < m)
        {
            tokenValue = newTokenValue;
        }
        else
        {
            Random rand = new Random();
            tokenValue = rand.nextInt(m);
        }
        isSelected = 0;
    }

    public int getTokenValue()
    {
        return tokenValue;
    }
}
