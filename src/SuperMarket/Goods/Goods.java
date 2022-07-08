package SuperMarket.Goods;

import SuperMarket.Category.Category;

import java.io.Serializable;

public class Goods implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private double discount;
    private String code;
    private int qrcode;

    private Category category = new Category();

    public Goods(String name, double price, int quantity, double discount, String code, int qrcode, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.code = code;
        this.qrcode = qrcode;
        this.category = category;
    }

    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQrcode() {
        return qrcode;
    }

    public void setQrcode(int qrcode) {
        this.qrcode = qrcode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", Price=" + price +
                ", Quantity=" + quantity +
                ", Discount=" + discount +
                ", Code='" + code + '\'' +
                ", Qrcode=" + qrcode +
                ", Category=" + category +
                '}';
    }
}
