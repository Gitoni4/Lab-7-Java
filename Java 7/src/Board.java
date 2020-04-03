import java.util.*;

public class Board
{
    private List<Token> tokenList;
    private int tokenNumber;
    private int tokenLimit;
    int progressionNumber;

    public Board(int tokenNumber, int numberNeededToWin, int tokenLimit)
    {
        this.tokenNumber = tokenNumber;
        this.progressionNumber = numberNeededToWin;
        this.tokenLimit = tokenLimit;
        this.tokenList = new ArrayList<>();
        addTokens(tokenLimit);
    }

    public void addTokens(int tokenLimit)
    {
        for (int i = 0; i < tokenNumber; i++)
        {
            tokenList.add(new Token(tokenLimit));
        }
    }

    public int getTokenLimit()
    {
        return tokenLimit;
    }

    public List<Token> getTokenList()
    {
        return tokenList;
    }

    public void showAllTokens()
    {
        System.out.println("Tokens on board : ");
        for (Token token : tokenList)
        {
            System.out.print(token.getTokenValue() + " ");
        }
    }

    public void removeToken(int index)
    {
        tokenList.remove(index);
    }
}
