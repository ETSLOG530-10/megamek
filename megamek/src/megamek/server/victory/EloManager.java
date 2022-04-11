package megamek.server.victory;

import megamek.common.Game;
import megamek.common.Player;
import megamek.server.DBManager;

public class EloManager {
    private static final double DEFAULT_ELO = 500;

    public static void updateElo(Game game) {
        VictoryResult victoryResult = game.getVictoryResult();
        if (victoryResult == null) return;
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

            // Insert new elo into database
            DBManager.addOrUpdateRow(player.getName(), eloModification);
        }
    }
}
