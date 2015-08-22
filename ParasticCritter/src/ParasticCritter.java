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
import info.gridworld.grid.Grid;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ParasticCritter extends Critter
{
    //For express how full the critter is.
    private int fullPoint;
    //The max fullPoint
    private int maxFull;
    //Whether the critter is hungry, when the fullpoint is less than half of maxFull
    private boolean hungry;

    public ParasticCritter()
    {
        setColor(Color.BLUE);
        maxFull = 5;
        fullPoint = maxFull;
        hungry = false;
    }

    public ParasticCritter(int num) {
        setColor(Color.BLUE);
        maxFull = num;
        fullPoint = maxFull;
        hungry = false;
    }

    public int getFullPoint() {
        return fullPoint;
    }

    public void setFullPoint(int fp) {
        fullPoint = fp;
    }

    public void act() {
        //Every act will use 1 point of full point
        --fullPoint;
        //When fullpoint is less than 0, the critter will die
        if (fullPoint < 0) {
            removeSelfFromGrid();
            return;
        }
        //Judge hungry
        if (fullPoint < (maxFull / 2)) {
            hungry = true;
            setColor(Color.RED);
        }
        super.act();
    }

    public void addFullPoint(int n) {
        fullPoint += n;
        if (fullPoint > maxFull) {
            fullPoint = maxFull;
        }
        if (fullPoint >= (maxFull / 2)) {
            hungry = false; 
            setColor(Color.BLUE);
        }   
    }

    public ArrayList<Actor> getActors() {
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        ArrayList<Location> locs = getLocationsInDirections(dirs);
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for (Location loc : locs) {
            if (getGrid().get(loc) != null) {
                actors.add(getGrid().get(loc));
            }
        }
        //When hugry the critter can find 1 step more distance to find more food
        if (hungry) {
            locs = getFarLocationsInDirections(dirs);
            for (Location loc : locs) {
                if (getGrid().get(loc) != null) {
                    actors.add(getGrid().get(loc));
                }  
            }
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        boolean noFind = true;
        for (Actor a : actors)
        {
            //Eat the actors except rock and critter, eat one will add 1 point to full point(at most maxFull)
            if (!(a instanceof Rock) && !(a instanceof Critter)) {
                a.removeSelfFromGrid();                                       
                addFullPoint(1);
                noFind = false;
            }       
        }                    
        //When finding no food, turn
        if (noFind) {
            turn();
        }
    }

    public void turn() {
        setDirection(getDirection() + Location.HALF_LEFT);    
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        ArrayList<Location> clocs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT }; 
        //When hungry, the critter will jump instead of move
        if (hungry) {
            clocs = getFarLocationsInDirections(dirs);
        } else {
            clocs = getLocationsInDirections(dirs);
        }
        for (Location loc : clocs) {
            if (getGrid().isValid(loc) && getGrid().get(loc) == null) {
                locs.add(loc);
            }
        }

        return locs;
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5) {
                angle = Location.LEFT;
            }
            else {
                angle = Location.RIGHT;
            }
            setDirection(getDirection() + angle);
        }
        else {
            super.makeMove(loc);
        }             
    }
    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }    
        }
        return locs;
    }    

    /**
     * Finds the valid far adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getFarLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d).getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            } 
        }
        return locs;
    }    
}
