package SuperMarket.Category;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tên Ngành Hàng: '" + name + '\'' +
                ", ";
    }
}
