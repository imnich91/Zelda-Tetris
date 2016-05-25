/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Board;

/**
 * Holds all of the buttons used with the game.
 * @author iann91
 * @version 2 December
 */
public class ButtonPanel extends JPanel implements PropertyChangeListener {
    
    /**
     * String displayed on controls button.
     */
    private static final String CONTROLS = "CONTROLS";
    
    /**
     * String displayed on the about button.
     */
    private static final String ABOUT = "CREDITS";
    
    /**
     * End game string. 
     */
    private static final String END_GAME = "END GAME";
    
    /**
     * Panel border thickness. 
     */
    private static final int BORDER_THICKNESS = 4;
    
    /**
     * Border thickness around buttons. 
     */
    private static final int BUTTON_BORDER = 2;
    
    /**
     * Icon displayed on the controls JOptionPane.
     */
    private static final ImageIcon MY_ICON = new ImageIcon("./images/triforce.png");
    
    /**
     * Serial id. 
     */
    private static final long serialVersionUID = -806373862473032841L;

    /**
     * Number of rows in the playing panel.
     */
    private static final int NUM_ROWS = 20;
    
    /**
     * Panel width.
     */
    private static final int DEFAULT_WIDTH = 180;
    
    /**
     * Panel height.
     */
    private static final int DEFAULT_HEIGHT = 80;

    /**
     * Panel dimensions. 
     */
    private static final Dimension DEFAULT_SIZE = 
                    new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    
    /**
     * Number of columns in the playing panel.
     */
    private static final int NUM_COLUMNS = 10;
    
    /**
     * Enable grid string.
     */
    private static final String ENABLE_GRID = "ENABLE GRID";
    
    /**
     * Disable grid string. 
     */
    private static final String DISABLE_GRID = "DISABLE GRID";
    
    /**
     * Mute string. 
     */
    private static final String MUTE = "MUTE";
    
//    private static final String SAVE_GAME = "SAVE GAME";
//    
//    private static final String LOAD_GAME = "LOAD GAME";
    
    /**
     * Grid string.
     */
    private static final String GRID_STRING = "Grid";
    
    /**
     * Number of rows in grid layout.
     */
    private static final int LAYOUT_ROWS = 6;
    
    /**
     * Number of columns in grid layout. 
     */
    private static final int LAYOUT_COLUMNS = 1;
    
    /**
     * Font size of the border title. 
     */
    private static final int FONT_SIZE = 20;
    
    /**
     * New game button. 
     */
    private final JButton myNewGameButton = new JButton("NEW GAME");
    
    /**
     * End game button. 
     */
    private final JButton myEndGameButton = new JButton(END_GAME);
    
    //private final JButton mySaveLoadButton;
    
    /**
     * About button. 
     */
    private final JButton myAboutButton = new JButton("ABOUT");
    
    //private final JButton myHighScoresButton = new JButton("HIGHSCORES");
    
    /**
     * Mute button.
     */
    private JButton myMuteButton;
    
    /**
     * Grid button. 
     */
    private JButton myGridButton;
    
    /**
     * Boolean for grid.
     */
    private boolean myGrid;
    
    /**
     * Grid text for holding enabled or disabled. 
     */
    private String myGridText; 
    
    //private String mySaveLoadText;
    
    /**
     * Mute text for holding mute or unmute. 
     */
    private String myMuteText;
    
    /**
     * Tetris game board. 
     */
    private Board myBoard;
    
    /**
     * JFrame of the GUI.
     */
    private final JFrame myFrame;

    /**
     * Initializes the fields and constructs the button panel. 
     * @param theFrame JFrame of the GUI.
     */
    public ButtonPanel(final JFrame theFrame) {
        super();
        setupBorder();
        setPreferredSize(DEFAULT_SIZE); 
        myGridText = ENABLE_GRID;
        myMuteText = MUTE;
        myGrid = false;
        setupButtons();
        setBackground(Color.lightGray);
        setLayout(new GridLayout(LAYOUT_ROWS, LAYOUT_COLUMNS));
        myFrame = theFrame;
    }
    
