/**
 * Write a description of class Fluxx here.
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
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;

public class Fluxx extends Applet implements MouseInputListener
{
    //Look at the notes
    private Graphics bufferGraphics;
    private Image offscreen;
    private Dimension dim;
    private Game game;
    private int handSize, handSize2;
    private int curX, curY;
    private int clickX, clickY;
    private int eeperPos1, eeperPos2;
    private int CARD_WIDTH;
    private int CARD_HEIGHT;
    private int handPos;
    BufferedImage cardBack;
    private double scale = .8;

    public void init()
    {
        //game = new Game(new Player(game),new Player(game));
        game=new Game();
        game.setPlayer1(new Player(game, "Dylan"));
        game.setPlayer2(new Player(game, "Alex"));
        dim = getSize();
        cardBack = null;
        addMouseMotionListener(this);
        addMouseListener(this);
        offscreen = createImage(dim.width, dim.height);
        bufferGraphics = offscreen.getGraphics();
                try
        {
            cardBack = ImageIO.read(new File("Fluxx_back.jpg"));
        }
        catch(Exception e)
        {

        }
    }

    public void paint(Graphics g)
    {    
        CARD_WIDTH = (int)(dim.width * .101);
        CARD_HEIGHT = (int)(dim.height * .239);
        
        bufferGraphics.clearRect(0,0,dim.width, dim.width);
        if(game.getPlayer1().getPlays() == 0)
        {
            game.nextTurn();
        }
        //bufferGraphics.drawString(curX + "  " + curY, 1100, 800);

        bufferGraphics.drawString("Plays Remaining: " + game.getPlayer1().getPlays(), 1100, 740);
        bufferGraphics.drawString("Plays: " + game.getPlays(), 1100, 760);
        bufferGraphics.drawString("Draws: " + game.getDraws(), 1100, 780);
        bufferGraphics.drawString("Keeper Limit: " + game.getKeeperLimit(), 1100, 800);
        bufferGraphics.drawString("Hand Limit: " + game.getHandLimit(), 1100, 820);
        
        bufferGraphics.drawString(game.getPlayer1().getName(), 800, 810);
        bufferGraphics.drawString(game.getPlayer2().getName(), 800, 110);
        

        handSize2 = game.getPlayer2().getHand().size();
        handPos = (int)((dim.width * .761/(handSize2)));
        for(int i = 0; i < handSize2; i++)
        {
            //bufferGraphics.drawImage(cardBack, i * handPos, 0, this);  
            bufferGraphics.drawImage(cardBack, i * handPos, 0, (i * handPos) + CARD_WIDTH, CARD_HEIGHT, 0, 0, 654, 1030, this);
        }

        handSize = game.getPlayer1().getHand().size();
        handPos = (int)((700.0/(handSize)));


        int eeperSetSize1 = game.getPlayer1().getEeperSet().size();
        int eeperSetSize2 = game.getPlayer2().getEeperSet().size();
        eeperPos1 = (int)(700.0/eeperSetSize1);
        eeperPos2 = (int)(700.0/eeperSetSize2);
        //draws Player 1 Eepers
        for(int i = 0; i < eeperSetSize1; i++)
        {           
            bufferGraphics.drawImage(game.getPlayer1().getEeperSet().get(i).getImage(), i * eeperPos1, 470, (int)(CARD_WIDTH * scale + (i * eeperPos1)), (int)(CARD_HEIGHT * scale + 470), 0, 0, game.getPlayer1().getEeperSet().get(i).getWidth(), game.getPlayer1().getEeperSet().get(i).getHeight(), this);            
        }
        //draws Player 2 Eepers
        for(int i = 0; i < eeperSetSize2; i++)
        {
            bufferGraphics.drawImage(game.getPlayer2().getEeperSet().get(i).getImage(), i * eeperPos2, 230, (int)(CARD_WIDTH * scale + (i * eeperPos2)), (int)(CARD_HEIGHT * scale + 230), 0, 0, game.getPlayer2().getEeperSet().get(i).getWidth(), game.getPlayer2().getEeperSet().get(i).getHeight(), this);
        }

        if(game.getGoal1() != null && game.getGoal2() == null)
        {
            bufferGraphics.drawImage(game.getGoal1().getImage(), 1140, 330, CARD_WIDTH + 1140, 330 + CARD_HEIGHT, 0, 0, game.getGoal1().getWidth(), game.getGoal1().getHeight(), this);
        }
        else if(game.getGoal2() != null)
        {
            bufferGraphics.drawImage(game.getGoal1().getImage(), 1140, 250, CARD_WIDTH + 1140, 250 + CARD_HEIGHT, 0, 0, game.getGoal1().getWidth(), game.getGoal1().getHeight(), this);
            bufferGraphics.drawImage(game.getGoal2().getImage(), 1140, 500, CARD_WIDTH + 1140, 500 + CARD_HEIGHT, 0, 0, game.getGoal2().getWidth(), game.getGoal2().getHeight(), this);
        }

        int rulePos = (int)(700.0/game.getRules().size());
        
        for(int i = 0; i < game.getRules().size(); i++)
        {
            bufferGraphics.drawImage(game.getRules().get(i).getImage(),860, i * rulePos, 860 + CARD_WIDTH, i * rulePos + CARD_HEIGHT, 0, 0, game.getRules().get(i).getImage().getWidth(), game.getRules().get(i).getImage().getHeight(), this);
        }
        
        for(int i = 0; i < handSize; i++)
        {
            if(curY >= 700 && curX >= handPos * i && curX < handPos * i + CARD_WIDTH && curX < handPos * (i + 1))
            {
                //bufferGraphics.drawImage(cardBack, i * handPos, 450, (i * handPos) + CARD_WIDTH, 450 + CARD_HEIGHT, 0, 0, 654, 1030, this);
                bufferGraphics.drawImage(game.getPlayer1().getHand().get(i).getImage(), i * handPos, 450, (i * handPos) + CARD_WIDTH, 450 + CARD_HEIGHT, 0, 0, game.getPlayer1().getHand().get(i).getWidth(), game.getPlayer1().getHand().get(i).getHeight(), this);
                //                 bufferGraphics.setColor(Color.GREEN);
                //                 bufferGraphics.fillRect(i * handPos, 450, CARD_WIDTH, CARD_HEIGHT);
                //                 bufferGraphics.setColor(Color.BLACK);
                //                 bufferGraphics.drawRect(i * handPos, 450, CARD_WIDTH, CARD_HEIGHT);

            }
            else
            {
                //bufferGraphics.drawImage(game.getPlayer1().getHand().get(i).getImage(), i * handPos, 700, this);  
                //bufferGraphics.drawImage(cardBack, i * handPos, 700, (i * handPos) + CARD_WIDTH, 700 + CARD_HEIGHT, 0, 0, 654, 1030, this);
                bufferGraphics.drawImage(game.getPlayer1().getHand().get(i).getImage(), i * handPos, 700, CARD_WIDTH + (i * handPos), CARD_HEIGHT + 700, 0, 0, game.getPlayer1().getHand().get(i).getWidth(), game.getPlayer1().getHand().get(i).getHeight(), this);
                //                 bufferGraphics.setColor(Color.RED);
                //                 bufferGraphics.fillRect(i * handPos, 700, CARD_WIDTH, CARD_HEIGHT);
                //                 bufferGraphics.setColor(Color.BLACK);
                //                 bufferGraphics.drawRect(i * handPos, 700, CARD_WIDTH, CARD_HEIGHT);
            }
        }
        
        int discardSize = game.getDiscardPile().size();
        Stack<Card> temp = new Stack<Card>();
        
        for(int i = 0; i < discardSize; i++)
        {
            temp.push(game.getDiscardPile().pop());
        }
        
        int discardPos = (int)(40.0/discardSize);
        Card tempCard;
        
        for(int i = 0; i < discardSize; i++)
        {
            tempCard = temp.pop();
            bufferGraphics.drawImage(tempCard.getImage(), i * discardPos + 1100, 5, CARD_WIDTH + 1100 + i * discardPos, CARD_HEIGHT + 5, 0, 0, tempCard.getWidth(), tempCard.getHeight(), this);
            game.getDiscardPile().push(tempCard);
        }

        g.drawImage(offscreen, 0, 0, this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseMoved(MouseEvent evt)
    {
        curX = evt.getX();
        curY = evt.getY();

        repaint();
    }

    public void mouseDragged(MouseEvent evt)
    {

    }

    public void mouseClicked(MouseEvent evt)
    {
        clickX = evt.getX();
        clickY = evt.getY();
        int handIndex = isHandHighlighted(clickX, clickY);
        int eeperSetIndex = isEeperSetHighlighted(clickX, clickY);
        if(handIndex != -1 && game.getPlayCount() != 0)
        {
            game.getPlayer1().play(handIndex);
            if(game.getGoal1()!=null && game.getPlayer1().getEeperSet().contains(game.getGoal1().getEeper1()) && game.getPlayer1().getEeperSet().contains(game.getGoal1().getEeper2()))
                JOptionPane.showMessageDialog(null,null,"Jeremy Sucks",JOptionPane.ERROR_MESSAGE);
        }

        repaint();
    }

    public void mousePressed(MouseEvent evt)
    {

    }

    public void mouseReleased(MouseEvent evt)
    {

    }

    public void mouseEntered(MouseEvent evt)
    {

    }

    public void mouseExited(MouseEvent evt)
    {

    }

    private int isHandHighlighted(int x, int y)
    {
        for(int i = 0; i < handSize; i++)
        {
            if (y >= 700 && x >= handPos * i && x < handPos * i + CARD_WIDTH && x < handPos * (i + 1))
            {
                return i;
            }
        }

        return -1;
    }

    public int isEeperSetHighlighted(int x, int y)
    {
        for(int i = 0; i < handSize; i++)
        {
            if (y >= 400 && y <= 620 && x >= eeperPos1 * (i + 1) && x < eeperPos1 * (i + 1) + CARD_WIDTH && x < eeperPos1 * (i + 2))
            {
                return i;
            }
        }
        return  -1;
    }
}
