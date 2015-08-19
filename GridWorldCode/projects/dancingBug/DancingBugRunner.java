import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class DancingBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        int[] arr = {1, 3, 5, 7, 9};
        DancingBug emiya = new DancingBug(arr);
        emiya.setColor(Color.RED);
        DancingBug saber = new DancingBug(arr);
        saber.setColor(Color.BLUE);
        world.add(new Location(2, 3), emiya);
        world.add(new Location(3, 4), saber);
        world.show();
    }
}
