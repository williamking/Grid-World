import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class CircleBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        //create new bug
        CircleBug emiya = new CircleBug(4);
        emiya.setColor(Color.RED);
        //create new bug
        CircleBug saber = new CircleBug(5);
        saber.setColor(Color.BLUE);
        //put the bug to world
        world.add(new Location(7, 8), emiya);
        world.add(new Location(3, 4), saber);
        world.show();
    }
}
