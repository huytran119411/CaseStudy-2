package SuperMarket.Category;

import java.io.Serializable;

public class ELHA extends Category implements Serializable {

    private int warrantyPeriod;

    public ELHA(String name, int warrantyPeriod) {
        super(name);
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Hạn Bảo Hành (Năm)=" + warrantyPeriod +
                "} ";
    }
}
