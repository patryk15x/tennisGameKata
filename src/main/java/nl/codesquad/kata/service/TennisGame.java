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
        setTennisGameScore();
    }

    public String getTennisGameScore() {
        return tennisGameScore;
    }

    private void setTennisGameScore() {
        this.tennisGameScore = GameScore.getGameScore(this);
    }

    public void setTennisPlayerScoreAndUpdateGameScore(TennisPlayer tennisPlayer, PlayerScore playerScore) {
        getTennisPlayerByName(tennisPlayer.getPlayerName()).setPlayerScore(playerScore);
        setTennisGameScore();
    }

    public String getTennisPlayerNameByScore(String score) {
        Predicate<TennisPlayer> playerScorePredicate = player -> player.getPlayerScore().toString().equals(score);
        return this.getTennisPlayer(playerScorePredicate).getPlayerName();
    }

    private TennisPlayer getTennisPlayerByName(String name) {
        Predicate<TennisPlayer> tennisPlayerPredicate = player -> player.getPlayerName().equals(name);
        return this.getTennisPlayer(tennisPlayerPredicate);
    }

    private TennisPlayer getTennisPlayer(Predicate<TennisPlayer> tennisPlayerPredicate) {
        return Arrays.stream(tennisGamePlayers)
                .filter(tennisPlayerPredicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found!"));
    }

    public PlayerScore getFirstTennisPlayerScore() {
        return tennisGamePlayers[0].getPlayerScore();
    }

    public PlayerScore getSecondTennisPlayerScore() {
        return tennisGamePlayers[1].getPlayerScore();
    }
}

