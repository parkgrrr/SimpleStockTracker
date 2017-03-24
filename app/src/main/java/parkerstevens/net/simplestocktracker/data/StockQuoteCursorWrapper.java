package parkerstevens.net.simplestocktracker.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import parkerstevens.net.simplestocktracker.data.StockTrackerDbSchema.StockQuoteTable;
import parkerstevens.net.simplestocktracker.model.Stock;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockQuoteCursorWrapper extends CursorWrapper {
    public StockQuoteCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Stock getStockQuote() {
        String status = getString(getColumnIndex(StockQuoteTable.Cols.STATUS));
        String name = getString(getColumnIndex(StockQuoteTable.Cols.NAME));
        String symbol = getString(getColumnIndex(StockQuoteTable.Cols.SYMBOL));
        String lastPrice = getString(getColumnIndex(StockQuoteTable.Cols.LASTPRICE));
        String change = getString(getColumnIndex(StockQuoteTable.Cols.CHANGE));
        double changePercent = getDouble(getColumnIndex(StockQuoteTable.Cols.CHANGEPERCENT));
        String timeStamp = getString(getColumnIndex(StockQuoteTable.Cols.TIMESTAMP));
        String marketCap = getString(getColumnIndex(StockQuoteTable.Cols.MARKETCAP));
        int volume = getInt(getColumnIndex(StockQuoteTable.Cols.VOLUME));
        String changeYtd = getString(getColumnIndex(StockQuoteTable.Cols.CHANGEYTD));
        double changePercentYtd = getDouble(getColumnIndex(StockQuoteTable.Cols.CHANGEPERCENTYTD));
        String high = getString(getColumnIndex(StockQuoteTable.Cols.HIGH));
        String low = getString(getColumnIndex(StockQuoteTable.Cols.LOW));
        String open = getString(getColumnIndex(StockQuoteTable.Cols.OPEN));
        String timeString = getString(getColumnIndex(StockQuoteTable.Cols.CREATETIME));
        Calendar createTime = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        //Date date = sdf.parse(timeString));
        try{
            createTime.setTime(sdf.parse(timeString));
        } catch (java.text.ParseException e){
            e.printStackTrace();
        }


        Stock stock = new Stock(createTime);
        stock.setStatus(status);
        stock.setName(name);
        stock.setSymbol(symbol);
        stock.setLastPrice(new BigDecimal(lastPrice));
        stock.setChange(new BigDecimal(change));
        stock.setChangePercent(changePercent);
        stock.setTimeStamp(timeStamp);
        stock.setMarketCap(new BigDecimal(marketCap));
        stock.setVolume(volume);
        stock.setChangeYTD(new BigDecimal(changeYtd));
        stock.setChangePercentYTD(changePercentYtd);
        stock.setHigh(new BigDecimal(high));
        stock.setLow(new BigDecimal(low));
        stock.setOpen(new BigDecimal(open));

        return stock;
    }
}
