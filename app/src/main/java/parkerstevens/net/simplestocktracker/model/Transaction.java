package parkerstevens.net.simplestocktracker.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by pstev on 3/7/2017.
 */

public class Transaction {
    private UUID mId;
    private String mSymbol;
    private int mQuantity;
    private BigDecimal mPrice;
    private BigDecimal mFees;

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

    public BigDecimal getPrice() {
        return mPrice;
    }

    public void setPrice(BigDecimal price) {
        mPrice = price;
    }

    public BigDecimal getFees() {
        return mFees;
    }

    public void setFees(BigDecimal fees) {
        mFees = fees;
    }
}
