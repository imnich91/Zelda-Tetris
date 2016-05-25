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
 * Displays the next piece in its default rotational state. 
 * @author iann91   
 * @version 2 December
 */
public class NextPiecePanel extends JPanel implements PropertyChangeListener {
    
    /**
     * Difference between board coordinates and panel coordinates.
     */
    private static final int TRANSLATE = 23;
    
    /**
     * Default width of panel.
     */
    private static final int DEFAULT_WIDTH = 180;

    /**
     * Number of blocks in a piece.
     */
    private static final int BLOCKS = 4;
    
    /**
     * Default height of panel.
     */
    private static final int DEFAULT_HEIGHT = 0;
    
    /**
     * Size of each block in the GUI. 
     */
    private static final int TILE_SIZE = 30;
    
    /**
     * Default dimensions of panel.
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    
    /**
     * Amount rounded off the rectangles. 
     */
    private static final int ROUND = 20;
    
    /**
     * Font size of titled border. 
     */
    private static final int FONT_SIZE = 20;
    
    /**
     * Border thickness. 
     */
    private static final int BORDER_THICKNESS = 4;
    /**
     * Serial id.
     */
    private static final long serialVersionUID = -10089604555418530L;
    
    /**
     * Game playing boolean.
     */
    private boolean myPlaying;
    
    /**
     * Piece's rotation.
     */
    private int [][] myRotation; 
    
    /**
     * Color of piece.
     */
    private Color myColor;
    
    /**
     * Constructs the Piece panel and sets playing to false. 
     */
    public NextPiecePanel() {
        super();
        setupPanel();
        myPlaying = false;
        myColor = Color.black;
    }
    
    /**
     * Helps constructor with setup of panel. 
     */
    private void setupPanel() {
        final TitledBorder title = BorderFactory.createTitledBorder("Next Piece");
        title.setTitleColor(Color.WHITE);
        title.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_THICKNESS, true));
        title.setTitleFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, FONT_SIZE));
        setBorder(title); 
        setPreferredSize(DEFAULT_SIZE); 
        setBackground(Color.lightGray);
    }
    /**
     * Sets the piece to display to the next piece. 
     * @param theRotation Rotational state of piece.
     * @param thePlaying Whether or not the game is playing. 
     */
    public void setPiece(final int [][] theRotation, final boolean thePlaying) {
        myRotation = theRotation.clone();
        myPlaying = thePlaying;
        repaint();
    }
    /**
     * Draws the piece to the panel. 
     * @param theGraphics Graphics for drawing.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
     
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        //If playing draw the piece. 
        if (myPlaying) {
            g2d.setColor(myColor);
            for (int i = 0; i < BLOCKS; i++) {
                g2d.fillRoundRect((myRotation[i][0] - 2) * TILE_SIZE, 
                               (TRANSLATE + 1 - myRotation[i][1]) 
                               * TILE_SIZE, TILE_SIZE, TILE_SIZE, ROUND, ROUND);
            }
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < BLOCKS; i++) {
                g2d.drawRoundRect((myRotation[i][0] - 2) * TILE_SIZE, 
                               (TRANSLATE + 1 - myRotation[i][1]) 
                               * TILE_SIZE, TILE_SIZE, TILE_SIZE, ROUND, ROUND);
            }
        }
    }

    /**
     * Updates parts of the piece panel due to events in the play panel. 
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("Color")) {
            myColor = (Color) theEvent.getNewValue();
        } else if (theEvent.getPropertyName().equals("ClearNext")) {
            myPlaying = (boolean) theEvent.getNewValue();
            repaint();
        }
        
    }
}
