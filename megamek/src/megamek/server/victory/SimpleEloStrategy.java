package megamek.server.victory;

import megamek.common.Game;
import megamek.common.Player;

public class SimpleEloStrategy implements EloStrategy {
    @Override
    public Double getVictoryPoints(Double points, Game game, Player player) {
        return points + 1;
    }

    @Override
    public Double getDefeatPoints(Double points, Game game, Player player) {
        return points - 1;
    }
}