    /**
     * Helps setup the border around the panel. 
     */
    private void setupBorder() { 
        final TitledBorder title = BorderFactory.createTitledBorder("Menu");
        title.setTitleColor(Color.WHITE);
        title.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_THICKNESS, true));
        title.setTitleFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC, FONT_SIZE));
        setBorder(title);
    }
    /**
     * Helps the constructor create the buttons. 
     */
    private void setupButtons() {
      //mySaveLoadText = LOAD_GAME;
        myGridButton = new JButton(myGridText);
        myMuteButton = new JButton(myMuteText);
        //mySaveLoadButton = new JButton(mySaveLoadText);
        createNewGameButton();
        createEndButton();
        //createSaveLoadButton();
        createControlsButton();
        //createHighScoresButton();
        createGridButton();
        createMuteButton();
        createAboutButton();
    }

    /**
     * Sets the board used in the button panel to the current board. 
     * @param theBoard current board.
     */
    public void setBoard(final Board theBoard) {
        myBoard = theBoard;
    }

    /**
     * Creates the new game button and its action associated with it. 
     * Creates a new board to be used for playing a new game. 
     * @return the button. 
     */
    private JButton createNewGameButton() {
        myNewGameButton.setBorder(BorderFactory.createLineBorder
                                  (Color.BLACK, BUTTON_BORDER, true));
        myNewGameButton.setBackground(Color.lightGray);
        add(myNewGameButton);
        myNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.newGame(NUM_COLUMNS, NUM_ROWS, null);
                //Lets the playing area know that a new board is being used.
                firePropertyChange("NewGame", null, null);
                myFrame.requestFocus(); 
                myEndGameButton.setEnabled(true);
                myNewGameButton.setEnabled(false);
            }
        });
        return myNewGameButton;
    }
    
    /**
     * Creates the controls button and the action associated with it.
     * @return the button.
     */
    private JButton createControlsButton() {
        final JButton button = new JButton(CONTROLS);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, BUTTON_BORDER, true));
        button.setBackground(Color.lightGray);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(CONTROLS, null, true);
                //Opens a JOptionPane to display the controls. 
                JOptionPane.showMessageDialog(myFrame, "Move left:    LEFT ARROW\n"
                                + "Move right:    RIGHT ARROW\nMove down:    DOWN ARROW\n"
                                + "Rotate:    UP ARROW\nDrop:    SPACE BAR\nPause:    P \n"
                                + "Scoring: 30 * Lines Cleared + 5 ^ Lines Cleared"
                                , CONTROLS, 1, MY_ICON);
                firePropertyChange(CONTROLS, null, false);
                myFrame.requestFocus();
            } 
        });
        return button;
    }
    /**
     * Creates the end game button. Ends the game in the current state when pressed.
     * @return the button. 
     */
    private JButton createEndButton() {
        myEndGameButton.setEnabled(false);
        myEndGameButton.setBorder(BorderFactory.createLineBorder
                                  (Color.BLACK, BUTTON_BORDER, true));
        myEndGameButton.setBackground(Color.lightGray);
        add(myEndGameButton);
        myEndGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myNewGameButton.setEnabled(true);
                firePropertyChange(END_GAME, null, true);
                myEndGameButton.setEnabled(false);
                myFrame.requestFocus();
            } 
        });
        return myEndGameButton;
    }
