import java.awt.Image;
public class GoalCard extends Card
{
    private EeperCard eeper1,eeper2;
    public GoalCard(String s,EeperCard e1,EeperCard e2,String w1, int w, int h)
    {
        super(s,w1,w,h);
        eeper1=e1;
        eeper2=e2;
    }
//     public GoalCard(EeperCard e1,EeperCard e2,String w)
//     {
//         super(w);
//         eeper1=e1;
//         eeper2=e2;
//     }
    public String toString()
    {
        return eeper1+" and "+eeper2;
    }
    public EeperCard getEeper1()
    {
        return eeper1;
    }
    public EeperCard getEeper2()
    {
        return eeper2;
    }
}
