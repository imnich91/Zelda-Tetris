/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.BorderLayout;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Sets up the frame and adds all of its components to create the GUI.
 * @author iann91
 * @version 2 December
 */
public class TetrisGUI extends JFrame {
    
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 554738866543688862L;
    
    /**
     * Icon used in the title of the Frame.
     */
    private static final ImageIcon MY_ICON = new ImageIcon("./images/triforce.jpg");

    /**
     * Padding between Panels.
     */
    private static final int PADDING = 10;

    /**
     * Panel for displaying next piece. 
     */
    private  NextPiecePanel myPiecePanel;
    
    /**
     * Panel where the game is played.
     */
    private  PlayingArea myPlayPanel;
    
    /**
     * Panel where the score is kept.
     */
    private  ScorePanel myScorePanel; 
    
    /**
     * Panel to hold the buttons. 
     */
    private ButtonPanel myButtonPanel;
    
    /**
     * Constructs the frame, initializes all of the components and adds them to the frame. 
     */
    public TetrisGUI() {
        super();
        setup();
    }
    
    /**
     * Helper method for constructing the GUI.
     */
    private void setup() {
        setupEast();
        setupCenter();
        setupListeners();
 
        setTitle("Tetris: Zelda Edition");
        setIconImage(MY_ICON.getImage());

        setFocusable(true);
        setResizable(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        pack();
        setVisible(true);      
        setLocationRelativeTo(null);
  
    }
    
    /**
     * Initializes the Side panel to hold the components that hold game information.
     * Holds the next piece panel, button panel and score panel.
     * Adds the components to the frame.
     */
    private void setupEast() {
        final SidePanel sidePanel = new SidePanel();
        final BoxLayout layout = new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS);
        sidePanel.setLayout(layout);
        sidePanel.setBackground(Color.LIGHT_GRAY);
        
        myPiecePanel = new NextPiecePanel();
        
        myScorePanel = new ScorePanel();
        
        myButtonPanel = new ButtonPanel(this);
        
        sidePanel.add(myPiecePanel);
        
        sidePanel.add(Box.createVerticalStrut(PADDING));
        
        sidePanel.add(myButtonPanel);
        
        sidePanel.add(Box.createVerticalStrut(PADDING));
        
        sidePanel.add(myScorePanel);
                
        add(sidePanel, BorderLayout.EAST);
    }
    /**
     * Initializes the playing panel and adds it to the frame. 
     */
    private void setupCenter() {
        
        myPlayPanel = new PlayingArea(myButtonPanel, myPiecePanel);
        
        add(myPlayPanel, BorderLayout.CENTER);
    }
    
    /**
     * Adds all of the listeners to their proper components.
     */
    private void setupListeners() {
        myPlayPanel.addPropertyChangeListener(myPiecePanel);
        myPlayPanel.addPropertyChangeListener(myScorePanel);
        myPlayPanel.addPropertyChangeListener(myButtonPanel);
        myScorePanel.addPropertyChangeListener(myPlayPanel);
        myButtonPanel.addPropertyChangeListener(myPlayPanel);
        myButtonPanel.addPropertyChangeListener(myScorePanel);
        addKeyListener(myPlayPanel);
    }
}

