package megamek.server.victory;

import megamek.common.Game;
import megamek.common.Player;

public interface EloStrategy {
    Double getVictoryPoints(Double points, Game game, Player player);
    Double getDefeatPoints(Double points, Game game, Player player);
}
