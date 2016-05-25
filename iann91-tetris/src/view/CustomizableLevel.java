/*
 * TCSS 305 - Autumn 2015
 * Assignment 6 - Tetris
 */
package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.AbstractPiece;
import model.Block;

/**
 * Provides the features for the game. 
 * Sets the background image, sound and color of the pieces. 
 * @author iann91
 * @version 10 December
 */
public class CustomizableLevel extends JPanel implements PropertyChangeListener {
    
    /**
     * Serial id.
     */
    private static final long serialVersionUID = 6306265137031927645L;

    /**
     * Level one.
     */
    private static final int LEVEL_ONE = 1;
    
    /**
     * Level two.
     */
    private static final int LEVEL_TWO = 2;
    
    /**
     * Level three.
     */
    private static final int LEVEL_THREE = 3;
    
    /**
     * Level four.
     */
    private static final int LEVEL_FOUR = 4;
    
    /**
     * Level five. 
     */
    private static final int LEVEL_FIVE = 5;
    
    /**
     * Level six.
     */
    private static final int LEVEL_SIX = 6;
    
    /**
     * Level seven. 
     */
    private static final int LEVEL_SEVEN = 7;
    
    /**
     * Level eight. 
     */
    private static final int LEVEL_EIGHT = 8;
    
    /**
     * Level nine. 
     */
    private static final int LEVEL_NINE = 9;
    
    /**
     * Menu sound. 
     */
    private static final String MENU_SOUND = "music/GreatFairy.wav";
    
    /**
     * Level one sound. 
     */
    private static final String LEVEL_ONE_SOUND = "music/KidLink.wav";
    
    /**
     * Level two sound. 
     */
    private static final String LEVEL_TWO_SOUND = "music/lostwoods.wav";
    
    /**
     * Level three sound. 
     */
    private static final String LEVEL_THREE_SOUND = "music/HyruleField.wav";
    
    /**
     * Level four sound. 
     */
    private static final String LEVEL_FOUR_SOUND = "music/foresttemple.wav";
    
    /**
     * Level five sound. 
     */
    private static final String LEVEL_FIVE_SOUND = "./music/SongOfStorms.wav";
    
    /**
     * Level six sound. 
     */
    private static final String LEVEL_SIX_SOUND = "./music/goroncity.wav";
    
    /**
     * Level seven sound. 
     */
    private static final String LEVEL_SEVEN_SOUND = "music/watertemple.wav";
    
    /**
     * Level eight sound. 
     */
    private static final String LEVEL_EIGHT_SOUND = "music/gerudovalley.wav";
    
    /**
     * Level nine sound. 
     */
    private static final String LEVEL_NINE_SOUND = "music/spirittemple.wav";
    
    /**
     * Level ten sound. 
     */
    private static final String LEVEL_TEN_SOUND = "music/ganonscastle.wav";
    
    /**
     * Level one image.
     */
    private static final String LEVEL_ONE_IMAGE = "images/decutree.png";
                    
    /**
     * Level two image. 
     */
    private static final String LEVEL_TWO_IMAGE = "images/kidlink2.jpg";
    
    /**
     * Level three image. 
     */
    private static final String LEVEL_THREE_IMAGE = "images/North_Hyrule_Field.jpg";
    
    /**
     * Level four image. 
     */
    private static final String LEVEL_FOUR_IMAGE = "images/foresttemple.jpg";
    
    /**
     * Level five image. 
     */
    private static final String LEVEL_FIVE_IMAGE = "images/songofstorms.png";
    
    /**
     * Level six image. 
     */
    private static final String LEVEL_SIX_IMAGE = "images/deathmountain2.jpg";
    
    /**
     * Level seven image. 
     */
    private static final String LEVEL_SEVEN_IMAGE = "images/watertemple.png";
    
    /**
     * Level eight image. 
     */
    private static final String LEVEL_EIGHT_IMAGE = "images/gerudovalley.jpg";
    
    /**
     * Level nine image. 
     */
    private static final String LEVEL_NINE_IMAGE = "images/spirittemple.jpg";

