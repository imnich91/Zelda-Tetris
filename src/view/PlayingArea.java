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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
//import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.AbstractPiece;
import model.Block;
import model.Board;

/**
 * This is the panel where the game of tetris is played.
 * @author iann91
 * @version 2 December
 */
public class PlayingArea extends JPanel implements ActionListener, Observer
                                        , KeyListener, PropertyChangeListener {
        
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 4226892667308307163L;

    /**
     * Amount of blocks in a piece.
     */
    private static final int BLOCKS = 4;
    
    /**
     * Width of the Panel.
     */
    private static final int DEFAULT_WIDTH = 300;
    
    /**
     * Height of the Panel.
     */
    private static final int DEFAULT_HEIGHT = 600;
    
    /**
     * Multiplier for calculating score. 
     */
    private static final int LINE_MULTIPLIER = 5;
    
    /**
     * Dimension of the Panel.
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    
    /**
     * Number of rows.
     */
    private static final int NUM_ROWS = 20;
    
    /**
     * Number of columns.
     */
    private static final int NUM_COLUMNS = 10;
    
    /**
     * Size of each block in the panel.
     */
    private static final int TILE_SIZE = 30;
    
    /**
     * Initial delay of the timer.
     */
    private static final int START_TIME = 1000;
    
    /**
     * Game paused string.
     */
    private static final String PAUSE_STRING = "PAUSED";
    
    /**
     * Game over string.
     */
    private static final String GAMEOVER_STRING = "GAME OVER";
//    
//    private static final String SET_SAVE_LOAD = "SetSaveLoad";
//    
//    private static final String SAVE_GAME = "SAVE GAME";
//    
//    private static final String LOAD_GAME = "LOAD GAME";
    
    /**
     * Enable grid string.
     */
    private static final String ENABLE_GRID_STRING = "EnableGridButton";
    
    /**
     * Level up sound. 
     */
    private static final String LEVEL_UP_SOUND = "music/LevelUp.wav";
    
    /**
     * Menu music. 
     */
    private static final String MENU_SOUND = "music/GreatFairy.wav";
    
    /**
     * Game over sound. 
     */
    private static final String GAMEOVER_SOUND = "music/GameOver.wav";
    
    /**
     * Amount the timer is decremented each level. 
     */
    private static final int DECREMENT_TIME = 100;
    
    /**
     * Amount rounded off blocks. 
     */
    private static final int ROUND = 20;
    
    /**
     * Sound player. 
     */
    private SoundPlayer mySoundPlayer;
    
    /**
     * Game ended boolean. 
     */
    private boolean myEndGame;
                    
    /**
     * Grid boolean for drawing. 
     */
    private boolean myGrid;
    
    /**
     * Muted boolean. 
     */
    private boolean myMuted;
    /**
     * Delay of the timer.
     */
    private int myTime;
    
    /**
     * Rotation of the piece. 
     */
    private int[][] myCoordinates;
    
    /**
     * Frozen blocks. 
     */
    private List<Block[]> myFrozen;
    
    /**
     * Timer.
     */
    private Timer myTimer;
    
    /**
     * The game board.
     */
    private Board myBoard;
    
    /**
     * Current piece.
     */
    private AbstractPiece myPiece;
    
    /**
     * Next piece.
     */
    private AbstractPiece myNextPiece;
    
    /**
     * If the game is being played.
     */
    private Boolean myPlaying;
    
    /**
     * Panel for displaying next piece.
     */
    private NextPiecePanel myPiecePanel;
    
    /**
     * If the game is paused.
     */
    private boolean myPaused;
    
    /**
     * If the key listener can be used. 
     */
    private boolean myKeys;
    
    /**
     * Score. 
     */
    private int myScore;
    
    /**
     * Level. 
     */
    private int myLevel;
    
    /**
     * Background image. 
     */
    private BufferedImage myImage;
    
    /**
     * Class for level image and sound. 
     */
    private CustomizableLevel myCustomizable;
    
    /**
     * Text displayed when paused or game over. 
     */
    private String myText;
    
    /**
     * Initializes all of the fields of the playing area. 
     * Constructs the playing area. 
     * @param theButtonPanel Button panel on the GUI.
     * @param theNextPanel Next piece panel on the GUI.
     */
    public PlayingArea(final ButtonPanel theButtonPanel, final NextPiecePanel theNextPanel) {
        super();
        constructionHelper(theButtonPanel, theNextPanel);
    }
    
    /**
     * Helper method for the constructor. 
     * @param theButtonPanel Button panel on the GUI.
     * @param theNextPanel Next piece panel on the GUI.
     */
    private void constructionHelper(final ButtonPanel theButtonPanel
                                   , final NextPiecePanel theNextPanel) {
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        myBoard = new Board(NUM_COLUMNS, NUM_ROWS, null);
        firePropertyChange("Board", null, myBoard);
        theButtonPanel.setBoard(myBoard);
        myTime = START_TIME;
        myTimer = new Timer(myTime, this);
        myBoard.addObserver(this);
        myPiece = (AbstractPiece) myBoard.getCurrentPiece();
        myNextPiece = (AbstractPiece) myBoard.getNextPiece();
        myFrozen = new ArrayList<Block[]>();
        myCoordinates = new int[BLOCKS][2];
        myPiecePanel = theNextPanel;
        myScore = 0;
        myLevel = 0;
        setupBooleans();
        myText = "";
        myCustomizable = new CustomizableLevel(myNextPiece);
        mySoundPlayer = new SoundPlayer();
        myCustomizable.setSound(mySoundPlayer);
        try {
            myImage = ImageIO.read(new File("images/mainmenu.jpg"));
            mySoundPlayer.loop(MENU_SOUND);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        addPropertyChangeListener(myCustomizable);
    }
    
    /**
     * Helps the constructor setup the booleans.
     */
    private void setupBooleans() {
        myPlaying = false;
        myPaused = false;
        myKeys = false;
        myEndGame = false;
        myGrid = false;
        myMuted = false;
    }
    
    /**
     * Draws the current piece in motion as well as the frozen blocks to the Panel.
     * Also draws the String when the game is paused or the game is over. 
     * @param theGraphics Graphics for drawing.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(myImage, 0, 0, null);
                
        if (myPlaying || myEndGame) {
            //draw grid
            if (myGrid) {
                drawGrid(g2d);
            }
            
            //draw frozen pieces
            drawFrozen(g2d);
            
            //draw falling piece
            drawFallingPiece(g2d);
            
            if (myEndGame) {
                drawString(g2d);
            }
        } else if (myPaused || myBoard.isGameOver()) {
            drawString(g2d);
        }
    }
        
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Object source = theEvent.getSource();
        if (source.equals(myTimer)) {
            playingTimerHelper();
        }
    }
    
    /**
     * Helper method for the timer event. 
     * Used to eliminate nested if statements in part b.
     */
    private void playingTimerHelper() {
        if (myBoard.isGameOver()) {
            myGrid = false;
            myPlaying = false;
            firePropertyChange("ClearNext", null, false);
            firePropertyChange(ENABLE_GRID_STRING, null, false);
            //firePropertyChange(SET_SAVE_LOAD, null, LOAD_GAME);
            firePropertyChange("GAMEOVER", null, true);
            myTimer.stop();
            if (!myMuted) {
                mySoundPlayer.stopAll();
                mySoundPlayer.play(GAMEOVER_SOUND);
            }
            myText = GAMEOVER_STRING;
            myKeys = false;
            repaint();
        } else {
            myBoard.step();
        }
    }
        
    /**
     * Updates the GUI when something changes on the board. 
     * @param theObservable observable object.
     * @param theObject the cleared lines. 
     */
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        if (theObservable instanceof Board) {
            if (myTimer.isRunning()) {
                if (theObject != null) {
                    updateScorePanel((int) theObject);
                }
                myPiece = (AbstractPiece) myBoard.getCurrentPiece(); 
                myNextPiece = (AbstractPiece) myBoard.getNextPiece();
                firePropertyChange("Color", null, myCustomizable.colorHelper(myLevel
                                                         , myNextPiece.getBlock()));
                myPiecePanel.setPiece(myNextPiece.getBoardCoordinates(), myPlaying);
                myCoordinates = myPiece.getBoardCoordinates();
                myFrozen = myBoard.getFrozenBlocks();
                repaint();
            } else {
                if (!myTimer.isRunning()) {
                    myTimer.start();
                    myLevel = 1;
                    myPlaying = true;
                    myKeys = true;
                } 
            }
        }
    }

    
    @Override
    public void keyTyped(final KeyEvent theEvent) {
        
    }

    /**
     * Key Listener for playing the game. 
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (myKeys) {
            if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                myBoard.moveLeft();
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myBoard.moveRight();
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_UP) {
                myBoard.rotate();
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myBoard.moveDown();
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.hardDrop();
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                if (myTimer.isRunning()) {
                    myText = PAUSE_STRING;
                    myTimer.stop();
                    myPlaying = false; 
                    myPaused = true;
                    repaint();
                } else {
                    myText = GAMEOVER_STRING;
                    myTimer.start();
                    myPlaying = true;
                    myPaused = false;
                    repaint();
                }
            }
        }
    }

    @Override
    public void keyReleased(final KeyEvent theEvent) {
        
    }
    
   
    
    /**
     *  Property change listener that updates the 
     *  playing area based off of buttons that are pressed. 
     *  Also allows for muting and unmuting sound. 
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        final String setMute = "SetMute";
        final String isMute = "isMuted";
        if (theEvent.getPropertyName().equals("NewGame")) {
            newGamePropertyHelper();      
        } else if (theEvent.getPropertyName().equals("Level Up")) {
            levelUpPropertyHelper(theEvent);
        } else if (theEvent.getPropertyName().equals("CONTROLS")) {
            helpControls(theEvent);
                
        } else if (theEvent.getPropertyName().equals("END GAME") 
                        && (boolean) theEvent.getNewValue()) {
            endGamePropertyHelper();
        } else if (theEvent.getPropertyName().equals("Grid")) {
            myGrid = (boolean) theEvent.getNewValue();
            repaint();
        } else if (theEvent.getPropertyName().equals("Mute")) {
            myMuted = true;
            mySoundPlayer.stopAll();
            firePropertyChange(setMute, null, "UNMUTE");
            firePropertyChange(isMute, null, myMuted);
        } else if (theEvent.getPropertyName().equals("Unmute")) {
            myMuted = false;
            firePropertyChange(isMute, null, myMuted);
            myCustomizable.getFeatures(myLevel, myImage, myPlaying);
            firePropertyChange(setMute, null, "MUTE");
        } else if (theEvent.getPropertyName().equals("CREDITS")) {
            creditsPropertyHelper(theEvent);
        }
            //else if (theEvent.getPropertyName().equals("SaveGame")) {
//            final JDialog save = new JDialog();
//            final JTextField date = new JTextField("Date: ");
//            save.setTitle(SAVE_GAME);
//            save.setSize(100, 50);
//            save.add(date, BorderLayout.CENTER);
//            save.pack();
//            save.setLocationRelativeTo(null);
//            save.setVisible(true);
//            if (save.isActive()) {
//                myText = PAUSE_STRING;
//                myPlaying = false;
//                myPaused = true;
//                myTimer.stop();
//                repaint();
//            }
        //}
    }
    
    /**
     * Helps with credits property change to reduce complexity. 
     * @param theEvent property change event.
     */
    private void creditsPropertyHelper(final PropertyChangeEvent theEvent) {
        if ((boolean) theEvent.getNewValue()) {
            if (myPlaying) {
                myText = PAUSE_STRING;
            } else if (myEndGame || myBoard.isGameOver()) {
                myText = GAMEOVER_STRING;
            }
            myPlaying = false;
            myPaused = true;
            myTimer.stop();
        } else if (!(boolean) theEvent.getNewValue() && myPlaying) {
            myPlaying = true;
            myPaused = false;
            myTimer.start();
        }
        repaint();
    }
    
    /**
     * Helps with end game property change to reduce complexity. 
     */
    private void endGamePropertyHelper() {
        myGrid = false;
        firePropertyChange(ENABLE_GRID_STRING, null, false);
        //firePropertyChange(SET_SAVE_LOAD, null, LOAD_GAME);
        myTimer.stop();
        myText = GAMEOVER_STRING;
        if (!myMuted) {
            mySoundPlayer.stopAll();
            mySoundPlayer.play(GAMEOVER_SOUND);
        }
        myPlaying = false;
        myKeys = false;
        myPaused = true;
        myEndGame = true;
        repaint();
    }
    
    /**
     * Helps with level up property change to reduce complexity. 
     * @param theEvent property change event.
     */
    private void levelUpPropertyHelper(final PropertyChangeEvent theEvent) {
        myLevel = (int) theEvent.getNewValue();
        myTime -= DECREMENT_TIME;
        myTimer.setDelay(Math.max(DECREMENT_TIME, myTime));
        if (!myMuted) {
            mySoundPlayer.stopAll();
            mySoundPlayer.play(LEVEL_UP_SOUND);
        } 
        myImage = myCustomizable.getFeatures(myLevel, myImage, myPlaying);
        repaint();
    }
    
    /**
     * Helps with new game property change to reduce complexity. 
     */
    private void newGamePropertyHelper() {
        firePropertyChange(ENABLE_GRID_STRING, null, true);
        //firePropertyChange(SET_SAVE_LOAD, null, SAVE_GAME);
        mySoundPlayer.stopAll();
        myLevel = 1;
        myScore = 0;
        myTime = START_TIME;
        myTimer.setDelay(myTime);
        myPlaying = true;
        myPaused = false;
        myKeys = true;
        myEndGame = false; 
        myImage = myCustomizable.getFeatures(myLevel, myImage, myPlaying);
    }
    
    /**
     * Helps with controls property change to reduce complexity. 
     * @param theEvent property change event.
     */
    private void helpControls(final PropertyChangeEvent theEvent) {
        if ((boolean) theEvent.getNewValue()) {
            if (myPlaying) {
                myText = PAUSE_STRING;
            } else if (myEndGame || myBoard.isGameOver()) {
                myText = GAMEOVER_STRING;
            }
            myPlaying = false;
            myPaused = true;
            myTimer.stop();
        } else if (!(boolean) theEvent.getNewValue() && myPlaying) {
            myPlaying = true;
            myPaused = false;
            myTimer.start();
        }
        repaint();
    }
    
    /**
     * Draws the grid to the panel. 
     * @param theG2d The graphics for drawing. 
     */
    private void drawGrid(final Graphics2D theG2d) {
        theG2d.setColor(Color.WHITE);
        
        for (int i = 1;  i < NUM_COLUMNS; i++) {
            theG2d.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, (NUM_ROWS + 1) * TILE_SIZE);
        }
        for (int i = 1; i < NUM_ROWS + 1; i++) {
            theG2d.drawLine(0, i * TILE_SIZE, NUM_COLUMNS * TILE_SIZE, i * TILE_SIZE);
        }
    }
    /**
     * Draws the frozen blocks. 
     * @param theG2d the graphics used for drawing. 
     */
    private void drawFrozen(final Graphics2D theG2d) {
        
        Block[] frozenBlocks;
         
        for (int i = 0; i < myFrozen.size(); i++) {
            frozenBlocks = myFrozen.get(i);
            for (int j = 0; j < frozenBlocks.length; j++) {
                if (!(frozenBlocks[j] == Block.EMPTY)) {
                    theG2d.setColor(myCustomizable.colorHelper(myLevel, frozenBlocks[j]));
                    theG2d.fillRoundRect(j * TILE_SIZE, ((NUM_ROWS - i) 
                                    * TILE_SIZE) - TILE_SIZE
                                   , TILE_SIZE, TILE_SIZE, ROUND, ROUND);
                    theG2d.setColor(Color.BLACK);
                    theG2d.drawRoundRect(j * TILE_SIZE, ((NUM_ROWS - i) 
                                    * TILE_SIZE) - TILE_SIZE
                                   , TILE_SIZE, TILE_SIZE, ROUND, ROUND); 
                }
            }         
        }
    }
    /**
     * Draws the current piece. 
     * @param theG2d the graphics used for drawing. 
     */
    private void drawFallingPiece(final Graphics2D theG2d) {
        for (int i = 0; i < BLOCKS; i++) {
            theG2d.setColor(myCustomizable.colorHelper(myLevel, myPiece.getBlock()));
            theG2d.fillRoundRect(myCoordinates[i][0] * TILE_SIZE
                            , ((NUM_ROWS - myCoordinates[i][1]) * TILE_SIZE) - TILE_SIZE
                            , TILE_SIZE, TILE_SIZE, ROUND, ROUND);
            theG2d.setColor(Color.BLACK);
            theG2d.drawRoundRect(myCoordinates[i][0] * TILE_SIZE
                           , ((NUM_ROWS - myCoordinates[i][1]) * TILE_SIZE) -  TILE_SIZE
                           , TILE_SIZE, TILE_SIZE, ROUND, ROUND);
        }
    }
    
    /**
     * Draws the game over and paused strings. 
     * @param theG2d the graphics used for drawing. 
     */
    private void drawString(final Graphics2D theG2d) {
        theG2d.setColor(Color.WHITE);
        
        final Font font = new Font(Font.SERIF, Font.BOLD + Font.ITALIC, 50);
        
        final FontRenderContext renderContext = theG2d.getFontRenderContext();
        final GlyphVector glyphVector = font.createGlyphVector(renderContext
                                                               , myText);
        final Rectangle2D visualBounds = glyphVector.getVisualBounds().getBounds();

        final int x =
                (int) ((getWidth() - visualBounds.getWidth()) / 2 - visualBounds.getX());
        final int y =
                (int) ((getHeight() - visualBounds.getHeight()) / 2 - visualBounds.getY());
        theG2d.setFont(font);
        theG2d.drawString(myText, x, y);
    }
    
    /**
     * Updates the score panel with the new score when lines are cleared. 
     * @param theCleared lines cleared. 
     */
    private void updateScorePanel(final int theCleared) {
        myScore += theCleared * TILE_SIZE + Math.pow(LINE_MULTIPLIER, theCleared);
        firePropertyChange("Cleared", null, theCleared);
        firePropertyChange("Score", null, myScore);
    }
}
