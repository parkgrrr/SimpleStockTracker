package parkerstevens.net.simplestocktracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import parkerstevens.net.simplestocktracker.model.Transaction;

import static parkerstevens.net.simplestocktracker.data.StockTrackerDbSchema.TransactionTable;

/**
 * Created by pstev on 3/3/2017.
 */

public class StocksHelper{
    private static final String TAG = "StocksHelper";
    private static StocksHelper mStocksHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    //private MarkitOnDemandApiInterface mMarkitApi;
    private ApiHelper.MarkitOnDemandApiInterface mMarkitApi;
    private List<Transaction> mTrans = new ArrayList<>();


    public static StocksHelper get(Context context){
        if(mStocksHelper == null){
            mStocksHelper = new StocksHelper(context);
        }
            return mStocksHelper;
    }

    private StocksHelper(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new StockTrackerDbHelper(mContext)
                .getWritableDatabase();
        //setupRetrofit();
    }

    public void addTransaction(Transaction t) {
        ContentValues values = getContentValues(t);

        mDatabase.insert(TransactionTable.NAME, null, values);
    }

    public void deleteTransaction(UUID id) {
        mDatabase.delete(
                TransactionTable.NAME,
                TransactionTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
                );
    }

    public void updateTransaction(Transaction t) {
        String uuidString = t.getId().toString();
        ContentValues values = getContentValues(t);

        mDatabase.update(TransactionTable.NAME, values,
        TransactionTable.Cols.UUID + " = ?",
        new String[] {uuidString});
    }


    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        TransactionCursorWrapper cursor = queryTransactions(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                transactions.add(cursor.getTransaction());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return transactions;
    }

    public Transaction getTransaction(UUID id) {
        TransactionCursorWrapper cursor = queryTransactions(
                TransactionTable.Cols.UUID + " = ?",
                new String[] {id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTransaction();
        }finally {
            cursor.close();
        }
    }


    public static ContentValues getContentValues(Transaction trans){
        ContentValues values = new ContentValues();
        values.put(TransactionTable.Cols.UUID, trans.getId().toString());
        values.put(TransactionTable.Cols.SYMBOL, trans.getSymbol());
        values.put(TransactionTable.Cols.QUANTITY, trans.getQuantity() + "");
        values.put(TransactionTable.Cols.PRICE, trans.getPrice().toString());
        values.put(TransactionTable.Cols.FEES, trans.getFees().toString());

        return values;
    }

    private TransactionCursorWrapper queryTransactions(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TransactionTable.NAME,
                null, //colums - null selects all
                whereClause,
                whereArgs,
                null, //groupby
                null, //having
                null //orderby
        );
        return new TransactionCursorWrapper(cursor);
    }

}