    /**
     * Level ten image. 
     */
    private static final String LEVEL_TEN_IMAGE = "images/ganoncastle.jpg";
    
    /**
     * Main menu image. 
     */
    private static final String MAIN_MENU_IMAGE = "images/mainmenu.jpg";
    
    /**
     * RGB value 102.
     */
    private static final int ONE_HUNDRED_TWO = 102;
    
    /**
     * RGB value 153.
     */
    private static final int ONE_FIFTY_THREE = 153;
    
    /**
     * RGB value 204.
     */
    private static final int TWO_HUNDRED_FOUR = 204;
    
    /**
     * RGB value 255.
     */
    private static final int TWO_HUNDRED_FIFTY_FIVE = 255;
    
    /**
     * RGB value 51.
     */
    private static final int FIFTY_ONE = 51;
    
    /**
     * Next piece. 
     */
    private final AbstractPiece myNextPiece;
    
    /**
     * Sound player. 
     */
    private SoundPlayer mySoundPlayer;
    
    /**
     * Background image. 
     */
    private BufferedImage myImage;
    
    /**
     * Muted boolean. 
     */
    private boolean myMuted;
    
    /**
     * Block color. 
     */
    private Color myBlockColor;
    
    /**
     * Constructs the customizable level. 
     * @param thePiece next piece. 
     */
    public CustomizableLevel(final AbstractPiece thePiece) {
        super();
        myNextPiece = thePiece;
        myMuted = false;
    }
    
    /**
     * Sets this sound player to the sound player on the playing area. 
     * @param theSoundPlayer sound player. 
     */
    public void setSound(final SoundPlayer theSoundPlayer) {
        mySoundPlayer = theSoundPlayer;
    }
    
    /**
     * Sets each block to its own color depending on the level.
     * @param theLevel current level. 
     * @param theBlock the block. 
     * @return the block color. 
     */
    public Color colorHelper(final int theLevel, final Block theBlock) {
        switch (theBlock) {
            case I: 
                setIColor(theLevel);
                break;
            case J: 
                setJColor(theLevel);
                break;
            case L: 
                setLColor(theLevel);
                break;
            case O: 
                setOColor(theLevel);
                break;
            case S:
                setSColor(theLevel);
                break;
            case T: 
                setTColor(theLevel);
                break;
            case Z:
                setZColor(theLevel);
                break;
            default: 
                myBlockColor = Color.BLACK;
                break;
        }
        if (theBlock == myNextPiece.getBlock()) {
            firePropertyChange("Color", null, myBlockColor);
        }   
        return myBlockColor;
    }
    
