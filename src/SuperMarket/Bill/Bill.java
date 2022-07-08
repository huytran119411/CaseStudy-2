package SuperMarket.Bill;

import SuperMarket.Goods.Goods;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bill implements Serializable {
    private int id;
    private String staffName;
    private ArrayList<Goods> goodsArrayList = new ArrayList<>();
    private double totalBill;
    private String payBy;
    private LocalDate billDate;

    public Bill(int id, String staffName, ArrayList<Goods> goodsArrayList, double totalBill, String payBy, LocalDate billDate) {
        this.id = id;
        this.staffName = staffName;
        this.goodsArrayList = goodsArrayList;
        this.totalBill = totalBill;
        this.payBy = payBy;
        this.billDate = billDate;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public ArrayList<Goods> getGoodsArrayList() {
        return goodsArrayList;
    }

    public void setGoodsArrayList(ArrayList<Goods> goodsArrayList) {
        this.goodsArrayList = goodsArrayList;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public String getPayBy() {
        return payBy;
    }

    public void setPayBy(String payBy) {
        this.payBy = payBy;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", staffName='" + staffName + '\'' +
                ", goodsArrayList=" + goodsArrayList +
                ", totalBill=" + totalBill +
                ", payBy='" + payBy + '\'' +
                ", billDate=" + billDate +
                '}';
    }
}
