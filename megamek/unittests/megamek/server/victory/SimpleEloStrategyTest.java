package megamek.server.victory;

import megamek.common.Game;
import megamek.common.Player;
import megamek.common.force.Forces;
import megamek.common.options.GameOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import static org.junit.Assert.*;

@RunWith(value = JUnit4.class)
public class SimpleEloStrategyTest {

    protected Game createMockedGame() {
        Game testGame = Mockito.mock(Game.class);
        Forces testForces = new Forces(testGame);
        Mockito.when(testGame.getGameListeners()).thenReturn(new Vector<>());
        Mockito.when(testGame.getEntities()).thenReturn(Collections.emptyIterator());
        Mockito.when(testGame.getPlayers()).thenReturn(Collections.emptyEnumeration());
        Mockito.when(testGame.getAttacks()).thenReturn(Collections.emptyEnumeration());
        Mockito.when(testGame.getForces()).thenReturn(testForces);
        Mockito.when(testGame.getOptions()).thenReturn(new GameOptions());
        return testGame;
    }

    @Test
    public void testGetVictoryPoints() throws IOException {
        EloStrategy strategy = new SimpleEloStrategy();

        Game testGame = createMockedGame();

        assertEquals(1.0, strategy.getVictoryPoints(0.0, testGame, new Player(0, "Test")), 0.001);
    }

    @Test
    public void testGetDefeatPoints() throws IOException {
        EloStrategy strategy = new SimpleEloStrategy();

        Game testGame = createMockedGame();

        assertEquals(0.0, strategy.getDefeatPoints(1.0, testGame, new Player(0, "Test")), 0.001);
    }
}
