import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
    private int steps;
    private int maxSteps;

    public SpiralBug(int length) {
        maxSteps = length;
        steps = 0;
    }

    public void act() {
        if (steps < maxSteps && canMove()) {
            move();
            ++steps;
        } else {
            turn();
            maxSteps += 1;
            steps = 0;
        }
    }
}