    /**
     * Sets the background image depending on the level.
     * Also changes the sound depending on the level. 
     * @param theLevel current level.
     * @param theImage background image. 
     * @param thePlaying is game being played. 
     * @return the background image. 
     */
    public BufferedImage getFeatures(final int theLevel
                                   , final BufferedImage theImage, final boolean thePlaying) {
        myImage = theImage; 
        if (thePlaying) {
            helpGetFeatures(theLevel);
        } else {
            helpMenuFeatures();
        }
        return myImage;
    }
    /**
     * Helper method for setting the features to reduce complexity. 
     * @param theLevel current level. 
     */
    private void helpGetFeatures(final int theLevel) {
        if (theLevel == LEVEL_ONE) {
            helpLevelOneFeatures();
        } else if (theLevel == LEVEL_TWO) {
            helpLevelTwoFeatures();
        } else if (theLevel == LEVEL_THREE) {
            helpLevelThreeFeatures();
        } else if (theLevel == LEVEL_FOUR) {
            helpLevelFourFeatures();
        } else if (theLevel == LEVEL_FIVE) {
            helpLevelFiveFeatures();
        } else if (theLevel == LEVEL_SIX) {
            helpLevelSixFeatures();
        } else if (theLevel == LEVEL_SEVEN) {
            helpLevelSevenFeatures();
        } else if (theLevel == LEVEL_EIGHT) {
            helpLevelEightFeatures();
        } else if (theLevel == LEVEL_NINE) {
            helpLevelNineFeatures();
        } else {
            helpLevelTenFeatures();
        } 
    }
    /**
     * Helper method for first level features to reduce complexity.
     */
    private void helpLevelOneFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_ONE_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_ONE_SOUND);
        }
    }
    
    /**
     * Helper method for second level features to reduce complexity.
     */
    private void helpLevelTwoFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_TWO_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_TWO_SOUND);
        }
    }
    
    /**
     * Helper method for third level features to reduce complexity.
     */
    private void helpLevelThreeFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_THREE_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_THREE_SOUND);
        }
    }
    
    /**
     * Helper method for fourth level features to reduce complexity.
     */
    private void helpLevelFourFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_FOUR_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_FOUR_SOUND);
        }
    }
    
    /**
     * Helper method for fifth level features to reduce complexity.
     */
    private void helpLevelFiveFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_FIVE_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_FIVE_SOUND);
        }
    }
    
    /**
     * Helper method for sixth level features to reduce complexity.
     */
    private void helpLevelSixFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_SIX_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_SIX_SOUND);
        }
    }
    
    /**
     * Helper method for seventh level features to reduce complexity.
     */
    private void helpLevelSevenFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_SEVEN_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_SEVEN_SOUND);
        }
    }
    
    /**
     * Helper method for eighth level features to reduce complexity.
     */
    private void helpLevelEightFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_EIGHT_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_EIGHT_SOUND);
        }
    }
    
    /**
     * Helper method for ninth level features to reduce complexity.
     */
    private void helpLevelNineFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_NINE_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_NINE_SOUND);
        }
    }
    
    /**
     * Helper method for tenth level features to reduce complexity.
     */
    private void helpLevelTenFeatures() {
        try {
            myImage = ImageIO.read(new File(LEVEL_TEN_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(LEVEL_TEN_SOUND);
        }
    }
    
    /**
     * Helper method for menu features to reduce complexity.
     */
    private void helpMenuFeatures() {
        try {
            myImage = ImageIO.read(new File(MAIN_MENU_IMAGE));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }   
        if (!myMuted) {
            clearPrevSong();
            mySoundPlayer.loop(MENU_SOUND);
        }
    }
    
    /**
     * Sets the block color for the I block for each level.
     * @param theLevel current level.
     */
    private void setIColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(ONE_FIFTY_THREE, ONE_HUNDRED_TWO, 0);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(ONE_FIFTY_THREE, ONE_HUNDRED_TWO, 0);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        }  else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, 0, 0);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(0, TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(ONE_FIFTY_THREE, 0, ONE_FIFTY_THREE);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FOUR, FIFTY_ONE);
        } else {
            myBlockColor = new Color(ONE_FIFTY_THREE, 0, ONE_FIFTY_THREE);
        }
    }
    
    /**
     * Sets the block color for the J block for each level.
     * @param theLevel current level. 
     */
    private void setJColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(FIFTY_ONE, ONE_HUNDRED_TWO, 0);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(ONE_FIFTY_THREE, ONE_FIFTY_THREE, ONE_FIFTY_THREE);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(FIFTY_ONE, ONE_HUNDRED_TWO, 0);
        } else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(ONE_FIFTY_THREE, 0, 0);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(FIFTY_ONE, TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(ONE_HUNDRED_TWO, 0, ONE_HUNDRED_TWO);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FOUR);
        } else {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, 0, TWO_HUNDRED_FIFTY_FIVE);
        }
    }
    
    /**
     * Sets the block color for the L block for each level.
     * @param theLevel current level. 
     */
    private void setLColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR, 0);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR, 0);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(0, ONE_FIFTY_THREE, 0);
        }  else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(ONE_FIFTY_THREE, ONE_FIFTY_THREE
                                     , ONE_FIFTY_THREE);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, ONE_HUNDRED_TWO
                                   , ONE_HUNDRED_TWO);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(FIFTY_ONE, FIFTY_ONE, TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(FIFTY_ONE, 0, ONE_FIFTY_THREE);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE
                                   , ONE_FIFTY_THREE, ONE_HUNDRED_TWO);
        } else {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, 0, ONE_FIFTY_THREE);
        }
    }
    
    /**
     * Sets the block color for the O block for each level.
     * @param theLevel current level. 
     */
    private void setOColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, ONE_FIFTY_THREE, 0);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, ONE_FIFTY_THREE, 0);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(ONE_HUNDRED_TWO, ONE_HUNDRED_TWO
                                     , ONE_HUNDRED_TWO);
        }  else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(ONE_HUNDRED_TWO, ONE_HUNDRED_TWO
                                     , ONE_HUNDRED_TWO);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, ONE_HUNDRED_TWO
                                     , FIFTY_ONE);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(0, TWO_HUNDRED_FIFTY_FIVE, TWO_HUNDRED_FOUR);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(FIFTY_ONE, FIFTY_ONE, FIFTY_ONE);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        } else {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE
                                   , ONE_FIFTY_THREE, TWO_HUNDRED_FIFTY_FIVE);
        }
    }
    
    /**
     * Sets the block color for the S block for each level.
     * @param theLevel current level. 
     */
    private void setSColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(ONE_FIFTY_THREE, TWO_HUNDRED_FOUR, 0);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(ONE_FIFTY_THREE, TWO_HUNDRED_FOUR, 0);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(ONE_FIFTY_THREE, ONE_FIFTY_THREE, 0);
        }  else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, TWO_HUNDRED_FIFTY_FIVE
                                   , FIFTY_ONE);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(0, ONE_FIFTY_THREE, ONE_FIFTY_THREE);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, 0
                                     , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE
                                   , ONE_FIFTY_THREE, ONE_FIFTY_THREE);
        } else {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, TWO_HUNDRED_FIFTY_FIVE
                                     , TWO_HUNDRED_FIFTY_FIVE);
        }
    }
    
    /**
     * Sets the block color for the T block for each level.
     * @param theLevel current level. 
     */
    private void setTColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(0, ONE_FIFTY_THREE, FIFTY_ONE);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(0, ONE_FIFTY_THREE, FIFTY_ONE);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FIFTY_FIVE);
        }  else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(ONE_FIFTY_THREE, TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, ONE_HUNDRED_TWO, 0);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(ONE_FIFTY_THREE, ONE_FIFTY_THREE
                                   , TWO_HUNDRED_FIFTY_FIVE);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(0, FIFTY_ONE, 0);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        } else {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, 0, ONE_FIFTY_THREE);
        }
    }
    
    /**
     * Sets the block color for the Z block for each level.
     * @param theLevel current level. 
     */
    private void setZColor(final int theLevel) {
        if (theLevel == LEVEL_ONE || theLevel == LEVEL_FOUR) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        } else if (theLevel == LEVEL_TWO) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        } else if (theLevel == LEVEL_THREE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, ONE_FIFTY_THREE, 0);
        }  else if (theLevel == LEVEL_FIVE) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, TWO_HUNDRED_FIFTY_FIVE
                                   , TWO_HUNDRED_FOUR);
        } else if (theLevel == LEVEL_SIX) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, FIFTY_ONE, 0);
        } else if (theLevel == LEVEL_SEVEN) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR
                                   , TWO_HUNDRED_FOUR, TWO_HUNDRED_FOUR);
        } else if (theLevel == LEVEL_EIGHT) {
            myBlockColor = new Color(TWO_HUNDRED_FIFTY_FIVE, TWO_HUNDRED_FOUR, 0);
        } else if (theLevel == LEVEL_NINE) {
            myBlockColor = new Color(TWO_HUNDRED_FOUR, ONE_FIFTY_THREE, 0);
        } else {
            myBlockColor = new Color(ONE_HUNDRED_TWO, ONE_HUNDRED_TWO, ONE_HUNDRED_TWO);
        }
    }

    /**
     * Property change for muting music. 
     * @param theEvent mute pressed. 
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("isMuted")) {
            myMuted = (boolean) theEvent.getNewValue();
        }
    }
    /**
     * Clears memory heap of previous songs. 
     */
    private void clearPrevSong() {
        mySoundPlayer.clearClips();
    }
}
