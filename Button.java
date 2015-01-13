import wheelsunh.users.*;
import java.awt.Color;
import java.awt.event.*;

/**
 * Button.java.
 * Created a button for rolling three dice
 * @author Andrew Goddu
 *
 */
public class Button extends Rectangle
{
    private Dice dice1, dice2, dice3;
    private boolean active = false;
    private Color color;

    /**
     * Constructs the button that requires three dice.
     * @param d1 Dice Left die
     * @param d2 Dice Middle die
     * @param d3 Dice Right die
     */
    public Button ( Dice d1, Dice d2, Dice d3 )
    {
        super ( );
        setFrameColor ( Color.BLACK );
        dice1 = d1;
        dice2 = d2;
        dice3 = d3;
    }

    /**
     * Determines if the button should roll or not.
     * @param newActive boolean Active status
     */
    public void setActive ( boolean newActive )
    {
        active = newActive;

        if ( active )
            super.setFillColor ( color );
        else
            super.setFillColor ( Color.GRAY );
    }

    /**
     * Returns the active status of the button.
     * @return active Boolean Active status
     */
    public boolean getStatus ( )
    {
        return active;
    }

    /**
     * Sets the color of the button.
     * @param c Color Fill color of the button
     */
    public void setColor ( Color c )
    {
        super.setFillColor ( c );
        color = c;
    }

    /**
     * Sets the body color of the dice to green.
     */
    public void win ( )
    {
        dice1.setColor ( Color.GREEN );
        dice2.setColor ( Color.GREEN );
        dice3.setColor ( Color.GREEN );
    }

    /**
     * Sets the body color of the dice to yellow.
     */
    public void push ( )
    {
        dice1.setColor ( Color.YELLOW );
        dice2.setColor ( Color.YELLOW );
        dice3.setColor ( Color.YELLOW );
    }

    /**
     * Sets the body color of the dice to red.
     */
    public void lose ( )
    {
        dice1.setColor ( Color.RED );
        dice2.setColor ( Color.RED );
        dice3.setColor ( Color.RED );
    }

    /**
     * Sets the body color of the dice to white.
     */
    public void blank ( )
    {
        dice1.setColor ( Color.WHITE );
        dice2.setColor ( Color.WHITE );
        dice3.setColor ( Color.WHITE );
    }

    /**
     * Rolls the dice.
     * 
     * @param e MouseEvent Event storage
     */
    public void mouseClicked ( MouseEvent e )
    {
        if ( active )
        {
            dice1.roll ( );
            dice2.roll ( );
            dice3.roll ( );
        }
    }

    /**
     * Main method.
     * Used to test Button.java
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        Dice d1 = new Dice ( );
        Dice d2 = new Dice ( 50, 0 );
        Dice d3 = new Dice ( 100, 0 );
        Button b1 = new Button ( d1, d2, d3 );
        b1.setActive ( true );
    }
}
