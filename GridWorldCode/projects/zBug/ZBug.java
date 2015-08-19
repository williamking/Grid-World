import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug {
    private int steps;
    private int maxSteps;
    private int period;

    public ZBug(int length) {
        steps = 0;
        period = 0;
        maxSteps = length;
        setDirection(Location.HALF_RIGHT * 2);
    }

    public void act() {
        if (steps < maxSteps && canMove() && period < 3) {
            move();
            ++steps;
        } else {
            if (period == 0 && steps == maxSteps) {
                turn();
                turn();
                turn();
            }
            if (period == 1 && steps == maxSteps) {
                turn();
                turn();
                turn();
                turn();
                turn();
            }
            ++period;
            steps = 0;
        }
    }
}
