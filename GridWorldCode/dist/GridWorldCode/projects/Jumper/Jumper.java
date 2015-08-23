import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

/**
 * Written by william
 */

public class Jumper extends Bug {

    public Jumper() {
        hp = max_health = 5;
    }

    public Jumper(int ml) {
        if (ml <= 0)
            throw new IllegalStateException("Illgal hp!");
        hp = max_health = ml;
    }

    private int hp;
    private int max_health;

    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    
    public int getHp() {
        return hp;
    }

    public void reborn() {
        if (hp > 0)
            return;
        hp = max_health;
        putSelfInGrid(getGrid(), getLocation());
    }

    public void act() {
        if (canMove())
            move();
        else { 
            turn();
            turn();
        }
    }


    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        int dir = getDirection();
        Location next = loc.getAdjacentLocation(dir).getAdjacentLocation(dir);
        if (gr.isValid(next)) {
            Actor neighbor = gr.get(next); 
            if ((neighbor != null) && !(neighbor instanceof Flower)) {
                getHurt();
                Location to = next.getAdjacentLocation(dir);
                if (gr.isValid(to))
                    neighbor.moveTo(to);
                else
                    neighbor.removeSelfFromGrid();
                if (neighbor instanceof Jumper) {
                    Jumper jumper2 = (Jumper)neighbor;
                    jumper2.getHurt();
                }
            }
            if (hp > 0)
                moveTo(next);
        }
    }
    
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        return true;
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

    public void getHurt() {
        --hp;
        if (hp <= 0)
           removeSelfFromGrid();
    }
}
