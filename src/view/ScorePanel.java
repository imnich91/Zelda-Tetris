/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * Creates the score panel and displays all of the scoring and gameplay information.
 * @author iann91
 * @version 10 December
 */
public class ScorePanel extends JPanel implements PropertyChangeListener {
    
    /**
     * Default width of the panel.
     */
    private static final int DEFAULT_WIDTH = 150;

    /**
     * Default height of the panel. 
     */
    private static final int DEFAULT_HEIGHT = 30;
    
    /**
     * Dimensions of the panel. 
     */
    private static final Dimension DEFAULT_SIZE = 
                            new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    
    /**
     * X-value of the Strings displayed on the panel. 
     */
    private static final int STRING_X = 10;
    
    /**
     * Y-value of the score string. 
     */
    private static final int SCORE_STRING_Y = 50;
    
    /**
     * Level ten. 
     */
    private static final int LEVEL_TEN = 10;
    
    /**
     * Y-value of the level string. 
     */
    private static final int LEVEL_STRING_Y = 90;
    
    /**
     * Y-value of the cleared string. 
     */
    private static final int CLEARED_STRING_Y = 130;
    
    /**
     * Y-value of the lines left string. 
     */
    private static final int LINES_LEFT_STRING_Y = 170;
    
    /**
     * Font size of the border. 
     */
    private static final int FONT_SIZE = 20;
    
    /**
     * Width of the panel border. 
     */
    private static final int BORDER_WIDTH = 4;
    
    /**
     * Level string. 
     */
    private static final String PLAIN_LEVEL_STRING = "Level: ";
    
    /**
     * Score string. 
     */
    private static final String PLAIN_SCORE_STRING = "Score: ";
    
    /**
     * Lines cleared string. 
     */
    private static final String PLAIN_LINES_CLEARED_STRING = "Lines Cleared: ";
    
    /**
     * Lines left string. 
     */
    private static final String PLAIN_LINES_LEFT_STRING = "Lines Left: ";
    
    /**
     * Infinite lines string. 
     */
    private static final String INFINITE = "Infinite";
    
    /**
     * Final level string. 
     */
    private static final String FINAL = "FINAL!";

    /**
     * Serial id of score panel. 
     */
    private static final long serialVersionUID = -8289605387537815570L;
    
    /**
     * Multiplier for calculating score. 
     */
    private static final int LINE_MULTIPLIER = 5;
    
    /**
     * Score. 
     */
    private int myScore;
    
    /**
     * Level. 
     */
    private int myLevel;
    
    /**
     * Lines cleared. 
     */
    private int myCleared;
    
    /**
     * Lines left. 
     */
    private int myLinesLeft; 
    
    /**
     * Holds the String for level change. 
     */
    private String myLevelString;
    
    /**
     * Holds the String for score change. 
     */
    private String myScoreString;
    
    /**
     * Holds the String for cleared lines change. 
     */
    private String myClearedString;
    
    /**
     * Holds the String for lines left change. 
     */
    private String myLinesLeftString;
    
    /**
     * Creates the score panel. 
     */
    public ScorePanel() {
        super();
        setupPanel();
        myScore = 0;
        myLevel = 1;
        myCleared = 0;
        myLinesLeft = LINE_MULTIPLIER * myLevel;
        myLevelString = PLAIN_LEVEL_STRING + myLevel;
        myScoreString = PLAIN_SCORE_STRING + myScore;
        myClearedString = PLAIN_LINES_CLEARED_STRING + myCleared;
        myLinesLeftString = PLAIN_LINES_LEFT_STRING + myLinesLeft;
    }
    
    /**
     * Helps the constructor setup the panel. 
     */
    private void setupPanel() {
        final TitledBorder title = BorderFactory.createTitledBorder("Score Board");
        title.setTitleColor(Color.WHITE);
        title.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_WIDTH, true));
        title.setTitleFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, FONT_SIZE));
        setBorder(title); 
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.lightGray);
    }

    /**
     * Updates the panel based off of events sent by the play panel. 
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("Score")) {
            myScore = (int) theEvent.getNewValue();
            myScoreString = PLAIN_SCORE_STRING + myScore;
            repaint();                
        }
        if (theEvent.getPropertyName().equals("NewGame")) {
            myScore = 0;
            myLevel = 1;
            myCleared = 0;
            myLinesLeft = LINE_MULTIPLIER * myLevel;
            myLevelString = PLAIN_LEVEL_STRING + myLevel;
            myScoreString = PLAIN_SCORE_STRING + myScore;
            myClearedString = PLAIN_LINES_CLEARED_STRING + myCleared;
            myLinesLeftString = PLAIN_LINES_LEFT_STRING + myLinesLeft;
            repaint();
        }
        if (theEvent.getPropertyName().equals("Cleared")) {
            final int temp = (int) theEvent.getNewValue();
            myCleared += temp;
            myClearedString = PLAIN_LINES_CLEARED_STRING + myCleared;
            if (myLevel == LEVEL_TEN) {
                myLinesLeft = Integer.MAX_VALUE;
                myLinesLeftString = PLAIN_LINES_LEFT_STRING + INFINITE;
                myLevelString = PLAIN_LEVEL_STRING + FINAL;
            } else {
                myLinesLeft -= temp;
                if (myLinesLeft <= 0) {
                    levelUpHelper();
                } else {
                    myLinesLeftString = PLAIN_LINES_LEFT_STRING + myLinesLeft;
                }
            }
                
            repaint();
        }
    }
    
    /**
     * Draws the strings to the panel. 
     * @param theGraphics Graphics used for drawing. 
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
     // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        

        final Font font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 20);
        g2d.setFont(font);

        g2d.drawString(myScoreString, STRING_X, SCORE_STRING_Y);
        g2d.drawString(myLevelString, STRING_X, LEVEL_STRING_Y);
        g2d.drawString(myClearedString, STRING_X, CLEARED_STRING_Y);
        g2d.drawString(myLinesLeftString, STRING_X, LINES_LEFT_STRING_Y);
    }
    
    /**
     * Helps level up to reduce complexity. 
     */
    private void levelUpHelper() {
        myLevel++;
        if (myLevel == LEVEL_TEN) {
            myLinesLeft = Integer.MAX_VALUE;
            myLinesLeftString = PLAIN_LINES_LEFT_STRING + INFINITE;
            myLevelString = PLAIN_LEVEL_STRING + FINAL;
        } else {
            myLevelString = PLAIN_LEVEL_STRING + myLevel;
            myLinesLeft = myLevel * LINE_MULTIPLIER;
            myLinesLeftString  = PLAIN_LINES_LEFT_STRING + myLinesLeft;
        }

        firePropertyChange("Level Up", null, myLevel);
    }
}
