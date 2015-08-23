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
	public Location last;
	public boolean isEnd = false;
	public Stack<Location> crossLocation = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(1, 1);
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
                return;
            }
            Location backLoc = crossLocation.pop();
            if (getGrid().get(backLoc) instanceof Flower) {
                getGrid().get(backLoc).removeSelfFromGrid();
                moveBack();
                stepCount++;
            }
        } 
	}

    public void setLast(int row, int col) {
        last = new Location(row, col);            
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
            if (gr.isValid(newLoc) && (gr.get(newLoc) == null || newLoc.equals(last))) {
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
			moveTo(next);
            if (next.equals(last)) {
                isEnd = true;
            }
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
    
	public void moveBack() {
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
			moveTo(next);
            if (next.equals(last)) {
                isEnd = true;
            }
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
