import java.util.*;

class Stock {

    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void updatePrice() {
        Random r = new Random();
        price += (r.nextDouble() * 100 - 50);

        if(price < 100)
            price = 100;
    }
}

class Investor {

    String name;
    double balance;

    HashMap<String, Integer> portfolio =
            new HashMap<>();

    Investor(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void buyStock(Stock stock, int qty) {

        double amount = stock.price * qty;

        if(balance >= amount) {

            balance -= amount;

            portfolio.put(
                    stock.name,
                    portfolio.getOrDefault(
                            stock.name,0) + qty);

            System.out.println(
                    "Successfully Purchased");
        }
        else {
            System.out.println(
                    "Insufficient Balance");
        }
    }

    void sellStock(Stock stock, int qty) {

        int owned =
                portfolio.getOrDefault(
                        stock.name,0);

        if(owned >= qty) {

            portfolio.put(
                    stock.name,
                    owned - qty);

            balance += stock.price * qty;

            System.out.println(
                    "Successfully Sold");
        }
        else {
            System.out.println(
                    "Not Enough Shares");
        }
    }

    void showPortfolio(
            ArrayList<Stock> market) {

        System.out.println(
                "\n===== PORTFOLIO =====");

        double total = balance;

        for(Stock s : market) {

            int qty =
                    portfolio.getOrDefault(
                            s.name,0);

            double value =
                    qty * s.price;

            total += value;

            System.out.println(
                    s.name +
                    " | Shares: " + qty +
                    " | Value: ₹" + value);
        }

        System.out.println(
                "Cash Balance: ₹" +
                balance);

        System.out.println(
                "Total Portfolio Value: ₹" +
                total);
    }
}

public class StockTradingEnvironmentInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market =
                new ArrayList<>();

        market.add(
                new Stock("TCS",3500));

        market.add(
                new Stock("INFY",1800));

        market.add(
                new Stock("WIPRO",500));

        Investor investor =
                new Investor(
                        "Ragavi",
                        100000);

        while(true) {

            System.out.println(
                    "\n===== STOCK MARKET =====");

            System.out.println(
                    "1. View Market");

            System.out.println(
                    "2. Buy Stock");

            System.out.println(
                    "3. Sell Stock");

            System.out.println(
                    "4. View Portfolio");

            System.out.println(
                    "5. Update Prices");

            System.out.println(
                    "6. Exit");

            System.out.print(
                    "Enter Choice: ");

            int choice =
                    sc.nextInt();

            switch(choice) {

                case 1:

                    System.out.println(
                            "\nAvailable Stocks");

                    for(int i=0;i<market.size();i++) {

                        Stock s =
                                market.get(i);

                        System.out.println(
                                (i+1)+". "+
                                s.name+
                                " - ₹"+
                                String.format(
                                        "%.2f",
                                        s.price));
                    }
                    break;

                case 2:

                    System.out.print(
                            "Stock Number: ");

                    int buy =
                            sc.nextInt()-1;

                    System.out.print(
                            "Quantity: ");

                    int qty =
                            sc.nextInt();

                    investor.buyStock(
                            market.get(buy),
                            qty);

                    break;

                case 3:

                    System.out.print(
                            "Stock Number: ");

                    int sell =
                            sc.nextInt()-1;

                    System.out.print(
                            "Quantity: ");

                    qty =
                            sc.nextInt();

                    investor.sellStock(
                            market.get(sell),
                            qty);

                    break;

                case 4:

                    investor.showPortfolio(
                            market);

                    break;

                case 5:

                    for(Stock s : market)
                        s.updatePrice();

                    System.out.println(
                            "Market Updated");

                    break;

                case 6:

                    System.out.println(
                            "Thank You");

                    System.exit(0);
            }
        }
    }
}