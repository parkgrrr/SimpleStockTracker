package parkerstevens.net.simplestocktracker;

import java.util.UUID;

/**
 * Created by pstev on 3/7/2017.
 */

public class Transactions {
    private UUID transId;
    private String mSymbol;
    private int mQuantity;
    private double mPrice;
    private int mFees;

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public UUID getTransId() {
        return transId;
    }

    public void setTransId(UUID transId) {
        this.transId = transId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public int getFees() {
        return mFees;
    }

    public void setFees(int fees) {
        mFees = fees;
    }
}
