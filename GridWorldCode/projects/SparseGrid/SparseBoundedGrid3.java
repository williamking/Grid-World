/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid3 extends AbstractGrid
{
    private SparseGridNode[] occupantArray;// the array storing the grid elements
    private int colNum;    
    private int rowNum;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid3(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new SparseGridNode[rows];
        for (int i = 0; i < rows; ++i) {
            occupantArray[i] = null;
        }
        colNum = cols;
        rowNum = rows;
    }

    public int getNumRows()
    {
        return rowNum;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return colNum;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public Object get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        SparseGridNode row = occupantArray[loc.getRow()];
        if (row == null) return null;
        Object result = null;
        while (row != null) {
            if (row.getCol() == loc.getCol()) {
                result = row.getObj();
                break;
            }
            row = row.getNext();
        }
        return result; // unavoidable warning
    }

    public Object put(Location loc, Object obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        // Add the object to the grid.
        Object oldOccupant = get(loc);
        SparseGridNode newObj = new SparseGridNode(obj, loc.getCol(), occupantArray[loc.getRow()]);
        occupantArray[loc.getRow()] = newObj;
        return null;
    }

    public Object remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        Object r = get(loc); 
        SparseGridNode row = occupantArray[loc.getRow()];
        SparseGridNode pre = null;
        while (row.getCol() != loc.getCol()) {
            pre = row;
            row = row.getNext();
        }
        if (pre != null) {
            pre.setNext(row.getNext());
        } else {
            occupantArray[loc.getRow()] = row;
        }
        return r;
    }

private class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;
    
    public SparseGridNode(Object occ, int colc, SparseGridNode p) {
        occupant = occ;
        col = colc;
        next = p;
        //System.out.println("sds");
    }
    
    public SparseGridNode getNext() {
        return next;
    }

    public void setNext(SparseGridNode node) {
        next = node;
    }

    public int getCol() {
        return col;
    }

    public Object getObj() {
        return occupant;
    }

}
}
