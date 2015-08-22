import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.actor.ActorWorld;

public class JumperTest {
    public ActorWorld w = new ActorWorld();
    //public UnboundedGrid<Actor> w.getGrid() = new UnboundedGrid<Actor>();
    public Jumper a = new Jumper();
    public Jumper b = new Jumper();
    public Rock r = new Rock();
    public Flower f = new Flower();
    public Actor x = new Actor();

    //Init the location and direction.
    @Before
    public void setUp() throws Exception {
        w.add(new Location(1, 1), a);
        a.setDirection(Location.EAST);
    }
    
    //Remove the Jumper when finishing test.
    @After
    public void tearDown() throws Exception {
        a.removeSelfFromGrid();
    }
        
    //Test jumping.
    @Test
    public void testJump() {
        a.act();
        assertEquals(a.getLocation(), new Location(1, 3));
        assertEquals(a.getDirection(), Location.EAST);
        
        w.add(new Location(1, 4), f);
        a.act();
        assertEquals(a.getLocation(), new Location(1, 5));
        assertEquals(f, w.getGrid().get(new Location(1, 4)));
        assertEquals(a.getDirection(), Location.EAST);

        w.add(new Location(1, 6), r);
        a.act();
        assertEquals(a.getLocation(), new Location(1, 7));
        assertEquals(r, w.getGrid().get(new Location(1, 6)));
        assertEquals(a.getDirection(), Location.EAST);

        a.act();
        a.act();
        assertEquals(a.getLocation(), new Location(1, 9));
        assertEquals(a.getDirection(), Location.SOUTH);

        f.removeSelfFromGrid();
        r.removeSelfFromGrid();
    }
        
    //Test crashing to Obj.
    @Test
    public void testJumpToFlowerOrRock() {
        w.add(new Location(1, 3), f);
        a.act();
        assertEquals(a.getLocation(), new Location(1, 3));
        assertEquals(null, f.getGrid());
        assertEquals(a.getDirection(), Location.EAST);
        
        w.add(new Location(1, 5), r);
        int oldHp = a.getHp();
        a.act();
        assertEquals(a.getLocation(), new Location(1, 5));
        assertEquals(a.getDirection(), Location.EAST);
        assertEquals(oldHp - 1, a.getHp());
        assertEquals(new Location(1, 6), r.getLocation());

        r.moveTo(new Location(1, 9));
        a.act();
        a.act();
        assertEquals(null, r.getGrid());
    }
    
    //It will not jump outside.
    @Test
    public void testJumpToOutside() {
        a.setDirection(Location.WEST);
        a.act();
        assertEquals(new Location(1, 1), a.getLocation());
        assertEquals(Location.NORTH, a.getDirection());
    }

    //It will turn.
    @Test
    public void testFacingEdge() {
        a.moveTo(new Location(0, 0));
        a.setDirection(Location.WEST);
        a.act();
        assertEquals(new Location(0, 0), a.getLocation());
        assertEquals(Location.NORTH, a.getDirection());
    }

    //Jumping to actor.
    @Test
    public void testJumpToActor() {
        w.add(new Location(1, 3), x);
        int oldHp = a.getHp();
        a.act();
        assertEquals(a.getLocation(), new Location(1, 3));
        assertEquals(a.getDirection(), Location.EAST);
        assertEquals(oldHp - 1, a.getHp());
        assertEquals(new Location(1, 4), x.getLocation());

        a.moveTo(new Location(1, 7));
        x.moveTo(new Location(1, 9));
        oldHp = a.getHp();
        a.act();
        assertEquals(a.getLocation(), new Location(1, 9));
        assertEquals(a.getDirection(), Location.EAST);
        assertEquals(oldHp - 1, a.getHp());
        assertEquals(null, x.getGrid());

        a.setDirection(Location.SOUTH);
        w.add(new Location(3, 9), b);
        oldHp = b.getHp();
        a.act();
        assertEquals(a, w.getGrid().get(new Location(3, 9)));
        assertEquals(b, w.getGrid().get(new Location(4, 9)));
        assertEquals(oldHp - 1, b.getHp());

        b.removeSelfFromGrid();
    }

    //The crashing is none of business of the path.
    @Test
    public void testEncounterAnother() {
        w.add(new Location(1, 2), b);
        int oldHpOfA = a.getHp();
        int oldHpOfB = b.getHp();
        a.act();
        assertEquals(new Location(1, 3), a.getLocation());
        assertEquals(new Location(1, 2), b.getLocation());
        assertEquals(Location.EAST, a.getDirection());
        assertEquals(Location.NORTH, b.getDirection());
        assertEquals(oldHpOfA, a.getHp());
        assertEquals(oldHpOfB, b.getHp());

        b.removeSelfFromGrid();
    }
}


