import java.util.ArrayList;
import java.util.*;
import java.awt.Image;
import java.awt.TextComponent;
import javax.swing.JOptionPane;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game
{
    private Pile deck;
    private Pile discardDeck;
    private Player player1, player2;
    private ArrayList<NewRuleCard> rules;
    private GoalCard goal1,goal2;
    private int draw=1,play=1,handLimit=-1,keeperLimit=-1;
    
    public Game()
    {
        deck = new Pile();
        deck.fillPile();
        discardDeck = new Pile();
        player1 = new Player();
        player2 = new Player();
        rules = new ArrayList<NewRuleCard>();
    }
    
    public Game(Player one, Player two)
    {
        deck = new Pile();
        deck.fillPile();
        discardDeck = new Pile();
        player1 = one;
        player2 = two;
        rules = new ArrayList<NewRuleCard>();
    }
    
    public ArrayList<Player> getPlayers()
    {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        return players;
    }
    
    public int getPlays()
    {
        return play;
    }
    
    public int getDraws()
    {
        return draw;
    }
    
    public int getKeeperLimit()
    {
        return keeperLimit;
    }
    
    public int getHandLimit()
    {
        return handLimit;
    }

    public void changeRuleCount(NewRuleCard newRule)
    {
        String t=newRule.getType();
        int i=newRule.getInt();
        if(t.equals("Draw"))
        {
            player1.setDraws(i - draw);
            player1.draw();
            draw=i;          
        }
        if(t.equals("Play"))
        {
            setPlayCount(i);
        }
        if(t.equals("Hand Limit"))
        {
            handLimit=i;
        }
        if(t.equals("Keeper Limit"))
        {
            keeperLimit=i;
        }
        boolean isThere=false;
        for(Card c : player1.getEeperSet())
        {
            if(c.getName().equals("The Party"))
            {
                isThere=true;
            }
        }
        for(Card c : player2.getEeperSet())
        {
            if(c.getName().equals("The Party"))
            {
                isThere=true;
            }
        }
        
        if(t.equals("Party Bonus")&&isThere)
        {
            draw++;
            play++;
            player1.setDraws(player1.getDraws()+1);
            player1.setPlays(player1.getPlays()+1);
        }
    }

    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
    
    
    public void nextTurn()
    {
        JOptionPane.showMessageDialog(null,null,player2.getName() + "'s Turn!", JOptionPane.ERROR_MESSAGE);
        Player temp = player1;
        player1 = player2;
        player2 = temp;
        player1.startTurn();
    }
    
    public Pile getDeck()
    {
        return deck;
    }
    
    public void switchDecks()
    {
        int size = discardDeck.size();
        for(int i = 0; i < size;i++)
        {
            Card a = discardDeck.pop();
            deck.push(a);
        }
        
    }
    
    public ArrayList<NewRuleCard> getRules()
    {
        return rules;
    }
    
    public void setRules(ArrayList<NewRuleCard> rule)
    {
        rules=rule;
    }
    
    public GoalCard getGoal1()
    {
        return goal1;
    }
    
    public GoalCard getGoal2()
    {
        return goal2;
    }
    
    public void setDrawCount(int d)
    {
        draw = d;
    }
    
    public int getDrawCount()
    {
        return draw;
    }
    
    public void setGoal1(GoalCard newGoal)
    {
        goal1=newGoal;
    }
    
    public void setGoal2(GoalCard newGoal)
    {
        goal2=newGoal;
    }
    
    public Pile getDiscardPile()
    {
        return discardDeck;
    }
    
    public int getPlayCount()
    {
        return play;
    }
    
    public void setPlayCount(int p)
    {
        if(player1.getPlays() >= p)
        {
            player1.setPlays(p);
        }
        if(play < p)
        {
            player1.setPlays(player1.getPlays() + (p - play));
        }

        play = p;
    }
    
    public Player getPlayer1()
    {
        return player1;
    }
    
    public Player getPlayer2()
    {
        return player2;
    }
    
    public void setPlayer1(Player p)
    {
        player1=p;
    }
    
    public void setPlayer2(Player p)
    {
        player2=p;
    }
    
    public void resetRules()
    {
        play=1;
        draw=1;
        handLimit=-1;
        keeperLimit=-1;
    }
    
    public void resetLimits()
    {
        handLimit=-1;
        keeperLimit=-1;
    }
    
}
