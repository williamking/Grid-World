import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class DancingBug extends Bug {
    //The array of times of turning
    private int[] stepArray = new int[100];
    //The length of the array
    private int arrayLength;
    //The present index of the steparray
    private int danceint;
    //whether the bug should move
    private int moving;
    //The present times of dancing
    private int dancingNum;

    public DancingBug(int[] array) {
        System.arraycopy(array, 0, stepArray, 0, array.length - 1);
        arrayLength = array.length;
        danceint = 0;
        moving = 0;
        dancingNum = 0;
    }

    public void act() {
        if (canMove()) {
            if (moving == 1) {
                move();
                moving = 0;
            } else {
                if (dancingNum < stepArray[danceint]) {
                    turn();
                    ++dancingNum;
                } else {
                    dancingNum = 0;
                    moving = 1;
                    //next dance
                    danceint = (danceint + 1) % arrayLength;
                }
            }
        } else {
            turn();
        }
    }
}
