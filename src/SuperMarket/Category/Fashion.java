package SuperMarket.Category;

import java.io.Serializable;

public class Fashion extends Category implements Serializable {
    private int numberDateToExchange;

    public Fashion() {
    }

    public Fashion(String name, int numberDateToExchange) {
        super(name);
        this.numberDateToExchange = numberDateToExchange;
    }

    public int getNumberDateToExchange() {
        return numberDateToExchange;
    }

    public void setNumberDateToExchange(int numberDateToExchange) {
        this.numberDateToExchange = numberDateToExchange;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Số Ngày Có Thể Đổi Trả=" + numberDateToExchange +
                "} ";
    }
}
