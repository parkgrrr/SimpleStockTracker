package parkerstevens.net.simplestocktracker.data;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockTrackerDbSchema {
    public static final class TransactionTable {
        public static final String NAME = "transactions";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SYMBOL = "symbol";
            public static final String QUANTITY = "quantity";
            public static final String PRICE = "price";
            public static final String FEES = "fees";
        }
    }
}
