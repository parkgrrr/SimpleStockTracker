package parkerstevens.net.simplestocktracker.data;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockTrackerDbSchema {
    public static final class TransactionTable {
        public static final String NAME = "transactions";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SYMBOL = "symbol"; //Stock Quote Foreign Key
            public static final String QUANTITY = "quantity";
            public static final String PRICE = "price";
            public static final String FEES = "fees";
        }
    }

    public static final class StockQuoteTable {
        public static final String NAME = "quotes";

        public static final class Cols {
            //public static final String UUID = "uuid";
            public static final String STATUS = "status";
            public static final String NAME = "name";
            public static final String SYMBOL = "symbol"; //composite key
            public static final String LASTPRICE = "last_price";
            public static final String CHANGE = "change";
            public static final String CHANGEPERCENT = "change_percent";
            public static final String TIMESTAMP = "time_stamp";
            public static final String MARKETCAP = "market_cap";
            public static final String VOLUME = "volume";
            public static final String CHANGEYTD = "change_ytd";
            public static final String CHANGEPERCENTYTD = "change_percent_ytd";
            public static final String HIGH = "high";
            public static final String LOW = "low";
            public static final String OPEN = "open";
            public static final String CREATETIME = "create_time";
        }
    }
}
