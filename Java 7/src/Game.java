import java.util.ArrayList;

public class Game
{
    int n, m, k;
    static int winner, roundEnd;
    ThreadGroup group;
    Board board;
    ArrayList<Player> players;
    int numberOfPlayers;

    public Game(int n, int m, int k, int numberOfPlayers, int numberOfRounds)
    {
        this.numberOfPlayers = numberOfPlayers;
        this.n = n;
        this.m = m;
        this.k = k;
        for (int i = 0; i < numberOfRounds; i++)
        {
            startGame();
        }
    }

    public void startGame()
    {
         Player.resetPlayerCount();
         Player.resetWinnerState();
         this.board = new Board(n, k, m);
         this.players = new ArrayList<Player>();
         this.winner = 0;
         this.roundEnd = 0;
         for (int i = 0; i < numberOfPlayers; i++)
         {
             players.add(new Player(this.board));
         }
         group = new ThreadGroup("playerThreadGroup");
         board.showAllTokens();
         Thread playerAction;
         for (Player currentPlayer : players)
         {
             playerAction = new Thread(group, currentPlayer, currentPlayer.setPlayerToThread());
             playerAction.start();
         }

    }
}
