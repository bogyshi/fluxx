
/**
 * Write a description of class MouseListenerTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.applet.*; 
import java.awt.event.*; 
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MouseListenerTest extends Applet implements MouseMotionListener
{
    private Graphics bufferGraphics;
    private Image offscreen;
    private Dimension dim;
    private int x, y;
    private final int CARD_WIDTH = 100;
    private final int CARD_HEIGHT = 220;

    public void init()
    { 
        //         dim = getSize();
        addMouseMotionListener(this);
        //         offscreen = createImage(dim.width, dim.height);
        //         bufferGraphics = offscreen.getGraphics();
    }

    public void paint(Graphics g)
    {
        g.drawString("" + x, 1000, 50);
        g.drawString("" + y, 1000, 100);
        // ArrayList<BufferedImage> handImages = new ArrayList<BufferedImage>();
        //bufferGraphics.clearRect(0,0,dim.width, dim.width);
        // 
        //         for(Card c: game.getPlayer1().getHand())
        //         {
        //             try
        //             {
        //                 handImages.add(ImageIO.read(new File(c.getFileName())));
        //             }
        //             catch(Exception e)
        //             {
        // 
        //             }
        //         }

        //         int size = handImages.size();
        int size = 5;
        int cardPos = (int)((1100.0/(size)) - (CARD_WIDTH / 2.0));
        g.setColor(Color.RED);
        for(int i = 1; i <= size; i++)
        {
            if(y >= 700 && x >= cardPos * i && x < cardPos * i + CARD_WIDTH && x < cardPos * (i + 1))
            {
                //bufferGraphics.drawImage(handImages.get(i), i * cardPos, 450, this);
                g.setColor(Color.GREEN);
                g.fillRect(i * cardPos, 450, CARD_WIDTH, CARD_HEIGHT);
            }
            else
            {
                //bufferGraphics.drawImage(handImages.get(i), i * cardPos, 700, this);  
                g.setColor(Color.RED);
                g.fillRect(i * cardPos, 700, CARD_WIDTH, CARD_HEIGHT);
            }

        }

        //         size = game.getPlayer2().getHand().size();
        cardPos = (int)((1100.0/(size + 1)) - (CARD_WIDTH / 2.0));
        //         BufferedImage cardBack = new BufferedImage(null, null, false, null);
        //         try
        //         {
        //             cardBack = ImageIO.read(new File("cardback.jpg"));
        //         }
        //         catch(Exception e)
        //         {
        // 
        //         }

        for(int i = 1; i <= size; i++)
        {
            //bufferGraphics.drawImage(cardBack, i * cardPos, 0, this);  
            g.setColor(Color.RED);
            g.fillRect(i * cardPos, 0, CARD_WIDTH, CARD_HEIGHT);
        }

    }

//     public void update(Graphics g)
//     {
//         paint(g);
//     } 

    public void mouseMoved(MouseEvent evt)
    {

        x = evt.getX();
        y = evt.getY();

        repaint();
    }

    public void mouseDragged(MouseEvent evt)
    {

    }
}
