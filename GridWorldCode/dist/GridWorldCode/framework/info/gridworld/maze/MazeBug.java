package info.gridworld.maze;

import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public boolean isEnd = false;
	public Stack<Location> crossLocation = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
    private int[] times = new int[4];

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
        } else {
            if (crossLocation.empty()) {
                String msg = "No path to last!";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
                removeSelfFromGrid();
                return;
            }
            Location backLoc = crossLocation.pop();
            if (getGrid().get(backLoc) instanceof Flower) {
                getGrid().get(backLoc).removeSelfFromGrid();
                moveBack(backLoc);
                stepCount++;
            }
        } 
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
        int[] directions = {Location.EAST, Location.WEST, Location.NORTH, Location.SOUTH};
        for (int dir: directions) {
            Location newLoc = loc.getAdjacentLocation(dir);
            if (gr.isValid(newLoc) && (gr.get(newLoc) == null || (gr.get(newLoc) instanceof Rock && gr.get(newLoc).getColor().getRed() == 255))) {
                valid.add(loc.getAdjacentLocation(dir));
            }
        }
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		return (getValid(getLocation()).size() > 0);
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
        ArrayList<Location> locs = getValid(loc);
        Random r = new Random();
        int n = r.nextInt(locs.size());
        next = locs.get(n);
		if (gr.isValid(next)) {
			setDirection(loc.getDirectionToward(next));
            crossLocation.push(loc);
            if (gr.get(next) instanceof Rock && gr.get(next).getColor().getRed() == 255) {
                isEnd = true;
            }
            int dir = loc.getDirectionToward(next);
            ++times[dir / 90];
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

    public Location chooseLoc(ArrayList<Location> locs) {
        int count = 0;
        Location loc = getLocation();
        for (int i = 0; i < locs.size(); ++i) {
            Location next = locs.get(i);
            int dir = loc.getDirectionToward(next);
            count += times[dir / 90];
        }
        Location result = null;
        Random r = new Random();
        int n = r.nextInt(count);
        int pre = 0;
        int now = 0;
        for (int i = 0; i < locs.size(); ++i) {
            Location next = locs.get(i);
            int dir = loc.getDirectionToward(next);
            now = pre + dir;
            if (n > pre && n <= now) {
                result = next;
                break;
            }
            pre = now;
        }
        return result;
    }
    
	public void moveBack(Location backLoc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
        Location loc = getLocation();
        next = backLoc;
		if (gr.isValid(next)) {
			setDirection(loc.getDirectionToward(next));
			moveTo(next);
            int dir = (getDirection() + Location.HALF_CIRCLE) % 360;
            --times[dir / 90];
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
