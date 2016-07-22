import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JOptionPane;
public class Card
{
    private String file;
    private BufferedImage img;
    private String name;
    private int width,height;
    public Card()
    {
        file=null;
        name=null;
    }

    public Card(String n, String w1, int w, int h)
    {
        file = w1;
        name=n;
        width = w;
        height = h;
        try
        {
            img = ImageIO.read(new File(file));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,null,"Error," + w1 + " file name does not exist", 0);
        }
    }

    public String getFile()
    {
        return file;
    }

    public BufferedImage getImage()
    {
        return img;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }

    public String getName()
    {
        return name;
    }
}
