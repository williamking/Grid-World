import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

/**
 * Written by william
 */

public class Jumper extends Bug {
    //Init the health point
    public Jumper() {
        maxHealth = 5;
        hp = maxHealth;
    }

    public Jumper(int ml) {
        if (ml <= 0) {
            throw new IllegalStateException("Illgal hp!");
        }
        maxHealth = ml;
        hp = maxHealth;
    }

    private int hp;
    private int maxHealth;

    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    
    //Get the present hp
    public int getHp() {
        return hp;
    }

    //If the Jumper has died, it can reborn to the world.
    public void reborn() {
        if (hp > 0) {
            return;
        }
        hp = maxHealth;
        putSelfInGrid(getGrid(), getLocation());
    }

    //Basical act
    public void act() {
        if (canMove()) {
            move();
        }
        else { 
            turn();
            turn();
        }
    }

    //Jumper's moving
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        int dir = getDirection();
        //Get the jumping destination
        Location next = loc.getAdjacentLocation(dir).getAdjacentLocation(dir);
        if (gr.isValid(next)) {
            Actor neighbor = gr.get(next); 
            if ((neighbor != null) && !(neighbor instanceof Flower)) {
                //If the Jumper crash to some Object, it will be hurt(hp decrease)......
                getHurt();
                Location to = next.getAdjacentLocation(dir);
                if (gr.isValid(to)) {
                    //The object crashed will move 1 step with the same direction.
                    neighbor.moveTo(to);
                }
                else {
                    //Be crashed out of the grid......
                    neighbor.removeSelfFromGrid();
                }
                //If the Obj crashed is also jumper, it will also be hurt......
                if (neighbor instanceof Jumper) {
                    Jumper jumper2 = (Jumper)neighbor;
                    jumper2.getHurt();
                }
            }
            //If the jumper doesn't die, moves.
            if (hp > 0) {
                moveTo(next);

            }

        }
    }
    
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        return true;
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

    public void getHurt() {
        //hp decreases....
        --hp;
        if (hp <= 0) {
           //die....
           removeSelfFromGrid();

        }

    }
}
