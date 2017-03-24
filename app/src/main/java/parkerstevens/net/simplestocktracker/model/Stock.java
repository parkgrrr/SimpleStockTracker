package parkerstevens.net.simplestocktracker.model;


import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by pstev on 3/3/2017.
 */

public class Stock {
    @SerializedName("Status")
    private String mStatus;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Symbol")
    private String mSymbol;
    @SerializedName("LastPrice")
    private BigDecimal mLastPrice;
    @SerializedName("Change")
    private BigDecimal mChange;
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
    private BigDecimal mHigh;
    @SerializedName("Low")
    private BigDecimal mLow;
    @SerializedName("Open")
    private BigDecimal mOpen;
    private Calendar mCreateTime;

    public Stock() {
        mCreateTime = Calendar.getInstance(Locale.US);
    }

    public Stock(Calendar createTime) {
        mCreateTime = createTime;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

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

    public BigDecimal getChange() {
        return mChange;
    }

    public void setChange(BigDecimal change) {
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

    public void setCreateTime(Calendar createTime) {
        mCreateTime = createTime;
    }

    public Calendar getCreateTime() {
        return mCreateTime;
    }
}
