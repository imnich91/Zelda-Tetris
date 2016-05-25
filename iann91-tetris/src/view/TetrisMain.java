/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.EventQueue;


/**
 * Main class for starting up the Power Paint program. 
 * @author iann91   
 * @version 19 November
 */
public final class TetrisMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain() { 
        throw new IllegalStateException();
    }

    /**
     * The main method, starts the PowerPaint GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI();
            }
        });
    }
}
