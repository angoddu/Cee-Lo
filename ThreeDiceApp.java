import wheelsunh.users.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * ThreeDiceApp.java.
 * Creates a game of C-Lo
 * @author Andrew Goddu
 *
 */
public class ThreeDiceApp extends ShapeGroup
{
    private Dice bank1, bank2, bank3, play1, play2, play3;
    private Button bankerButton, playerButton;
    private TextBox player, banker, playerLabel, bankerLabel;
    private BetSlider slider;

    private int rollResult, beatPoint, bet;

    /*----------Result table---------------------- 
     * 0 = Three of a kind - Banker wins
     * 1 = Ace Negative - Banker loses
     * 2-5 = Pair # - Higher wins
     * 6 = Pair 6 - Banker wins
     * 7 = Strung flower (4,5,6) - Banker wins
     * 8 = Dancing Dragon (1,2,3) - Banker loses
     -------------------------------------------*/

    /*-------Turn-------
     * 1 = Banker
     * 2 = Player 
     -----------------*/

    /**
     * Constructs game with six dice, two roll buttons, and a bet slider.
     */
    public ThreeDiceApp ( )
    {
        bank1 = new Dice ( 100, 100 );
        bank2 = new Dice ( 175, 100 );
        bank3 = new Dice ( 250, 100 );

        banker = new TextBox ( );
        banker.setText ( "Make a bet" );
        banker.setLocation ( 325, 100 );

        bankerLabel = new TextBox ( );
        bankerLabel.setText ( "Banker" );
        bankerLabel.setSize ( 52, 25 );
        bankerLabel.setLocation ( 39, 75 );

        play1 = new Dice ( 100, 200 );
        play2 = new Dice ( 175, 200 );
        play3 = new Dice ( 250, 200 );

        player = new TextBox ( );
        player.setLocation ( 325, 200 );

        playerLabel = new TextBox ( );
        playerLabel.setText ( "Player" );
        playerLabel.setSize ( 50, 25 );
        playerLabel.setLocation ( 40, 175 );

        bankerButton = new Button ( bank1, bank2, bank3 );
        bankerButton.setFillColor ( Color.GREEN );
        bankerButton.setLocation ( 40, 110 );
        bankerButton.setFrameColor ( Color.BLACK );
        bankerButton.setSize ( 50, 30 );
        super.add ( bankerButton );

        playerButton = new Button ( play1, play2, play3 );
        playerButton.setFrameColor ( Color.BLACK );
        playerButton.setLocation ( 40, 210 );
        playerButton.setSize ( 50, 30 );
        super.add ( playerButton );

        slider = new BetSlider ( );
        slider.setLocation ( 100, 300 );

        bankerButton.setActive ( true );
        playerButton.setActive ( false );

    }

    /**
     * Gets the results of the rolls.
     */
    public void getRollResult ( )
    {
        int roll1 = 0, roll2 = 0, roll3 = 0;

        if ( bankerButton.getStatus ( ) )
        {
            roll1 = bank1.getValue ( );
            roll2 = bank2.getValue ( );
            roll3 = bank3.getValue ( );
        } else if ( playerButton.getStatus ( ) )
        {
            roll1 = play1.getValue ( );
            roll2 = play2.getValue ( );
            roll3 = play3.getValue ( );
        }

        if ( roll1 == roll2 && roll2 == roll3 )
        {
            rollResult = 0; // Three of a kind
        } else if ( roll1 == roll2 )
        {
            rollResult = roll3; // Pair x
        } else if ( roll1 == roll3 )
        {
            rollResult = roll2; // Pair x
        } else if ( roll2 == roll3 )
        {
            rollResult = roll1; // Pair x
        } else if ( ( roll1 == 4 || roll2 == 4 || roll3 == 4 )
            && ( roll1 == 5 || roll2 == 5 || roll3 == 5 )
            && ( roll1 == 6 || roll2 == 6 || roll3 == 6 ) )
        {
            rollResult = 7; // Strung Flower
        } else if ( ( roll1 == 1 || roll2 == 1 || roll3 == 1 )
            && ( roll1 == 2 || roll2 == 2 || roll3 == 2 )
            && ( roll1 == 3 || roll2 == 3 || roll3 == 3 ) )
        {
            rollResult = 8; // Dancing Dragon
        } else
        {
            rollResult = - 1;
        }

    }

