package parkerstevens.net.simplestocktracker;

import java.util.UUID;

/**
 * Created by pstev on 3/7/2017.
 */

public class Transaction {
    private UUID mTransId;
    private String mSymbol;
    private int mQuantity;
    private double mPrice;
    private int mFees;

    public Transaction(String symbol) {
        mTransId = UUID.randomUUID();
        setSymbol(symbol);
    }

    public UUID getTransId() {
        return mTransId;
    }

    public void setTransId(UUID transId) {
        mTransId = transId;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        symbol.toUpperCase();
        mSymbol = symbol;
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
