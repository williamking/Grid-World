import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
    //How mant steps the bug has moved on one side
    private int steps;
    //How mant steps the bug should moved on one side
    private int maxSteps;

    public SpiralBug(int length) {
        maxSteps = length;
        steps = 0;
    }

    public void act() {
        //Judge whether the bug should turn
        if (steps < maxSteps && canMove()) {
            move();
            //next steps
            ++steps;
        } else {
            turn();
            maxSteps += 1;
            steps = 0;
        }
    }
}