//    
//    private JButton createSaveLoadButton() {
//        mySaveLoadButton.setBorder(BorderFactory.createLineBorder
//                                   (Color.BLACK, BUTTON_BORDER, true));
//        mySaveLoadButton.setBackground(Color.lightGray); 
//        mySaveLoadButton.setEnabled(false);
//        add(mySaveLoadButton);
//        
//        mySaveLoadButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                if (mySaveLoadText.equals(LOAD_GAME)) {
//                    firePropertyChange("LoadGame", null, null);
//                } else {
//                    firePropertyChange("SaveGame", null, null);
//                }
//                myFrame.requestFocus();
//            }
//        });
//        return mySaveLoadButton;
//    }
    
    /**
     * Creates the mute button to mute the sound. . 
     * When the mute button is pressed the String changes to unmute.
     * When it is pressed again it changes back to mute. 
     * @return the button. 
     */
    private JButton createMuteButton() {
        myMuteButton.setBorder(BorderFactory.createLineBorder
                                (Color.BLACK, BUTTON_BORDER, true));
        myMuteButton.setBackground(Color.lightGray);
        add(myMuteButton);
        myMuteButton.setEnabled(true);
        myMuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myMuteText.equals(MUTE)) {
                    firePropertyChange("Mute", null, true);
                } else {
                    firePropertyChange("Unmute", null, false);
                }
                myFrame.requestFocus();
            }
        });
        return myAboutButton;
    }
    
    /**
     * Creates the about button to display the credits when pressed. 
     * Pauses the game when pressed and unpauses when closed. 
     * @return the button. 
     */
    private JButton createAboutButton() {
        myAboutButton.setBorder(BorderFactory.createLineBorder
                                (Color.BLACK, BUTTON_BORDER, true));
        myAboutButton.setBackground(Color.lightGray);
        add(myAboutButton);
        myAboutButton.setEnabled(true);
        myAboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(ABOUT, null, true);
                //Opens a JOptionPane to display the controls. 
                aboutHelper();
                firePropertyChange(ABOUT, null, false);
                myFrame.requestFocus();
            }
        });
        return myAboutButton;
    }
    /**
     * Helps the about button to display the option paned when pressed. 
     */
    private void aboutHelper() {
        JOptionPane.showMessageDialog(myFrame, "TCSS 305 UW TACOMA\n"
                        + "Zelda inspired tetris created by Ian Nicholas.\n"
                        + "Back end code provided by Alan Fowler.\n"
                        + "                               CREDITS \n" + "MUSIC: \n"
                        + "Zelda: Ocarina of Time\n" 
                        + "PICTURES: \n" + "Level One picture from deviantart.net\n"
                                        + "Level Two picture from deviantart.net\n"
                                        + "Level Three picture from gengame.net\n"
                                        + "Level Four picture from deviantart.net\n"
                                        + "Level Five picture from deviantart.net\n"
                                        + "Level Six picture from ngfiles.com\n"
                                        + "Level Seven picture from zelda.com\n"
                                        + "Level Eight picture from deviantart.net\n"
                                        + "Level Nine picture from zeldawiki.org\n"
                                        + "Level Ten picture from media-cache-ec0.pinimg.com"
                        , ABOUT, 1, MY_ICON);
    }
    
//    private JButton createHighScoresButton() {
//        myHighScoresButton.setBorder(BorderFactory.createLineBorder
//                                     (Color.BLACK, BUTTON_BORDER, true));
//        myHighScoresButton.setBackground(Color.lightGray);
//        add(myHighScoresButton);
//        myHighScoresButton.setEnabled(false);
//        myHighScoresButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                myFrame.requestFocus();
//            }
//        });
//        return myHighScoresButton;
//    }
    
    /**
     * Creates the button to enable the grid.
     * When pressed the string changes to disable grid.
     * Changes to enable grid when pressed again. 
     * @return the button. 
     */
    private JButton createGridButton() {
        myGridButton.setBorder(BorderFactory.createLineBorder
                                     (Color.BLACK, BUTTON_BORDER, true));
        myGridButton.setBackground(Color.lightGray);
        myGridButton.setEnabled(myGrid);
        add(myGridButton);
        myGridButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (myGridText.equals(ENABLE_GRID)) {
                    myGrid = true;
                    firePropertyChange(GRID_STRING, null, myGrid);
                    myGridText = DISABLE_GRID;
                    myGridButton.setText(myGridText);
                } else {
                    myGrid = false;
                    firePropertyChange(GRID_STRING, null, myGrid);
                    myGridText = ENABLE_GRID;
                    myGridButton.setText(myGridText);
                }
                myFrame.requestFocus();
            }
        });
        return myGridButton;
    }

    /**
     * Property changes to enable and disable buttons based on events in the playing panel.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("GAMEOVER")) {
            myNewGameButton.setEnabled((boolean) theEvent.getNewValue());
            myEndGameButton.setEnabled(!(boolean) theEvent.getNewValue());
        } else if (theEvent.getPropertyName().equals("EnableGridButton")) {
            myGridButton.setEnabled((boolean) theEvent.getNewValue());
            if (!(boolean) theEvent.getNewValue()) {
                myGridText = ENABLE_GRID;
                myGridButton.setText(myGridText);
            }
        } else if (theEvent.getPropertyName().equals("SetMute")) {
            myMuteText = (String) theEvent.getNewValue();
            myMuteButton.setText(myMuteText);
        }
//        if (theEvent.getPropertyName().equals("SetSaveLoad")) {
//            mySaveLoadText = (String) theEvent.getNewValue();
//            mySaveLoadButton.setText(mySaveLoadText);
//        }
        
    }
}
