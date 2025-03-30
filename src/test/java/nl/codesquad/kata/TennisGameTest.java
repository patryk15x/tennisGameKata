package nl.codesquad.kata;

import nl.codesquad.kata.model.PlayerScore;
import nl.codesquad.kata.model.TennisPlayer;
import nl.codesquad.kata.service.TennisGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static nl.codesquad.kata.model.PlayerScore.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameTest {

    private static TennisGame tennisGame;
    private static TennisPlayer firstPlayer;
    private static TennisPlayer secondPlayer;

    @BeforeEach
    public void setUp() {
        firstPlayer = new TennisPlayer("Daniil Medvedev");
        secondPlayer = new TennisPlayer("Novak Djokovic");
        tennisGame = new TennisGame(firstPlayer, secondPlayer);
    }

    @Test
    public void shouldGetTennisGameScoreReturnLoveAll() {
        assertEquals("LOVE ALL", tennisGame.getTennisGameScore());
    }

    @ParameterizedTest
    @MethodSource("bothPlayerScoredCases")
    public void shouldGetTennisGameScoreReturnCorrectGameScore(
            PlayerScore firstPlayerScore,
            PlayerScore secondPlayerScore,
            String expectedGameScore) {
        tennisGame.setTennisPlayerScoreAndUpdateGameScore(firstPlayer, firstPlayerScore);
        tennisGame.setTennisPlayerScoreAndUpdateGameScore(secondPlayer, secondPlayerScore);
        assertEquals(expectedGameScore, tennisGame.getTennisGameScore());
    }

    @Test
    public void shouldGetTennisGameScoreReturnFifteenLove() {
        tennisGame.setTennisPlayerScoreAndUpdateGameScore(firstPlayer, FIFTEEN);
        assertEquals("FIFTEEN LOVE", tennisGame.getTennisGameScore());
    }

    @Test
    public void shouldGetTennisGameScoreReturnAdvantageDaniilMedvedev() {
        tennisGame.setTennisPlayerScoreAndUpdateGameScore(firstPlayer, ADVANTAGE);
        assertEquals("ADVANTAGE " + firstPlayer.getPlayerName(), tennisGame.getTennisGameScore());
    }

    @Test
    public void shouldGetTennisGameScoreReturnGameReturnDaniilMedvedev() {
        tennisGame.setTennisPlayerScoreAndUpdateGameScore(firstPlayer, GAME);
        assertEquals("GAME " + firstPlayer.getPlayerName(), tennisGame.getTennisGameScore());

    }

    private static Stream<Arguments> bothPlayerScoredCases() {
        return Stream.of(
                Arguments.of(FIFTEEN, FIFTEEN, "FIFTEEN ALL"),
                Arguments.of(THIRTY, THIRTY, "THIRTY ALL"),
                Arguments.of(FORTY, FORTY, "DEUCE")
        );
    }


}
