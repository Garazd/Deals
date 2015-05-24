import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Deal
{
    private final Date date = new Date();
    private final Party seller;
    private final Party buyer;
    private final Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Deal (Party buyer, Party seller)
    {
        this.seller = seller;
        this.buyer = buyer;
    }

    public double getSum()
    {
        double rez = 0;
        for (Entry<Product, Integer> entry : products.entrySet())
        {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            rez += product.getCost(quantity);
        }
        return rez;
    }
    public Date getDate()
    {
        return date;
    }
    public Party getSeller()
    {
        return seller;
    }
    public Party getBuyer()
    {
        return buyer;
    }
    public Map<Product, Integer> getProducts()
    {
        return products;
    }
}