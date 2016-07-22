import java.awt.Image;
public class EeperCard extends Card
{
    private int ability;
    public EeperCard(String n,String im,int w, int h)
    {
        super(n,im,w,h);
        ability=0;
    }
//     public EeperCard(String n)
//     {
//         super(n);
//         ability=0;
//     }
    public EeperCard(String n,String im, int i,int h, int w)
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
