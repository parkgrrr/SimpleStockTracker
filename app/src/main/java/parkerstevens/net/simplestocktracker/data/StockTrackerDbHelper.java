package parkerstevens.net.simplestocktracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import parkerstevens.net.simplestocktracker.data.StockTrackerDbSchema.TransactionTable;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockTrackerDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "stockBase.db";

    public StockTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TransactionTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
                        TransactionTable.Cols.UUID + ", " +
                        TransactionTable.Cols.SYMBOL + ", " +
                        TransactionTable.Cols.QUANTITY + ", " +
                        TransactionTable.Cols.PRICE + ", " +
                        TransactionTable.Cols.FEES  +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
