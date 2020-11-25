package pos.machine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {

    public String printReceipt(List<String> barcodes) {
        ArrayList<PurchasedItem> purchasedItems = addPurchasedItems(barcodes);

        String receipt = "***<store earning no money>Receipt***\n";

        int total = 0;

        for (PurchasedItem purchasedItem : purchasedItems) {
            receipt += String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", purchasedItem.getName(), purchasedItem.getAmount(), purchasedItem.getPrice(), purchasedItem.getSubTotal());
            total += purchasedItem.getSubTotal();
        }

        receipt += "----------------------\nTotal: " + total + " (yuan)\n**********************";

        return receipt;
    }

    private ArrayList<PurchasedItem> addPurchasedItems(List<String> barcodes) {
        Map<String, Long> barcodeCountMap = countBarcode(barcodes);
        ArrayList<PurchasedItem> purchasedItems = new ArrayList<>();

        for (Map.Entry<String, Long> entry : barcodeCountMap.entrySet()) {
            List<ItemInfo> listOfItemInfo = ItemDataLoader.loadAllItemInfos();
            ItemInfo itemInfo = listOfItemInfo.stream().filter(e -> e.getBarcode().equals(entry.getKey())).findAny().orElse(null);
            PurchasedItem purchasedItem = new PurchasedItem(itemInfo.getName(), itemInfo.getPrice(), entry.getValue().intValue());
            purchasedItems.add(purchasedItem);
        }

        return purchasedItems;
    }

    private LinkedHashMap<String, Long> countBarcode(List<String> barcodes) {
        LinkedHashMap<String, Long> barcodeCountMap = new LinkedHashMap<>();

        for (String barcode : barcodes) {
            barcodeCountMap.putIfAbsent(barcode, (long) 0);
            barcodeCountMap.put(barcode, barcodeCountMap.get(barcode) + 1);
        }

        return barcodeCountMap;
    }
}
