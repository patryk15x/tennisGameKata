package nl.codesquad.kata.service;

import nl.codesquad.kata.model.GameScore;
import nl.codesquad.kata.model.PlayerScore;
import nl.codesquad.kata.model.TennisPlayer;

import java.util.Arrays;
import java.util.function.Predicate;


public class TennisGame {

    private String tennisGameScore;
    private final TennisPlayer[] tennisGamePlayers;

    public TennisGame(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
        tennisGamePlayers = new TennisPlayer[]{firstPlayer, secondPlayer};
        setGameScore();
    }

    public String getTennisGameScore() {
        return tennisGameScore;
    }

    private void setGameScore() {
        this.tennisGameScore = GameScore.getGameScore(this);
    }

    public void setPlayerScoreAndUpdateGameScore(TennisPlayer tennisPlayer, PlayerScore playerScore) {
        getPlayerByName(tennisPlayer.getPlayerName()).setPlayerScore(playerScore);
        setGameScore();
    }

    public String getPlayerByScore(String score) {
        Predicate<TennisPlayer> playerScorePredicate = player -> player.getPlayerScore().toString().equals(score);
        return this.getTennisPlayer(playerScorePredicate).getPlayerName();
    }

    private TennisPlayer getPlayerByName(String name) {
        Predicate<TennisPlayer> tennisPlayerPredicate = player -> player.getPlayerName().equals(name);
        return this.getTennisPlayer(tennisPlayerPredicate);
    }

    private TennisPlayer getTennisPlayer(Predicate<TennisPlayer> tennisPlayerPredicate) {
        return Arrays.stream(tennisGamePlayers)
                .filter(tennisPlayerPredicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found!"));
    }

    public PlayerScore getFirstPlayerScore() {
        return tennisGamePlayers[0].getPlayerScore();
    }

    public PlayerScore getSecondPlayerScore() {
        return tennisGamePlayers[1].getPlayerScore();
    }
}

