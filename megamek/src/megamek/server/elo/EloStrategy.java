package megamek.server.elo;

import megamek.common.Game;
import megamek.common.Player;

public abstract class EloStrategy {
    protected Game game;

    public EloStrategy(Game game) {
        this.game = game;
    }

    public abstract Double getVictoryPoints(Double points, Game game, Player player);
    public abstract Double getDefeatPoints(Double points, Game game, Player player);
}
