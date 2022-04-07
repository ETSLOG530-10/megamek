package megamek.server.elo;

import megamek.common.Game;
import megamek.common.Player;

public class SimpleEloStrategy extends EloStrategy {
    public SimpleEloStrategy(Game game) {
        super(game);
    }

    @Override
    public Double getVictoryPoints(Double points, Game game, Player player) {
        return points + 1;
    }

    @Override
    public Double getDefeatPoints(Double points, Game game, Player player) {
        return points - 1;
    }
}
