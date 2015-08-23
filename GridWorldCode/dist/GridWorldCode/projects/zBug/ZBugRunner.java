import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class ZBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        ZBug emiya = new ZBug(4);
        emiya.setColor(Color.RED);
        ZBug saber = new ZBug(5);
        saber.setColor(Color.BLUE);
        world.add(new Location(2, 3), emiya);
        world.add(new Location(3, 4), saber);
        world.show();
    }
}
