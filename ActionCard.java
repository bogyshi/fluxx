import java.awt.Image;
public class ActionCard extends Card
{
    private int ability;
    public ActionCard(String n,String im,int w, int h)
    {
        super(n,im,w,h);
        ability=0;
    }
//     public ActionCard(String n)
//     {
//         super(n);
//         ability=0;
//     }
    public ActionCard(String n,String im, int i, int w, int h)
    {
        super(n,im,w,h);
        ability=i;
    }
    public String toString()
    {
        return getName();
    }
    public int getAbility()
    {
        return ability;
    }
}
