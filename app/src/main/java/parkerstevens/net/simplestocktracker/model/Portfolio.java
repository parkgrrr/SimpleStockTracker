package parkerstevens.net.simplestocktracker.model;

import java.util.UUID;

/**
 * Created by pstev on 3/7/2017.
 *
 * In the future I would like to implement an add portfolio feature
 */

public class Portfolio {
    private String mName;
    private UUID mPortId;

    public Portfolio(String name) {
        mName = name;
        mPortId = UUID.randomUUID();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public UUID getPortId() {
        return mPortId;
    }

}
