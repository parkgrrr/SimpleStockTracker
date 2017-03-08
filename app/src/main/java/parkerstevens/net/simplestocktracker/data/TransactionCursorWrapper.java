package parkerstevens.net.simplestocktracker.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.math.BigDecimal;
import java.util.UUID;

import parkerstevens.net.simplestocktracker.model.Transaction;

import static parkerstevens.net.simplestocktracker.data.StockTrackerDbSchema.TransactionTable;

/**
 * Created by pstev on 3/7/2017.
 */

public class TransactionCursorWrapper extends CursorWrapper {
    public TransactionCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Transaction getTransaction() {
        String uuidString = getString(getColumnIndex(TransactionTable.Cols.UUID));
        String symbol = getString(getColumnIndex(TransactionTable.Cols.SYMBOL));
        int quantity = getInt(getColumnIndex(TransactionTable.Cols.QUANTITY));
        double price = getDouble(getColumnIndex(TransactionTable.Cols.PRICE));
        double fees = getDouble(getColumnIndex(TransactionTable.Cols.FEES));

        Transaction trans = new Transaction(UUID.fromString(uuidString));
        trans.setSymbol(symbol);
        trans.setQuantity(quantity);
        trans.setPrice(new BigDecimal(price));
        trans.setFees(new BigDecimal(fees));

        return trans;
    }
}
