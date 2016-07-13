package com.hfad.sudoku_solver;


/**
 * This class represents the classic recursive backtracking algorithm.
 * It has a solver that can take a valid SudokuConfig and return a
 * solution, if one exists.
 *
 * This file comes from the backtracking lab. It should be useful
 * in this project. A second method has been added that you should
 * implement.
 *
 * @author Sean Strout @ RIT CS
 * @author James Heliotis @ RIT CS
 * @author Patrick Ly & Daniel Roy Barman
 */
public class Backtracker {

    private boolean debug;

    /**
     * Initialize a new backtracker.
     *
     * @param debug Is debugging output enabled?
     */
    public Backtracker(boolean debug) {
        this.debug = debug;
        if (this.debug) {
            System.out.println("Backtracker debugging enabled...");
        }
    }

    /**
     * A utility routine for printing out various debug messages.
     *
     * @param msg    The type of config being looked at (current, goal,
     *               successor, e.g.)
     * @param config The config to display
     */
    private void debugPrint(String msg, SudokuConfig config) {
        if (this.debug) {
            System.out.println(msg + ":\n" + config);
        }
    }

    /**
     * Try find a solution, if one exists, for a given SudokuConfig.
     *
     * @param config A valid SudokuConfig
     * @return A solution config, or null if no solution
     */
    public SudokuConfig solve(SudokuConfig config) {
        debugPrint("Current config", config);
        //config.display();
        //System.out.println("Valid: "+config.isValid());
        if (config.isGoal()) {
            debugPrint("\tGoal config", config);
            return config;
        } else {

            for (SudokuConfig child : config.getSuccessors()) {
                if (child.isValid()) {
                    debugPrint("\tValid successor", child);

                    SudokuConfig sol = solve(child);

                    if (sol != null) {
                        return sol;
                    }
                } else {
                    debugPrint("\tInvalid successor", child);
                }
            }
            // implicit backtracking happens here
        }
        return null;
    }
}
