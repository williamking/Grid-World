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

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid233 extends AbstractGrid
{
    private Object[][] occupantArray;
    private int bound;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid233()
    {
        occupantArray = new Object[16][16];
        bound = 16;
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return ((loc.getCol() >= 0) && (loc.getRow() >=0));
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (int i = 0; i < bound; ++i)
            for (int j = 0; j < bound; ++j)
                if (occupantArray[i][j] != null)
                    a.add(new Location(i, j));
        return a;
    }

    public Object get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!inBound(loc))
            return null; 
        else
            return occupantArray[loc.getRow()][loc.getCol()];
    }

    public Object put(Location loc, Object obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        while (!inBound(loc))
            extend();
        Object old = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return old;
    }

    public Object remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!inBound(loc))
            return null;
        else {
            occupantArray[loc.getRow()][loc.getCol()] = null;
            return get(loc);
        }
    }

    public void extend() {
        Object[][] newArray = new Object[bound * 2][bound * 2];
        for (int i = 0; i < bound; ++i)
            System.arraycopy(occupantArray[i], 0, newArray[i], 0, bound);
        bound *= 2;
        occupantArray = newArray;
    }

    public boolean inBound(Location loc) {
        return (isValid(loc) && (loc.getRow() < bound) && (loc.getCol() < bound));
    }
}
