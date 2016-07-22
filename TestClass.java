import java.awt.Image;
/**
 * Write a description of class TestClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestClass
{
    public static void main()
    {
        Game game = new Game();
        Player player=new Player(game);
        player.drawACard();
        player.drawACard();
        System.out.println(player);
        
    }
}
