package parkerstevens.net.simplestocktracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import parkerstevens.net.simplestocktracker.data.StockTrackerDbSchema.StockQuoteTable;
import parkerstevens.net.simplestocktracker.model.Stock;
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

    public void addStockQuote(Stock stock) {
        ContentValues values = getQuoteContentValues(stock);

        mDatabase.insert(StockQuoteTable.NAME, null, values);
    }

    public void deleteStockQuote(String symbol) {
        mDatabase.delete(
                StockQuoteTable.NAME,
                StockQuoteTable.Cols.SYMBOL + " = ?",
                new String[]{symbol}
        );
    }

    public void updateStockQuote(Stock stock) {
        String symbol = stock.getSymbol();
        ContentValues values = getQuoteContentValues(stock);

        mDatabase.update(StockQuoteTable.NAME, values,
                StockQuoteTable.Cols.SYMBOL + " = ?",
                new String[] {symbol});
    }

    public static ContentValues getQuoteContentValues(Stock stock){
        ContentValues values = new ContentValues();
        values.put(StockQuoteTable.Cols.STATUS, stock.getStatus());
        values.put(StockQuoteTable.Cols.NAME, stock.getName());
        values.put(StockQuoteTable.Cols.SYMBOL, stock.getSymbol());
        values.put(StockQuoteTable.Cols.LASTPRICE, stock.getLastPrice().toString());
        values.put(StockQuoteTable.Cols.CHANGE, stock.getChange().toString());
        values.put(StockQuoteTable.Cols.CHANGEPERCENT, stock.getChangePercent() + "");
        values.put(StockQuoteTable.Cols.TIMESTAMP, stock.getTimeStamp());
        values.put(StockQuoteTable.Cols.MARKETCAP, stock.getMarketCap().toString());
        values.put(StockQuoteTable.Cols.VOLUME, stock.getVolume() + "");
        values.put(StockQuoteTable.Cols.CHANGEYTD, stock.getChangeYTD().toString());
        values.put(StockQuoteTable.Cols.CHANGEPERCENTYTD, stock.getChangePercentYTD() + "");
        values.put(StockQuoteTable.Cols.HIGH, stock.getHigh().toString());
        values.put(StockQuoteTable.Cols.LOW, stock.getLow().toString());
        values.put(StockQuoteTable.Cols.OPEN, stock.getOpen().toString());
        values.put(StockQuoteTable.Cols.CREATETIME, stock.getCreateTime().getTime().toString());

        return values;
    }
    public List<Stock> getStockQuotes() {
        List<Stock> stocks = new ArrayList<>();

        StockQuoteCursorWrapper cursor = queryStockQuotes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                stocks.add(cursor.getStockQuote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return stocks;
    }

    public Stock getStockQuote(String symbol) {
        StockQuoteCursorWrapper cursor = queryStockQuotes(
                StockQuoteTable.Cols.SYMBOL + " = ?",
                new String[] {symbol}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getStockQuote();
        }finally {
            cursor.close();
        }
    }

    private StockQuoteCursorWrapper queryStockQuotes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                StockQuoteTable.NAME,
                null, //colums - null selects all
                whereClause,
                whereArgs,
                null, //groupby
                null, //having
                StockQuoteTable.Cols.SYMBOL //orderby
        );
        return new StockQuoteCursorWrapper(cursor);
    }

    public void addTransaction(Transaction t) {
        ContentValues values = getTransContentValues(t);

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
        ContentValues values = getTransContentValues(t);

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


    public static ContentValues getTransContentValues(Transaction trans){
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
                TransactionTable.Cols.SYMBOL //orderby
        );
        return new TransactionCursorWrapper(cursor);
    }

}
