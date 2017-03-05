package parkerstevens.net.simplestocktracker;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pstev on 3/3/2017.
 */

public class Stock {
    @SerializedName("Name")
    private String mName;
    @SerializedName("Symbol")
    private String mSymbol;
    @SerializedName("LastPrice")
    private double mLastPrice;
    @SerializedName("Change")
    private double mChange;
    @SerializedName("hangePresent")
    private double mChangePresent;
    @SerializedName("TimeStamp")
    private String mTimeStamp;
    @SerializedName("MarketCap")
    private double mMarketCap;
    @SerializedName("Volume")
    private int mVolume;
    @SerializedName("ChangeYTD")
    private double mChangeYTD;
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

    public double getLastPrice() {
        return mLastPrice;
    }

    public void setLastPrice(double lastPrice) {
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

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        mTimeStamp = timeStamp;
    }

    public double getMarketCap() {
        return mMarketCap;
    }

    public void setMarketCap(double marketCap) {
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
