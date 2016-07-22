import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.Component;
import java.awt.TextComponent;
import javax.swing.JOptionPane;
import java.awt.TextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Player
{
    private ArrayList<Card> hand;
    private ArrayList<EeperCard> eeperSet;
    private Game game;
    private int plays, draws;
    private String name;

    public Player()
    {
        hand = new ArrayList<Card>();
        eeperSet=new ArrayList<EeperCard>();
        game = null;
        name = "";
    }

    public Player(Game g, String n)
    {
        hand = new ArrayList<Card>();
        eeperSet=new ArrayList<EeperCard>();
        name = n;
        game= g;
        drawACard();
        drawACard();
        drawACard();

        plays = 1;
    }

    public Player(ArrayList<Card> h, String n)
    {
        hand=h;
        name = n;
        eeperSet=new ArrayList<EeperCard>();
        game=null;
    }

    public Player(ArrayList<Card> h,Game g, String n)
    {
        hand=h;
        name = n;
        eeperSet=new ArrayList<EeperCard>();
        game=g;
    }

    public void setName(String n)
    {
        name = n;
    }

    public String getName()
    {
        return name;
    }

    public int getDraws()
    {
        return draws;
    }

    public void setDraws(int d)
    {
        draws = d;
    }

    public void setPlays(int p)
    {
        plays = p;
    }

    public int getPlays()
    {
        return plays;
    }

    public void startTurn()
    {
        boolean isThere = false,isThere3 = false;;
        for(int i = 0; i<game.getRules().size();i++)
        {
            if(game.getRules().get(i).getName().equals("Inflation"))
                isThere = true;
            if(game.getRules().get(i).getName().equals("Party Bonus"))
                isThere3 = true;
        }
        boolean isThere2=false;
        for(Card c : eeperSet)
        {
            if(c.getName().equals("The Party"))
            {
                isThere2=true;
            }
        }
        for(Card c : game.getPlayer2().getEeperSet())
        {
            if(c.getName().equals("The Party"))
            {
                isThere2=true;
            }
        }

        if(isThere3&&isThere2)
        {
            draws = game.getDrawCount()+1;
            plays = game.getDrawCount()+1;
        }

        if(isThere)
        {
            draws = game.getDrawCount()+1;
            plays = game.getPlayCount()+1;
        }
        else
        {
            draws = game.getDrawCount();
            plays = game.getPlayCount();
        }
        draw();
        //game.setDrawCount(0);
    }

    public void draw()
    {
        while(draws > 0)
        {
            drawACard();
            draws--;
        }
    }

    public void drawACard()
    {
        if(game.getDeck().peek()==null)
        {
            game.switchDecks();
            JOptionPane.showMessageDialog(null,null,"dumbo jeremememy is dunzo",JOptionPane.ERROR_MESSAGE);
        }

        Card card=game.getDeck().pop();
        if(card instanceof CreeperCard)
        {
            play((CreeperCard)card);
            drawACard();
        }
        else
            hand.add(card);
    }

    public Card drawACard(int i)
    {
        Card card=game.getDeck().pop();
        if(card instanceof CreeperCard)
        {
            eeperSet.add((CreeperCard)card);
            return drawACard(1);
        }
        else
            return card;

    }

    public void play(Card card)
    {
        
        if(card instanceof KeeperCard)
        {   
            play((KeeperCard)card);
        }
        else if(card instanceof GoalCard)
        {
            play((GoalCard)card);
        }
        else if(card instanceof ActionCard)
        {
            
            play((ActionCard)card);
        }
        else if(card instanceof NewRuleCard)
        {
            play((NewRuleCard)card);
        }
    }

    public void play(int cardNum)
    {
        if(plays!=0)
        {
            Card card = hand.get(cardNum);
            if(card instanceof KeeperCard)
            {   
                play((KeeperCard)card);
            }
            else if(card instanceof GoalCard)
            {
                play((GoalCard)card);
            }
            else if(card instanceof ActionCard)
            {
                play((ActionCard)card);
            }
            else if(card instanceof NewRuleCard)
            {
                play((NewRuleCard)card);
            }
            plays--;
        }
        else
        {
            game.nextTurn();
        }
        if(hand.size() == 0)
        {
            game.nextTurn();
        }
    }

    public void play(CreeperCard creeper)
    {
        eeperSet.add(creeper);
        hand.remove(creeper);
    }

    public void play(KeeperCard keeper)
    {
        eeperSet.add(keeper);
        hand.remove(keeper);
    }

    public void play(GoalCard gool)
    {
        NewRuleCard DoubleAgenda=new NewRuleCard("Double Agenda",-1,"Double Agenda.jpg",0,0);
        int i = game.getRules().size();
        boolean isThere=false;
        for(Card c : game.getRules())
        {
            if(c.getName().equals("Double Agenda"))
            {
                isThere=true;
            }
        }
        if(!isThere)
        {

            if(game.getGoal1() != null)
            {
                game.getDiscardPile().push(game.getGoal1());
            }
            game.setGoal1(gool);
        }
        else
        {
            if(game.getGoal1() == null)
            {
                game.setGoal1(gool);
            }
            else
            {
                if(game.getGoal2()==null)
                    game.setGoal2(gool);
                else
                {
                    //TextComponent idk = new TextComponent(null);
                    String s = JOptionPane.showInputDialog("Enter the name of the goal you want to replace");
                    if(s.toLowerCase().equals(game.getGoal1().getName().toLowerCase()))
                    {
                        game.getDiscardPile().push(game.getGoal1());
                        game.setGoal1(gool);
                    }
                    else if(s.toLowerCase().equals(game.getGoal2().getName().toLowerCase()))
                    {
                        game.getDiscardPile().push(game.getGoal2());
                        game.setGoal2(gool);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,null,"dumbo, dont put in the name of a non existing goal, try again",JOptionPane.ERROR_MESSAGE);
                        play(gool);
                    }
                }
                //either goal1 or goal2 gets replaced, check which player played card and prompt for which to replace
            }
        }
        hand.remove(gool);

    }

    public void play(NewRuleCard newRule)
    {
        NewRuleCard temp=null;
        for(NewRuleCard c:game.getRules())
        {
            if(c.compareTo(newRule)==0)
            {
                //game.changeRuleCount(newRule);
                temp=c;
            }
        }
        if(temp!=null)
        {
            game.getRules().remove(temp);
            game.getDiscardPile().push(temp);
        }
        game.changeRuleCount(newRule);
        game.getRules().add(newRule);
        hand.remove(newRule);
    }

    private boolean isInflation()
    {
        for(int i = 0; i < game.getRules().size();i++)
        {
            if(game.getRules().get(i).getName().equals("Inflation"))
                return true;
        }
        return false;
    }
    
    public void play(ActionCard action)
    {
        int ab=action.getAbility(),j=0;
        hand.remove(action);
        game.getDiscardPile().push(action);
        if(isInflation())
        {
            j = 1;
        }

        switch (ab)
        {
            //JACKPOT
            case 0:
            drawCards(3+j);
            break;
            //DISCARD AND DRAW
            case 1:int i=hand.size();
            for(int jj=0;jj<i;jj++)
                game.getDiscardPile().push(hand.remove(jj));
            drawCards(i);

            drawCards(i);
            break;
            //DRAW 2 AND USE'EM

            case 2:
            for(int jjj=0; jjj<2+j;jjj++)
            {
                play(game.getDeck().pop());
            }
            break;
            //DRAW 3 AND USE 2 of thEM
            case 3:
            ArrayList<Card> temp3 = new ArrayList<Card>();
            temp3 = otherDrawCards(temp3 , 3+j);
            break;
            //EVERYBODY GETS 1
            case 4:
            if(j==0)
            {
                Card temp4 = drawACard(1);
                Card temp42 = drawACard(1);
                hand.add(temp4);
                hand.add(temp42);
                String s4 = temp4.getName();
                String s42 = temp42.getName();
                String s43 = JOptionPane.showInputDialog("Which CARD do you want to give?" + s4 + " , " + s42);
                if(isMaine(s43)!=-1)
                {
                    game.getPlayer2().getHand().add(hand.remove(isMaine(s43)));
                }
            }
            else
            {
                Card temp4 = drawACard(1);
                Card temp42 = drawACard(1);
                Card temp43 = drawACard(1);
                Card temp44 = drawACard(1);
                hand.add(temp4);
                hand.add(temp42);
                hand.add(temp43);
                hand.add(temp44);
                String s4 = temp4.getName();
                String s42 = temp42.getName();
                String s43 = temp43.getName();
                String s44 = temp44.getName();
                String s45 = JOptionPane.showInputDialog("Which cards(notice how it is plural? please add a comma between card names) do you want to give?" + s4 + " , " + s42 + " , " + s43 + " , " + s44);
                String card1 = s45.substring(0,s45.indexOf(",")).trim();
                String card2 = s45.substring(s45.indexOf(",")+1).trim();
                if(isMaine(card1)!=-1&&isMaine(card2)!=-1)
                {
                    Card c4 = hand.get(isMaine(card1));
                    Card c42 = hand.get(isMaine(card2));
                    game.getPlayer2().getHand().add(c4);
                    game.getPlayer2().getHand().add(c42);
                    if(isMaine(card1)>isMaine(card2))
                    {
                        hand.remove(isMaine(card1));
                        hand.remove(isMaine(card2));
                    }
                    else
                    {
                        hand.remove(isMaine(card2));
                        hand.remove(isMaine(card1));
                    }
                }
            }

            break;
            //EXCHANGE KEEPERS  
            case 5:
            Player other5=getOtherPlayer();
            if(eeperSet.isEmpty() || other5.getEeperSet().isEmpty())
            {
                JOptionPane.showMessageDialog(null,null,"wont work since someone has no keepers",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String s5 = JOptionPane.showInputDialog("Which keeper do you want to give?");
                String s52 = JOptionPane.showInputDialog("Which keeper do you want to take?");
                if(isThere(s5)!=-1 && isOtherThere(s52)!=-1)
                {
                    int i5=isThere(s5);
                    int i52=isOtherThere(s52);
                    KeeperCard c5 = (KeeperCard)game.getPlayer2().getEeperSet().get(i52);
                    KeeperCard c52 = (KeeperCard)eeperSet.get(i5);
                    eeperSet.add(c5);
                    game.getPlayer2().getEeperSet().add(c52);
                    game.getPlayer2().getEeperSet().remove(i52);
                    eeperSet.remove(i5);
                }
            }

            break;
            //LETS DO THAT AGAIN
            case 6:
            int length;
            ButtonGroup gr = new ButtonGroup();
            JPanel radioPanel = new JPanel(new GridLayout(0, 1));
            int ijk = game.getDiscardPile().size(),counts=0;
            ArrayList<JRadioButton> lst = new ArrayList<JRadioButton>();
            for(length=0;length<game.getDiscardPile().size();length++)
            {
                Card c = game.getDiscardPile().get(length);
                if(c instanceof ActionCard || c instanceof NewRuleCard)
                {
                    JRadioButton but = new JRadioButton(c.getName(),false);
                    gr.add(but);
                    radioPanel.add(but);
                    counts++;
                    lst.add(but);
                }
            }
            //JRadioButtonMenuItem grr = new JRadioButtonMenuItem(gr,false);
            int x = JOptionPane.showOptionDialog(null,radioPanel,"Somethings Happening",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            JOptionPane.showMessageDialog(null,x,"?,", JOptionPane.ERROR_MESSAGE);
            for(int counter=0;counter<counts;counter++)
            {
                if(lst.get(counter).isSelected())
                {
                    play(game.getDiscardPile().get(counter));
                }
            }
            
            break;
            //LETS SIMPLIFY
            case 7:
            if(game.getRules().size()==0)
            {
                JOptionPane.showMessageDialog(null,null,"There are no rules to eliminate", JOptionPane.ERROR_MESSAGE);
                break;
            }
            if(game.getRules().size()%2==0)
                int choices = game.getRules().getSize()/2;
            else
                int choices = game.getRules().size()/2 + 1;
            String[] rules = new String[game.getRules().size()];
            for(int count7 = 0;count7<game.getRules().size();count7++)
            {
                rules[count7] = game.getRules().get(count7).getName();
            }
            
            //clicky thing
            break;
            //No Limits
            case 8:
            for(NewRuleCard c:game.getRules())
            {
                if(c.isKeeperLimit() || c.isHandLimit())
                {
                    game.getRules().remove(c);
                    game.getDiscardPile().push(c);
                    game.resetLimits();
                }
            }
            break;
            //ROTATE HANDS
            case 9:Player other=getOtherPlayer();
            ArrayList<Card> temp9=hand;
            setHand(other.getHand());
            other.setHand(temp9);
            break;
            //RULES RESET
            case 10:
            boolean isDA=false;
            for(NewRuleCard c : game.getRules())
            {
                if(c.getName().equals("Double Agenda"))
                {
                    isDA = true;
                }

            }

            if(isDA)
            {
                String s = JOptionPane.showInputDialog("Enter the name of the goal you want to remove");
                if(s.toLowerCase().equals(game.getGoal1().getName().toLowerCase()))
                {
                    game.getDiscardPile().push(game.getGoal1());
                    game.setGoal1(null);
                }
                else if(s.toLowerCase().equals(game.getGoal2().getName().toLowerCase()))
                {
                    game.getDiscardPile().push(game.getGoal2());
                    game.setGoal2(null);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,null,"dumbo, dont put in the name of a non existing goal, try again",JOptionPane.ERROR_MESSAGE);
                    play(action);
                }
            }
            game.setRules(new ArrayList<NewRuleCard>());
            game.resetRules();
            break;
            //TAKE ANOTHER TURN
            case 11:
            game.setPlayCount(1);
            break;
            //TAXATION
            case 12:
            //clicky thing

            break;
            //TRADE HANDS
            case 13:Player other13=getOtherPlayer();
            ArrayList<Card> temp13=hand;
            setHand(other13.getHand());
            other13.setHand(temp13);
            break;
            //TRASH A NEW RULE
            case 14:
            String s14;
            if(!game.getRules().isEmpty())
                s14 = JOptionPane.showInputDialog("What rule do you want to get rid of?");
            else if(true)
                break;//Jeremy said this
            int size = game.getRules().size();
            for(int z = 0;z<size;z++)
            {
                if(game.getRules().get(z).getName().equals(s14))
                {
                    game.getRules().get(z);
                }
            }
            break;
            //USE WHAT YOU TAKE
            case 15:
            //clicky thing
            break;
            //CREEPER SWEEPER
            case 16:
            /*

            for(EeperCard k: eeperSet)
            {
            if(k instanceof CreeperCard)
            {
            getEeperSet().remove(k);
            game.getDiscardPile().push(k);
            }
            }
            for(EeperCard k:getOtherPlayer().getEeperSet())
            {
            if(k instanceof CreeperCard)
            {
            getOtherPlayer().getEeperSet().remove(k);
            game.getDiscardPile().push(k);
            }
            }

             */
            for(int q=0;q<eeperSet.size();q++)
            {
                if(eeperSet.get(q) instanceof CreeperCard)
                {
                    Card car = eeperSet.remove(q);
                    game.getDiscardPile().push(car);
                    q--;
                }
            }
            for(int w=0;w<game.getPlayer2().getEeperSet().size();w++)
            {
                if(eeperSet.get(w) instanceof CreeperCard)
                {
                    Card ca = game.getPlayer2().getEeperSet().remove(w);
                    game.getDiscardPile().push(ca);
                    w--;
                }
            }
            break;
            //TRASH SOMETHING
            case 17:
            //clicky thing
            break;
            //STEAL SOMETHING
            case 18:
            //clickyThing
            break;
            //MIX IT ALL UP
            case 19:ArrayList<EeperCard> all=new ArrayList<EeperCard>();
            all.addAll(eeperSet);
            all.addAll(getOtherPlayer().getEeperSet());
            eeperSet=new ArrayList<EeperCard>();
            getOtherPlayer().setEeperSet(new ArrayList<EeperCard>());
            int m=0;
            while(!all.isEmpty())
            {
                if(m%2==0)
                    eeperSet.add(all.remove(0));
                else
                    getOtherPlayer().getEeperSet().add(all.remove(0));
                m++;
            }

            break;
            //TODAYS SPECIAL
            case 20:
            int p;
            ArrayList<Card> temp20 = new ArrayList<Card>();
            temp20 = otherDrawCards(temp20 , 3+j);
            String s1 = JOptionPane.showInputDialog("Is it your birthday?");
            if(s1.equalsIgnoreCase("yes"))
            {
                p=3;
            }
            else
            {
                String s2 = JOptionPane.showInputDialog("Is it a special day?");
                if(s2.equalsIgnoreCase("yes"))
                {
                    p=2;
                }
                else
                {
                    p=1;
                }
            }
            //clicky thing
            break;
            //ITS TRASH DAY
            case 21:
            //clicky thing
            break;

        }
        JOptionPane.showMessageDialog(null,null,"hereeeeeeerrrrrrrrrrrrrrrrrr " + action.getName(),JOptionPane.ERROR_MESSAGE);
    }

    public int isThere(String s)
    {
        for(int i=0;i<eeperSet.size();i++)
        {
            if(eeperSet.get(i).getName().compareToIgnoreCase(s)==0)
            {
                return i;
            }
        }
        return -1;
    }

    public int isMaine(String ass)
    {
        for(int i=0;i<hand.size();i++)
        {
            if(hand.get(i).getName().compareToIgnoreCase(ass)==0)
            {
                return i;
            }
        }
        return -1;
    }

    public int isOtherThere(String s)
    {
        for(int i=0;i<game.getPlayer2().getEeperSet().size();i++)
        {
            if(game.getPlayer2().getEeperSet().get(i).getName().compareToIgnoreCase(s)==0)
            {
                return i;
            }
        }
        return -1;
    }

    public void checkGame()
    {

    }

    public String toString()
    {
        return "Hand: "+hand+"\n"+"Eepers: "+eeperSet;
    }

    public Game getGame()
    {
        return game;
    }

    public void drawCards(int numCards)
    {
        for(int i = 0; i < numCards; i++)
        {
            drawACard();
        }
    }

    public ArrayList<Card> otherDrawCards(ArrayList<Card> hand, int numCards)
    {
        for(int i = 0; i < numCards; i++)
        {
            hand.add(drawACard(1));
        }
        return hand;
    }

    public void setHand(ArrayList<Card> h)
    {
        hand=h;
    }

    public Player getOtherPlayer()
    {
        if(this.equals(game.getPlayer1()))
            return game.getPlayer2();
        return game.getPlayer1();
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public ArrayList<EeperCard> getEeperSet()
    {
        return eeperSet;
    }

    public void setEeperSet(ArrayList<EeperCard> set)
    {
        eeperSet=set;
    }
    /*
    public int getTotalDraws()
    {
    j=0;
    if(game.getRules().contains("Inflation"))
    {
    j = 1;
    }
    int total=draw+j;
    if(game.getRules().contains("No Hand Bonus") && hand.isEmpty())
    count+=3+j;
    if(game.getRules().contains("Poor Bonus") && eeperSet.size()<getOtherPlayer().getEeperSet().size())
    count+=1+j;
    if(game.getRules().contains("Party Bonus") && (eeperSet.contains("Party") || getOtherPlayer().getEeperSet().contains("Party")))
    count+=1+j;
    return count;
    }

    public void takeTurn()
    {
    drawCards(getTotalDraws());
    while(!hand.isEmpty())
    {
    if(plays==0!)
    endTurn();
    playCard(clicky thing);
    }
    }

     */
}

