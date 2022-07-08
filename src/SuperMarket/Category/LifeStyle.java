package SuperMarket.Category;

import java.io.Serializable;

public class LifeStyle extends Category implements Serializable {
        private String origin;

    public LifeStyle(String name, String origin) {
        super(name);
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Xuất Xứ=" + origin +
                "} ";
    }
}
