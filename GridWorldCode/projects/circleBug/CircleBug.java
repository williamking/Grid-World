import info.gridworld.actor.Bug;

public class CircleBug extends Bug {
    //How mant steps the bug has moved on one side
    private int steps;
    //How mant steps the Bug should move before turns
    private int maxSteps;

    public CircleBug(int length) {
        maxSteps = length;
        steps = 0;
    }

    public void act() {
        //Judge whether the bug should turn
        if (steps < maxSteps && canMove()) {
            move();
            ++steps;
        } else {
            turn();
            steps = 0;
        }
    }
}
