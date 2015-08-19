import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class SpiralBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        SpiralBug emiya = new SpiralBug(1);
        emiya.setColor(Color.RED);
        SpiralBug saber = new SpiralBug(2);
        saber.setColor(Color.BLUE);
        world.add(new Location(5, 5), emiya);
        world.add(new Location(6, 6), saber);
        world.show();
    }
}
