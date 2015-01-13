package Project8;

import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.*;

/**
 * Dice.java.
 * 
 * Creates a 2D dice with pips
 * @author Andrew Goddu
 *
 */
public class Dice extends ShapeGroup
{
    private RoundedRectangle body;
    private Ellipse pip1, pip2, pip3, pip4, pip5, pip6, pip7;
    private Dimension pipSize = new Dimension ( 10, 10 );
    private int dieValue;
    private Random gen = new Random ( );

    // Pip layout
    /*---------------------------------------
     *    pip1    empty   pip5
     *    pip2    pip4    pip6
     *    pip3    empty   pip7
    -----------------------------------------*/

    /**
     * Creates die with all 7 pips showing at location 0,0.
     */
    public Dice ( )
    {
        body = new RoundedRectangle ( Color.WHITE );
        body.setFrameColor ( Color.BLACK );
        body.setSize ( 50, 50 );
        body.setLocation ( 0, 0 );
        super.add ( body );

        pip1 = new Ellipse ( Color.BLACK );
        pip1.setSize ( pipSize );
        pip1.setLocation ( 5, 5 );
        super.add ( pip1 );

        pip2 = new Ellipse ( Color.BLACK );
        pip2.setSize ( pipSize );
        pip2.setLocation ( 5, 20 );
        super.add ( pip2 );

        pip3 = new Ellipse ( Color.BLACK );
        pip3.setSize ( pipSize );
        pip3.setLocation ( 5, 35 );
        super.add ( pip3 );

        pip4 = new Ellipse ( Color.BLACK );
        pip4.setSize ( pipSize );
        pip4.setLocation ( 20, 20 );
        super.add ( pip4 );

        pip5 = new Ellipse ( Color.BLACK );
        pip5.setSize ( pipSize );
        pip5.setLocation ( 35, 5 );
        super.add ( pip5 );

        pip6 = new Ellipse ( Color.BLACK );
        pip6.setSize ( pipSize );
        pip6.setLocation ( 35, 20 );
        super.add ( pip6 );

        pip7 = new Ellipse ( Color.BLACK );
        pip7.setSize ( pipSize );
        pip7.setLocation ( 35, 35 );
        super.add ( pip7 );

        one ( );
    }

    /**
     * Creates a die at Location x,y.
     * @param x int X coordinate
     * @param y int Y coordinate
     */
    public Dice ( int x, int y )
    {
        this ( );
        super.setLocation ( x, y );
    }

    /**
     * Rolls the dice and sets their value.
     */
    public void roll ( )
    {

        int side = gen.nextInt ( 6 ) + 1;

        switch ( side )
        {
            case 1:
                one ( );
                break;
            case 2:
                two ( );
                break;
            case 3:
                three ( );
                break;
            case 4:
                four ( );
                break;
            case 5:
                five ( );
                break;
            case 6:
                six ( );
                break;
            default:
                all ( );
                break;
        }
    }

    /**
     * Sets dice to show a one value.
     */
    public void one ( )
    {
        pip1.hide ( );
        pip2.hide ( );
        pip3.hide ( );
        pip4.show ( );
        pip5.hide ( );
        pip6.hide ( );
        pip7.hide ( );

        dieValue = 1;
    }

    /**
     * Sets dice to show a two value.
     */
    public void two ( )
    {
        pip1.show ( );
        pip2.hide ( );
        pip3.hide ( );
        pip4.hide ( );
        pip5.hide ( );
        pip6.hide ( );
        pip7.show ( );

        dieValue = 2;
    }

    /**
     * Sets dice to show a three value.
     */
    public void three ( )
    {
        pip1.show ( );
        pip2.hide ( );
        pip3.hide ( );
        pip4.show ( );
        pip5.hide ( );
        pip6.hide ( );
        pip7.show ( );

        dieValue = 3;
    }

    /**
     * Sets dice to show a four value.
     */
    public void four ( )
    {
        pip1.show ( );
        pip2.hide ( );
        pip3.show ( );
        pip4.hide ( );
        pip5.show ( );
        pip6.hide ( );
        pip7.show ( );

        dieValue = 4;
    }

    /**
     * Sets dice to show a five value.
     */
    public void five ( )
    {
        pip1.show ( );
        pip2.hide ( );
        pip3.show ( );
        pip4.show ( );
        pip5.show ( );
        pip6.hide ( );
        pip7.show ( );

        dieValue = 5;
    }

    /**
     * Sets dice to show a six value.
     */
    public void six ( )
    {
        pip1.show ( );
        pip2.show ( );
        pip3.show ( );
        pip4.hide ( );
        pip5.show ( );
        pip6.show ( );
        pip7.show ( );

        dieValue = 6;
    }

    /**
     * Sets dice to show all pips.
     * Only used for testing
     */
    public void all ( )
    {
        pip1.show ( );
        pip2.show ( );
        pip3.show ( );
        pip4.show ( );
        pip5.show ( );
        pip6.show ( );
        pip7.show ( );

        dieValue = 0;
    }

    /**
     * Sets the color of the dice.
     * @param c java.awt.Color Body color
     */
    public void setColor ( Color c )
    {
        body.setFillColor ( c );
    }

    /**
     * Returns the value of the dice.
     * @return dieValue int
     */
    public int getValue ( )
    {
        return dieValue;
    }

    /**
     * Main method.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        Dice d1 = new Dice ( 0, 50 );
        d1.one ( );
        Dice d2 = new Dice ( 60, 50 );
        d2.two ( );
        Dice d3 = new Dice ( 120, 50 );
        d3.three ( );
        Dice d4 = new Dice ( 180, 50 );
        d4.four ( );
        Dice d5 = new Dice ( 240, 50 );
        d5.five ( );
        Dice d6 = new Dice ( 300, 50 );
        d6.six ( );
    }
}