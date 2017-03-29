package parkerstevens.net.simplestocktracker.model;

import java.util.UUID;

/**
 * Created by pstev on 3/7/2017.
 */

public class Transaction {
    private UUID mId;
    private String mSymbol;
    private int mQuantity;
    private String mPrice;
    private String mFees;

    public Transaction(UUID id) {
        mId = id;
        //setSymbol(symbol);
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
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

    public String getPrice() {
        if(mPrice == null){
            return "0";
        }
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getFees() {
        return mFees;
    }

    public void setFees(String fees) {
        mFees = fees;
    }
}
