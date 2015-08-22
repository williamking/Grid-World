/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */

    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        Grid gr = getGrid(); 
        if (n == 0) {
            return;
        }
        for (int i = 0; i < actors.size(); ++i) {
            Actor other = actors.get(i);
            Location loc = getLocation();    
            Location otherLoc = other.getLocation();
            Location newLoc = otherLoc.getAdjacentLocation(loc.getDirectionToward(otherLoc));
            if (gr.isValid(newLoc) && gr.get(newLoc) == null) {
               other.moveTo(newLoc);
            }
            else {
                other.removeSelfFromGrid();
            }           
        }   
    }
  
}
