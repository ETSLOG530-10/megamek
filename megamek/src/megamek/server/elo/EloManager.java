package megamek.server.elo;

import megamek.common.Game;
import megamek.common.Player;
import megamek.server.DBManager;
import megamek.server.victory.VictoryResult;

public class EloManager {
    private static final double DEFAULT_ELO = 500;

    public static void updateElo(Game game) {
        DBManager.initiateDB();

        VictoryResult victoryResult = game.getVictoryResult();
        EloStrategy strategy = victoryResult.getEloStrategy();
        for (Player player: game.getPlayersVector()) {
            Double eloModification;
            Double initialElo = DBManager.getFromTable(player.getName());
            if (initialElo == -1.0)
                initialElo = DEFAULT_ELO;

            if (game.isPlayerVictor(player)) {
                eloModification = strategy.getVictoryPoints(initialElo, game, player);
            } else {
                eloModification = strategy.getDefeatPoints(initialElo, game, player);
            }

            if (victoryResult.getPlayerScore(player.getId()) <= 0.0)
                victoryResult.addPlayerScore(player.getId(), initialElo);

            victoryResult.addPlayerScore(player.getId(), eloModification);
        }
    }
}
