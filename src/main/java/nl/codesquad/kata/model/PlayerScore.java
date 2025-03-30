package nl.codesquad.kata.model;

public enum PlayerScore {
    LOVE,
    FIFTEEN,
    THIRTY,
    FORTY,
    ADVANTAGE,
    GAME;

    public static boolean isAll(PlayerScore p1, PlayerScore p2) {
        return p1 == p2 && !isDeuce(p1, p2);
    }

    public static boolean isDeuce(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return firstPlayerScore.equals(PlayerScore.FORTY)
                && secondPlayerScore.equals(PlayerScore.FORTY);
    }

    public static boolean isAdvantage(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return firstPlayerScore.equals(PlayerScore.ADVANTAGE)
                || secondPlayerScore.equals(PlayerScore.ADVANTAGE);
    }

    public static boolean isGame(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore) {
        return firstPlayerScore.equals(PlayerScore.GAME)
                || secondPlayerScore.equals(PlayerScore.GAME);
    }
}
