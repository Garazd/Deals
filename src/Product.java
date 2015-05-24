public abstract class Product
{
    public static final int Def_Discaunt = 10;

    private String title;
    private double prise;

    public String getTitle()
    {
        return title;
    }
    public double getPrise()
    {
        return prise;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setPrise(double prise)
    {
        this.prise = prise;
    }

    public double getCost(int quantity)
    {
        double realCost = quantity * prise;
        return realCost - realCost * calcDiscount(quantity) / 100;
    }

    protected int calcDiscount(int quantity)
    {
        if (quantity > Def_Discaunt)
        {
            return 10;
        }
        else
        {
            return 0;
        }
    }
}