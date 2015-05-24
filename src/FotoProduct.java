public class FotoProduct extends Product
{
    private static final int Discount_For_Non_Digital = 20;

    private double megapx;
    private boolean digital;

    public double getMegapx()
    {
        return megapx;
    }

    public void setMegapx (double megapx)
    {
        this.megapx = megapx;
    }
    public boolean isDigital()
    {
        return digital;
    }

    public void setDigital (boolean digital)
    {
        this.digital = digital;
    }

    @Override
    protected int calcDiscount(int quantity)
    {
        int def = super.calcDiscount(quantity);
        if (!digital)
        {
            def += Discount_For_Non_Digital;
        }
        return def;
    }
}