    /**
     * Gets the result of the rolls.
     * @param e MouseEvent Event stored
     */
    public void getResults ( MouseEvent e )
    {
        bet = slider.getBet ( );

        if ( bankerButton.getStatus ( )
            && e.getX ( ) > bankerButton.getXLocation ( )
            && e.getX ( ) < bankerButton.getXLocation ( )
                + bankerButton.getWidth ( )
            && e.getY ( ) > bankerButton.getYLocation ( )
            && e.getY ( ) < bankerButton.getYLocation ( )
                + bankerButton.getHeight ( ) )
        {
            bankerResult ( );
        } else if ( playerButton.getStatus ( )
            && e.getX ( ) > playerButton.getXLocation ( )
            && e.getX ( ) < playerButton.getXLocation ( )
                + playerButton.getWidth ( )
            && e.getY ( ) > playerButton.getYLocation ( )
            && e.getY ( ) < playerButton.getYLocation ( )
                + playerButton.getHeight ( ) )
        {
            playerResult ( );
        }

        if ( slider.getMaxBet ( ) <= 0
            && ( bankerButton.getStatus ( ) || playerButton.getStatus ( ) ) )
        {
            bankerButton.setActive ( false );
            playerButton.setActive ( false );
            JOptionPane.showMessageDialog ( null, "Game Over" );
        }
    }

    /**
     * Runs if the banker just rolled.
     */
    public void bankerResult ( )
    {

        System.out.println ( slider.getBet ( ) );
        playerButton.blank ( );
        player.setText ( "" );
        switch ( rollResult )
        {
            case 0:
                banker.setText ( "Three of a kind\nBanker wins " + bet );
                slider.addWinnings ( - bet );
                bankerButton.win ( );
                break;
            case 1:
                banker.setText ( "Ace Negative\nPlayer wins " + bet );
                slider.addWinnings ( bet );
                bankerButton.lose ( );
                break;
            case 2:
                banker.setText ( "Pair two\n" + "Player roll to beat two" );
                beatPoint = 2;
                bankerButton.setActive ( false );
                playerButton.setActive ( true );
                slider.setActive ( false );
                bankerButton.push ( );
                break;
            case 3:
                banker.setText ( "Pair three\n"
                    + "Player roll to beat three" );
                beatPoint = 3;
                bankerButton.setActive ( false );
                playerButton.setActive ( true );
                slider.setActive ( false );
                bankerButton.push ( );
                break;
            case 4:
                banker.setText ( "Pair four\n"
                    + "Player roll to beat four" );
                beatPoint = 4;
                bankerButton.setActive ( false );
                playerButton.setActive ( true );
                slider.setActive ( false );
                bankerButton.push ( );
                break;
            case 5:
                banker.setText ( "Pair five\n"
                    + "Player roll to beat five" );
                beatPoint = 5;
                bankerButton.setActive ( false );
                playerButton.setActive ( true );
                slider.setActive ( false );
                bankerButton.push ( );
                break;
            case 6:
                banker.setText ( "Pair six\nBanker wins " + bet );
                slider.addWinnings ( - bet );
                bankerButton.win ( );
                break;
            case 7:
                banker.setText ( "Strung Flower\nBanker wins " + bet );
                slider.addWinnings ( - bet );
                bankerButton.win ( );
                break;
            case 8:
                banker.setText ( "Dancing Dragon\nPlayer wins " + bet );
                slider.addWinnings ( bet );
                bankerButton.lose ( );
                break;
            default:
                banker.setText ( "Banker re-roll" );
                bankerButton.blank ( );
                slider.setActive ( true );
        }
    }

