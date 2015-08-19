import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class DancingBug extends Bug {
    private int[] stepArray = new int[100];
    private int maxlength;
    private int arrayLength;
    private int danceint;
    private int moving;
    private int dancingNum;

    public DancingBug(int[] array) {
        maxlength = 100;
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
                    danceint = (danceint + 1) % arrayLength;
                }
            }
        } else {
            turn();
        }
    }
}
