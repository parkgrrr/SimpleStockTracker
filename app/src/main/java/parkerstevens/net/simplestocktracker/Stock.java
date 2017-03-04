package parkerstevens.net.simplestocktracker;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pstev on 3/3/2017.
 */

public class Stock {
    private String mName;
    private String mSymbol;
    private BigDecimal mLastPrice;
    private double mChange;
    private double mChangePresent;
    private Date mTimeStamp;
    private BigDecimal mMarketCap;
    private int mVolume;
    private double mChangeYTD;
    private double mChangePercentYTD;
    private BigDecimal mHigh;
    private BigDecimal mLow;
    private BigDecimal mOpen;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public BigDecimal getLastPrice() {
        return mLastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        mLastPrice = lastPrice;
    }

    public double getChange() {
        return mChange;
    }

    public void setChange(double change) {
        mChange = change;
    }

    public double getChangePresent() {
        return mChangePresent;
    }

    public void setChangePresent(double changePresent) {
        mChangePresent = changePresent;
    }

    public Date getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        mTimeStamp = timeStamp;
    }

    public BigDecimal getMarketCap() {
        return mMarketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        mMarketCap = marketCap;
    }

    public int getVolume() {
        return mVolume;
    }

    public void setVolume(int volume) {
        mVolume = volume;
    }

    public double getChangeYTD() {
        return mChangeYTD;
    }

    public void setChangeYTD(double changeYTD) {
        mChangeYTD = changeYTD;
    }

    public double getChangePercentYTD() {
        return mChangePercentYTD;
    }

    public void setChangePercentYTD(double changePercentYTD) {
        mChangePercentYTD = changePercentYTD;
    }

    public BigDecimal getHigh() {
        return mHigh;
    }

    public void setHigh(BigDecimal high) {
        mHigh = high;
    }

    public BigDecimal getLow() {
        return mLow;
    }

    public void setLow(BigDecimal low) {
        mLow = low;
    }

    public BigDecimal getOpen() {
        return mOpen;
    }

    public void setOpen(BigDecimal open) {
        mOpen = open;
    }
}
