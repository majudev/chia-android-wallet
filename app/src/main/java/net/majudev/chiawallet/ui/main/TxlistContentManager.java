package net.majudev.chiawallet.ui.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxlistContentManager {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<TxlistItem> ITEMS = new ArrayList<TxlistItem>();

    /**
     * A map of transactions, by hash.
     */
    public static final Map<String, TxlistItem> ITEM_MAP = new HashMap<String, TxlistItem>();

    static {
        // Add some sample items.
        for (int i = 1; i <= 25; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

    private static void addItem(TxlistItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.hash, item);
    }

    private static TxlistItem createPlaceholderItem(int position) {
        return new TxlistItem(
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015" + position,
                (position % 2 == 0) ? TxlistItem.Direction.OUT : TxlistItem.Direction.IN,
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
                new Date(),
                115.185623938471*position,
                1.185623938471*(position/4),
                position % 14
        );
    }

    /**
     * An item representing tx entry
     */
    public static class TxlistItem {
        public final String hash;
        public final Direction direction;
        public final String address;
        public final Date date;
        public final double amount;
        public final double fee;
        public final int confirmations;

        public static enum Direction { IN, OUT };

        public TxlistItem(String hash,
                          Direction direction,
                          String address,
                          Date date,
                          double amount,
                          double fee,
                          int confirmations) {
            this.hash = hash;
            this.direction = direction;
            this.address = address;
            this.date = date;
            this.amount = amount;
            this.fee = fee;
            this.confirmations = confirmations;
        }

        /*@Override
        public String toString() {
            return ;
        }*/
    }
}