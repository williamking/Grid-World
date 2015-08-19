import info.gridworld.actor.Bug;

public class circleBug extends Bug {
    private int steps;
    private int maxSteps;

    public circleBug(int length) {
        maxSteps = length;
        steps = 0;
    }

    public void act() {
        if (steps < maxSteps && canMove()) {
            move();
            ++steps;
        } else {
            turn();
            steps = 0;
        }
    }
}
