import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player implements Runnable {
    private ArrayList<Token> tokensExtracted;
    static int playersNumber;
    private int currentPlayerNumber;
    private String playerName;
    static int winningState;
    boolean canMove;

    private Board board;

    public Player(Board newBoard) {
        tokensExtracted = new ArrayList<Token>();
        playersNumber++;
        currentPlayerNumber = playersNumber;
        this.board = newBoard;
        playerMovement();
    }

    public static void resetPlayerCount() {
        Player.playersNumber = 0;
    }

    public static void resetWinnerState() {
        Player.winningState = 0;
    }

    public void playerMovement() {
        canMove = true;
    }

    public int getPlayerID() {
        return currentPlayerNumber;
    }

    public String setPlayerToThread() {
        return String.format("Player %d", this.currentPlayerNumber);
    }

    public boolean isWinner() {
        int needed = board.progressionNumber;
        int n = tokensExtracted.size();
        if (needed > n)
            return false;
        int[] v = new int[n];
        for (int i = 0; i < n; i++)
            v[i] = tokensExtracted.get(i).getTokenValue();
        Arrays.sort(v);
        int r, count, last;

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                r = v[j] - v[i];
                last = v[j];
                count = 2;
                for (int k = j + 1; k < n && count < needed; k++)
                    if (v[k] == last + r) {
                        last = v[k];
                        count++;
                    }
                if (count == needed)
                    return true;
            }
        return false;
    }


    @Override
    public void run() {
        if (Player.winningState == 1) {
            return;
        }

        if (board.getTokenList().isEmpty()) {
            System.out.println("Game over, there are no more tokens on the board");
        }

        int tokenValue = 0;
        int size;
        while (canMove) {
            if (board.getTokenList().isEmpty()) {
                canMove = false;
                break;
            }

            synchronized (board.getTokenList()) {
                size = board.getTokenList().size();
                Random rand = new Random();
                int tokenIndex = rand.nextInt(size);
                tokenValue = board.getTokenList().get(tokenIndex).getTokenValue();
                board.removeToken(tokenIndex);
            }

                System.out.println(String.format("Player %d has taken the token %d", this.currentPlayerNumber, tokenValue));

                tokensExtracted.add(new Token(tokenValue, board.getTokenLimit()));

                if (this.isWinner()) {
                    System.out.println(this.playerName + "has won");
                    Player.winningState = 1;
                    break;
                }

        }

    }
}