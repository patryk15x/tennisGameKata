package nl.codesquad.kata.model;


public class TennisPlayer {
    private final String playerName;
    private PlayerScore playerScore;

    public TennisPlayer(String playerName) {
        this.playerName = playerName;
        this.playerScore = PlayerScore.LOVE;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayerScore getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(PlayerScore playerScore) {
        this.playerScore = playerScore;
    }
}
