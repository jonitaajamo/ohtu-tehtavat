package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class StatisticTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void canFindPlayerByName() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }

    @Test
    public void returnsNullIfPlayerNotFound() {
        assertEquals(null, stats.search("Granlund"));
    }

    @Test
    public void teamMethodReturnsTeamsPlayersWhenCalled() {
        assertEquals("Lemieux", stats.team("PIT").get(0).getName());
    }

    @Test
    public void returnsTopScorerWhenCalled(){
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }

}
