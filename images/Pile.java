import java.util.Stack;
import java.util.*;
public class Pile extends Stack<Card>
{
    public void fillPile()
    {

        KeeperCard chocolate = new KeeperCard("Chocolate", "Chocolate.jpg", 652, 1024);
        push(chocolate);
        KeeperCard cookies = new KeeperCard("Cookies", "Cookies.jpg", 648, 1024);
        push(cookies);
        KeeperCard dreams = new KeeperCard("Dreams", "Dreams.jpg", 648, 1024);
        push(dreams);
        KeeperCard love = new KeeperCard("Love", "Love.jpg", 648, 1028);
        push(love);
        KeeperCard milk = new KeeperCard("Milk", "Milk.jpg", 648, 1024);
        push(milk);
        KeeperCard money = new KeeperCard("Money", "Money.jpg", 648, 1024);
        push(money);
        KeeperCard peace = new KeeperCard("Peace", "Peace.jpg", 648, 1020);
        push(peace);
        KeeperCard sleep = new KeeperCard("Sleep", "Sleep.jpg", 663, 1029);
        push(sleep);
        KeeperCard television = new KeeperCard("Television", "Television.jpg", 648, 1024);
        push(television);
        KeeperCard theBrain = new KeeperCard("The Brain", "The Brain.jpg", 648, 1024);
        push(theBrain);
        KeeperCard theCosmos = new KeeperCard("The Cosmos", "The Cosmos.jpg", 665, 1029);
        push(theCosmos);
        KeeperCard theEye = new KeeperCard("The Eye", "The Eye.png", 673, 1031);
        push(theEye);
        KeeperCard theMoon = new KeeperCard("The Moon", "The Moon.jpg", 671, 1027);
        push(theMoon);
        KeeperCard theParty = new KeeperCard("The Party", "The Party.jpg", 689, 1029);
        push(theParty);
        KeeperCard theRocket = new KeeperCard("The Rocket", "The Rocket.jpg", 656, 1020);
        push(theRocket);
        KeeperCard theSun = new KeeperCard("The Sun", "The Sun.jpg", 644, 1020);
        push(theSun);
        KeeperCard theToaster = new KeeperCard("The Toaster", "The Toaster.jpg", 648, 1024);
        push(theToaster);
        KeeperCard time = new KeeperCard("Time", "Time.jpg", 648, 1020);
        push(time);
        KeeperCard bread = new KeeperCard("Bread", "Bread.jpg", 648, 1020);
        push(bread);

        CreeperCard death = new CreeperCard("Death","Death.jpg",648,1024);
        CreeperCard war = new CreeperCard("War","War.jpg",644,1020);
        CreeperCard tax = new CreeperCard("Taxes","Taxes.jpg",652,1020);
        CreeperCard potato = new CreeperCard("Radioactive Potato","Radioactive Potato.jpg",636,1020);
        push(death);
        push(war);
        push(tax);
        push(potato);

        push(new GoalCard("5 Keepers",null,null, "5 Keepers.jpg", 648, 1012));
        push(new GoalCard("10 Cards in Hand",null,null, "10 Cards in Hand.jpg", 648, 1020));
        push(new GoalCard("All That is Certain",null,null, "All That is Certain.jpg", 644, 1016));
        push(new GoalCard("All You Need is Love", love, null,"All You Need is Love.jpg", 644, 1024));
        push(new GoalCard("Baked Goods",cookies,bread, "Baked Goods.jpg", 648, 1012));
        push(new GoalCard("Bed Time", sleep,time,"Bed Time.jpg", 644, 1020));
        push(new GoalCard("Chocolate Cookies",chocolate,cookies, "Chocolate Cookies.jpg", 644, 1024));
        push(new GoalCard("Chocolate Milk",chocolate,milk, "Chocolate Milk.jpg", 648, 1020));
        push(new GoalCard("Death By Chocolate",death,chocolate, "Death By Chocolate.jpg", 648, 1020));
        push(new GoalCard("Dough",money,bread, "Dough.jpg", 652, 1024));
        push(new GoalCard("DreamLand",dreams,sleep, "DreamLand.jpg", 652, 1016));
        push(new GoalCard("Hearts and Minds",theBrain,love, "Hearts and Minds.jpg", 648, 1024));
        push(new GoalCard("Hippyism",peace,love, "Hippyism.jpg", 652, 1020));
        push(new GoalCard("InterStellar Spacecraft",theRocket,theCosmos, "Interstellar Spacecraft.jpg", 648, 1020));
        push(new GoalCard("Milk and Cookies",milk, cookies, "Milk and Cookies.jpg", 644, 1024));
        push(new GoalCard("Night and day",theSun,theMoon, "Night and Day.jpg", 644, 1020));
        push(new GoalCard("Party Snacks",theParty,cookies, "Party Snacks.jpg", 648, 1020));
        push(new GoalCard("Peace no War",peace,null, "Peace no War.jpg", 648, 1020));
        push(new GoalCard("Rocket Science",theRocket,theBrain, "Rocket Science.jpg", 648, 1024));
        push(new GoalCard("Rocket to the Moon",theRocket,theMoon, "Rocket to the Moon.jpg", 648, 1024));
        push(new GoalCard("Squishy Chocolate",theSun,chocolate, "Squishy Chocolate.jpg", 648, 1020));
        push(new GoalCard("Star Gazing",theEye,theCosmos, "Star Gazing.jpg", 648, 1024));
        push(new GoalCard("The Appliances",television,theToaster, "The Appliances.jpg", 648, 1020));
        push(new GoalCard("The Brain(No TV)",theBrain, null, "The Brain(No TV).jpg", 644, 1024));
        push(new GoalCard("The Mind's Eye",theBrain,theEye, "The Mind's Eye.jpg", 648, 1024));
        push(new GoalCard("Time is Money",time,money, "Time is Money.jpg", 648, 1020));
        push(new GoalCard("Toast",bread,theToaster, "Toast.jpg", 652, 1024));
        push(new GoalCard("War = Death",war,death, "War is Death.jpg", 648, 1024));
        push(new GoalCard("Winning the Lottery",money,dreams, "Winning the Lottery.jpg", 648, 1020));

        push(new NewRuleCard("Double Agenda", "Double Agenda.jpg", 648, 1032));
        push(new NewRuleCard("Draw", 2, "Draw 2.jpg", 652, 1020));
        push(new NewRuleCard("Draw", 3, "Draw 3.jpg", 648, 1020));
        push(new NewRuleCard("Draw", 4, "Draw 4.jpg", 648, 1020));
        push(new NewRuleCard("Draw", 5, "Draw 5.jpg", 644, 1024));
        push(new NewRuleCard("First Play Random", "First Play Random.jpg", 644, 1020));
        push(new NewRuleCard("Get On With It!", "Get On With It!.jpg", 648, 1020));
        push(new NewRuleCard("Hand Limit", 0, "Hand Limit 0.jpg", 656, 1028));
        push(new NewRuleCard("Hand Limit", 1, "Hand Limit 1.jpg", 648, 1020));
        push(new NewRuleCard("Hand Limit", 2, "Hand Limit 2.jpg", 656, 1024));

        push(new NewRuleCard("Keeper Limit", 2, "Keeper Limit 2.jpg", 652, 1080));
        push(new NewRuleCard("Keeper Limit", 3, "Keeper Limit 3.jpg", 648, 1032));
        push(new NewRuleCard("Keeper Limit", 4, "Keeper Limit 4.jpg", 644, 1024));
        push(new NewRuleCard("No Hand Bonus", "No Hand Bonus.jpg", 648, 1020));
        push(new NewRuleCard("Party Bonus", "Party Bonus.jpg", 648, 1020));
        push(new NewRuleCard("Play",2, "Play 2.jpg", 648, 1020));
        push(new NewRuleCard("Play",3, "Play 3.jpg", 648, 1020));
        push(new NewRuleCard("Play",4, "Play 4.jpg", 648, 1020));
        push(new NewRuleCard("Play",100, "Play All.jpg", 648, 1020));

        push(new ActionCard("Creeper Sweeper","Creeper Sweeper.png",16,663,1033));
        push(new ActionCard("Discard and Draw","Discard and Draw.jpg",1,648,1028));
        push(new ActionCard("Draw 2 and Use'em","Draw 2 and Use'em.jpg",2,648,1020));
        push(new ActionCard("Draw 3, Play 2 of Them","Draw 3, Play 2 of Them.jpg",3,648 ,1020 ));

        push(new ActionCard("Exchange Keepers","Exchange Keepers.jpg",5,644,1024));
        push(new ActionCard("Its Trash Day","Its Trash Day.jpg",21,699,1045));
        push(new ActionCard("Jackpot!","Jackpot!.jpg",0,648,1024));
        
        push(new ActionCard("Let's Simplify","Let's Simplify.jpg",7,644,1028));
        push(new ActionCard("Mix It All Up","Mix It All Up.jpg",19,648,1020));
        push(new ActionCard("No Limits","No Limits.jpg",8,744,1020));
        push(new ActionCard("Rotate Hands","Rotate Hands.jpg",9,644,1028));
        push(new ActionCard("Rules Reset","Rules Reset.jpg",10,648,1020));
        push(new ActionCard("Steal Something","Steal Something.jpg",18,644,1016));
        push(new ActionCard("Take Another Turn","Take Another Turn.jpg",11,648,1020));
        push(new ActionCard("Taxation!","Taxation!.jpg",12,648,1020));
        push(new ActionCard("Today's Special!","Today's Special!.jpg",20,648,1020));
        push(new ActionCard("Trade Hands","Trade Hands.jpg",13,648,1024));
        push(new ActionCard("Trash a New Rule","Trash a New Rule.jpg",14,644,1020));
        push(new ActionCard("Trash Something","Trash Something.jpg",17,648,1020));

        //us what yo utake (15)
        push(new ActionCard("Everybody Gets 1","Everybody Gets 1.jpg",4,648,1032));

        push(new NewRuleCard("Inflation", "Inflation.jpg", 669, 1027));
        push(new ActionCard("Let's Do That Again!","Let's Do That Again!.jpg",6,648,1020));
        //Collections.shuffle(this);
    }
}
