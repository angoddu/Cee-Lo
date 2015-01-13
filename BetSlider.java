import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;

/**
 * BetSlider.java.
 * Creates a slideable bet slider
 * @author Andrew Goddu
 *
 */
public class BetSlider extends ShapeGroup
{
    private Rectangle slider, base;
    private TextBox amount;
    private Point currentPoint;

    private int maxBet, bet, height = 20;
    private boolean active = true;

    /**
     * Constructs a bet slider at location 0,0.
     */
    public BetSlider ( )
    {
        maxBet = 100;

        base = new Rectangle ( );
        base.setLocation ( 0, 0 );
        base.setSize ( maxBet, height );
        base.setColor ( Color.GRAY );
        super.add ( base );

        slider = new Rectangle ( );
        slider.setLocation ( 0, 0 );
        slider.setSize ( maxBet / 10, height );
        bet = maxBet / 10;
        slider.setColor ( Color.RED );
        super.add ( slider );

        amount = new TextBox ( );
        amount.setLocation ( 0, 25 );
        amount.setText ( "$" + slider.getWidth ( ) + "/$" + maxBet );
        amount.setFrameColor ( new Color ( 0, 0, 0, 0 ) );
        super.add ( amount );
    }

    /**
     * Constructs slider at location x,y.
     * @param x int X coordinate
     * @param y int Y coordinate
     */
    public BetSlider ( int x, int y )
    {
        this ( );
        setLocation ( x, y );
    }

    /**
     * Adds or subtracts the winnings and adjusts the max bet accordingly.
     * @param winnings int Amount of the winnings
     */
    public void addWinnings ( int winnings )
    {
        maxBet += winnings;
        base.setSize ( maxBet, height );

        if ( bet > maxBet )
        {
            bet = maxBet;
            slider.setSize ( bet, height );
        }

        amount.setText ( "$" + bet + "/$" + maxBet );
    }

    /**
     * Enables of disables the slider.
     * @param b boolean Active value
     */
    public void setActive ( boolean b )
    {
        active = b;
        if ( active )
            slider.setColor ( Color.RED );
        else
            slider.setColor ( Color.BLACK );
    }

    /**
     * Sets the initial start location for the slider.
     * @param e MouseEvent Event stored
     */
    public void mousePressed ( MouseEvent e )
    {
        currentPoint = e.getPoint ( );
    }

    /**
     * Slides the slider and sets the bet.
     * @param e MouseEvent Event stored
     */
    public void mouseDragged ( MouseEvent e )
    {
        if ( active )
        {
            bet += ( e.getX ( ) - currentPoint.x );

            if ( bet >= maxBet )
                bet = maxBet;

            if ( bet <= 1 )
                bet = 1;

            slider.setSize ( bet, height );
            amount.setText ( "$" + bet + "/$" + maxBet );

            currentPoint = e.getPoint ( );
        }
    }

    /**
     * Returns the bet total.
     * @return slider.getWidth() int Bet amount
     */
    public int getBet ( )
    {
        return bet;
    }

    /**
     * Returns the maximum bet allowed
     * @return base.getWidth() int Maximum bet value
     */
    public int getMaxBet ( )
    {
        return base.getWidth ( );
    }

    /**
     * Main Method.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        new BetSlider ( );
    }
}
