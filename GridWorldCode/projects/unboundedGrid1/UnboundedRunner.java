import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * * This class runs a world with additional grid choices.
 * */
public class UnboundedRunner
{
      public static void main(String[] args)
      {
              ActorWorld world = new ActorWorld();
              world.addGridClass("UnboundedGrid233");
              // world.addGridClass("SparseBoundedGrid1");
              //world.addGridClass("SparseBoundedGrid2");
              //world.addGridClass("SparseBoundedGrid3");
              world.add(new Location(2, 2), new Critter());
              world.add(new Location(5, 5), new Flower());
              world.show(); 
      }

}
