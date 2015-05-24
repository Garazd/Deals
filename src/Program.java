import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class Program
{
    private final List<Product> products = new LinkedList<Product>();

    private final Collection<Deal> deals = new LinkedList<Deal>();

    public static void main(String[]args)
    {
        new Program();
    }

    private Program()
    {
        input();
        System.out.println("===============================");
        output();
    }

    private Deal inputDeal()
    {
        System.out.println("Input deal ->");

        System.out.println("Seller ->");
        Party seller = inputParty();

        System.out.println("Bayer ->");
        Party bayer = inputParty();
        Deal deal = new Deal (bayer, seller);
        boolean continueInput = true;
        do
        {
            Product product = inputProduct();
            String quantity = keyboard("   Quontity");
            deal.getProducts().put(product, Integer.valueOf(quantity));
            continueInput = continueInputQuestion("Product");
        }
        while (continueInput);
        return deal;
    }

    private Party inputParty ()
    {
        String partyName = keyboard("   Party name");
        String address = keyboard("   Party address");

        Party party = new Party();
        party.setName(partyName);
        party.setAddress(address);

        boolean continueInput;
        do
        {
            String key = keyboard("  Key");
            String value = keyboard("  Value");

            party.getProperties().put(key, value);

            continueInput = continueInputQuestion("Property");
        }
        while (continueInput);
        return party;
    }

    private Product inputProduct ()
    {
        Product product = null;
        String reply = keyboard("  Select product (y - Yes, n - No)");
        if (reply.compareToIgnoreCase("y") == 0)
        {
            product = selectProduct();
        }
        if (product != null)
        {
            return product;
        }
        String productType = keyboard("   1 - Foto, 2 - Botinki");
        String title = keyboard("   Name");
        String price = keyboard("   Price");

        if (productType.equals("1"))
        {
            String megapx = keyboard("   Megapixel");
            String digital = keyboard("   True - Digital, False - Non digital");

            FotoProduct fotoProduct = new FotoProduct();
            fotoProduct.setDigital(Boolean.valueOf(digital));
            fotoProduct.setMegapx(Double.valueOf(megapx));
            product = fotoProduct;
        }
        else if (productType.equals("2"))
        {
            String size = keyboard("  Size");
            String color = keyboard("  Color");

            BotinkiProduct botinkiProduct = new BotinkiProduct();
            botinkiProduct.setSize(Integer.valueOf(size));
            botinkiProduct.setColor(color);
            product = botinkiProduct;
        }
        else
        {
            System.err.println("Unknown product");
            System.exit(-1);
        }
        product.setTitle(title);
        product.setPrise(Double.valueOf(price));
        this.products.add(product);

        return product;
    }

    private Product selectProduct()
    {
        if (products.isEmpty())
        {
            System.out.println("  No product to select. Input is required");
            return null;
        }
        for (int i = 0; i < products.size(); i++)
        {
            System.out.println("    " + (i + 1) + ")"
                    + products.get(i).getTitle());
        }
        String reply = keyboard("   Your choise");
        Integer index = Integer.valueOf(reply);
        if (index <= products.size())
        {
            return products.get(index - 1);
        }
        else
        {
            System.out.println("  You've selected incorrect product index. Input is required.");
            return null;
        }
    }

    private void output ()
    {
        for (Deal deal: deals)
        {
            System.out.println("Deal"+deal.getDate());
            System.out.println(deal.getBuyer().getName()
                    + "buys from" + deal.getSeller().getName());
            for (Entry<Product, Integer> entry : deal.getProducts().entrySet())
            {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("    " + product.getTitle() + " "
                        + quantity + " x " + product.getPrise()
                        + " = " + product.getCost(quantity));
            }
            System.out.println("Sum: " + deal.getSum());
            System.out.println("--------------------------");

            outputParty(deal.getBuyer());
            outputParty(deal.getSeller());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

    private void input()
    {
        boolean continionInput = true;
        do
        {
            Deal deal = inputDeal();
            deals.add(deal);
            continionInput = continueInputQuestion("Deal");
        }
        while (continionInput);
    }

    private boolean continueInputQuestion(String inputBody)
    {
        String reply = keyboard("  Input another  " + inputBody
                + "? (y - Yes, n - No)");
        if (reply.compareToIgnoreCase("n") == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void outputParty (Party party)
    {
        System.out.println(party.getName() + ":");
        System.out.println("Address: " + party.getAddress());
        for (Entry<String, String> entry : party.getProperties().entrySet())
        {
            System.out.println("  " + entry.getKey() + " = "
                    + entry.getValue());
        }
    }

    private String keyboard(String message)
    {
        System.out.print(message + ": ");
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}