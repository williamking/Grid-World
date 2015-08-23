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
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ChameleonCritter
{

    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
            getDarker();
            return;
        }
        int find = 0;
        for (int i = 0; i < actors.size(); ++i) {
            Actor other = actors.get(i);
            Location loc = getLocation();    
            Location otherLoc = other.getLocation();
            if ((loc.getDirectionToward(otherLoc) - getDirection()) % Location.HALF_CIRCLE == 0) {
                find = 1;
                setColor(other.getColor());
                break;
            }
        }
        if (find == 0) {
            getDarker();
        }      
    }

}
