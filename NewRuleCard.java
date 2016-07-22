import java.awt.Image;
public class NewRuleCard extends Card implements Comparable<NewRuleCard>
{
    private String type;
    private int integer;
    public NewRuleCard(String t,int i,String im,int w,int h)
    {
        super(t+ " " + i,im,w,h);
        type=t;
        integer=i;
    }
    public NewRuleCard(String t, String im,int w, int h)
    {
        super(t,im,w,h);
        type=t;
        integer = -1;
    }
    public int compareTo(NewRuleCard newRule)
    {
        return type.compareTo(newRule.type);
    }
    public String getType()
    {
        return type;
    }
    public int getInt()
    {
        return integer;
    }
    public String toSrting()
    {
        return type+" ("+integer+")";
    }
    public boolean isHandLimit()
    {
        return type.equals("Hand Limit");
    }
    public boolean isKeeperLimit()
    {
        return type.equals("keeper Limit");
    }
}
