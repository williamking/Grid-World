import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class circleBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        circleBug emiya = new circleBug(4);
        emiya.setColor(Color.RED);
        circleBug saber = new circleBug(5);
        saber.setColor(Color.BLUE);
        world.add(new Location(7, 8), emiya);
        world.add(new Location(3, 4), saber);
        world.show();
    }
}