    /**
     * Runs if the player just rolled.
     */
    public void playerResult ( )
    {

        System.out.println ( slider.getBet ( ) );

        bankerButton.setActive ( true );
        playerButton.setActive ( false );
        slider.setActive ( true );

        switch ( rollResult )
        {
            case 0:
                player.setText ( "Three of a kind\nBanker wins " + bet );
                playerButton.lose ( );
                slider.addWinnings ( - bet );
                break;
            case 1:
                player.setText ( "Ace Negative\nPlayer wins " + bet );
                slider.addWinnings ( bet );
                playerButton.win ( );
                break;
            case 2:
                if ( rollResult > beatPoint )
                {
                    player.setText ( "Pair two\nPlayer wins " + bet );
                    playerButton.win ( );
                    slider.addWinnings ( bet );
                } else if ( rollResult == beatPoint )
                {
                    player.setText ( "Pair two\nGame is a push" );
                    playerButton.push ( );
                } else
                {
                    player.setText ( "Pair two\nBanker wins " + bet );
                    slider.addWinnings ( - bet );
                    playerButton.lose ( );
                }
                break;
            case 3:
                if ( rollResult > beatPoint )
                {
                    player.setText ( "Pair three\nPlayer wins " + bet );
                    playerButton.win ( );
                    slider.addWinnings ( bet );
                } else if ( rollResult == beatPoint )
                {
                    player.setText ( "Pair three\n" + "Game is a push " );
                    playerButton.push ( );
                } else
                {
                    player.setText ( "Pair three\nBanker wins " + bet );
                    slider.addWinnings ( - bet );
                    playerButton.lose ( );
                }
                break;
            case 4:
                if ( rollResult > beatPoint )
                {
                    player.setText ( "Pair four\nPlayer wins " + bet );
                    slider.addWinnings ( bet );
                    playerButton.win ( );
                } else if ( rollResult == beatPoint )
                {
                    player.setText ( "Pair four\n" + "Game is a push " );
                    playerButton.push ( );
                } else
                {
                    player.setText ( "Pair four\nBanker wins " + bet );
                    slider.addWinnings ( - bet );
                    playerButton.lose ( );
                }
                break;
            case 5:
                if ( rollResult > beatPoint )
                {
                    player.setText ( "Pair five\nPlayer wins " + bet );
                    slider.addWinnings ( - bet );
                    playerButton.win ( );
                } else if ( rollResult == beatPoint )
                {
                    player.setText ( "Pair five\n" + "Game is a push " );
                    playerButton.push ( );
                } else
                {
                    player.setText ( "Pair five\nBanker wins " + bet );
                    slider.addWinnings ( - bet );
                    playerButton.lose ( );
                }
                break;
            case 6:
                player.setText ( "Pair six\nBanker wins " + bet );
                slider.addWinnings ( - bet );
                playerButton.lose ( );
                break;
            case 7:
                player.setText ( "Strung Flower\nBanker wins " + bet );
                slider.addWinnings ( - bet );
                playerButton.lose ( );
                break;
            case 8:
                player.setText ( "Dancing Dragon\nPlayer wins " + bet );
                slider.addWinnings ( bet );
                playerButton.win ( );
                break;
            default:
                player.setText ( "Player re-roll" );
                bankerButton.setActive ( false );
                playerButton.setActive ( true );
                playerButton.blank ( );
                slider.setActive ( false );
        }
    }

    /**
     * Activates when the button is pressed.
     * @param e MouseEvent Event stored
     */
    public void mouseClicked ( MouseEvent e )
    {
        getRollResult ( );
        getResults ( e );
    }

    /**
     * Main method.
     * @param args String[]
     */
    public static void main ( String[] args )
    {
        new Frame ( );
        new ThreeDiceApp ( );
    }
}
