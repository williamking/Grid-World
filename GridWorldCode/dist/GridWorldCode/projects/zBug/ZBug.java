import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug {
    //The length the bug have moved on the side
    private int steps;
    //length of the side
    private int maxSteps;
    //where the zBug has moved to
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
            //First turn
            if (period == 0 && steps == maxSteps) {
                turn();
                turn();
                turn();
            }
            //Second turn
            if (period == 1 && steps == maxSteps) {
                turn();
                turn();
                turn();
                turn();
                turn();
            }
            //Change to next period and init the steps
            ++period;
            steps = 0;
        }
    }
}
