package parkerstevens.net.simplestocktracker;


import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by pstev on 3/3/2017.
 */

public class Stock {
    @SerializedName("Name")
    private String mName;
    @SerializedName("Symbol")
    private String mSymbol;
    @SerializedName("LastPrice")
    private BigDecimal mLastPrice;
    @SerializedName("Change")
    private double mChange;
    @SerializedName("ChangePercent")
    private double mChangePercent;
    @SerializedName("TimeStamp")
    private String mTimeStamp;
    @SerializedName("MarketCap")
    private BigDecimal mMarketCap;
    @SerializedName("Volume")
    private int mVolume;
    @SerializedName("ChangeYTD")
    private BigDecimal mChangeYTD;
    @SerializedName("ChangePercentYTD")
    private double mChangePercentYTD;
    @SerializedName("High")
    private double mHigh;
    @SerializedName("Low")
    private double mLow;
    @SerializedName("Open")
    private double mOpen;

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

    public double getChangePercent() {
        return mChangePercent;
    }

    public void setChangePercent(double changePresent) {
        mChangePercent = changePresent;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
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

    public BigDecimal getChangeYTD() {
        return mChangeYTD;
    }

    public void setChangeYTD(BigDecimal changeYTD) {
        mChangeYTD = changeYTD;
    }

    public double getChangePercentYTD() {
        return mChangePercentYTD;
    }

    public void setChangePercentYTD(double changePercentYTD) {
        mChangePercentYTD = changePercentYTD;
    }

    public double getHigh() {
        return mHigh;
    }

    public void setHigh(double high) {
        mHigh = high;
    }

    public double getLow() {
        return mLow;
    }

    public void setLow(double low) {
        mLow = low;
    }

    public double getOpen() {
        return mOpen;
    }

    public void setOpen(double open) {
        mOpen = open;
    }
}
