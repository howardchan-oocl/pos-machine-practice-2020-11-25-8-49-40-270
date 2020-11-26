package pos.machine;

public class PurchasedItem{
    private final String name;
    private final int price;
    private final int amount;
    private final int subTotal;

    public PurchasedItem(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.subTotal = price * amount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public String getSubString() {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", name, amount, price, subTotal);
    }
}
