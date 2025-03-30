package nl.codesquad.kata.model;

import nl.codesquad.kata.service.TennisGame;

import java.util.Map;
import java.util.function.BiPredicate;

public enum GameScore {
    ALL,
    DEUCE,
    ADVANTAGE,
    GAME;

    public static final Map<GameScore, BiPredicate<PlayerScore, PlayerScore>> GAME_SCORE_PREDICATES = Map.of(
            ALL, PlayerScore::isAll,
            DEUCE, PlayerScore::isDeuce,
            ADVANTAGE, PlayerScore::isAdvantage,
            GAME, PlayerScore::isGame
    );

    public static String getGameScore(TennisGame game) {
        if (hasScore(ALL, game))
            return game.getFirstPlayerScore() + " " + GameScore.ALL;
        if (hasScore(DEUCE, game))
            return DEUCE.toString();
        if (hasScore(ADVANTAGE, game))
            return ADVANTAGE + " " + game.getPlayerByScore(PlayerScore.ADVANTAGE.name());
        if (hasScore(GAME, game))
            return GAME + " " + game.getPlayerByScore(PlayerScore.GAME.name());
        else
            return game.getFirstPlayerScore() + " " + game.getSecondPlayerScore();
    }


    private static boolean hasScore(GameScore gameScoreCondition, TennisGame game) {
        return GAME_SCORE_PREDICATES.get(gameScoreCondition).test(
                game.getFirstPlayerScore(),
                game.getSecondPlayerScore());
    }
}
