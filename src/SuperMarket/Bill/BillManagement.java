package SuperMarket.Bill;

import SuperMarket.Goods.Goods;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BillManagement {
    public ArrayList<Bill> billArrayList = new ArrayList<>();
    public ArrayList<Goods> goodsArrayList = new ArrayList<>();
    public int id = 1;

    public Bill createBill(Scanner scanner, String name) {
        int code;
        ArrayList<Goods> AddGoods = new ArrayList<>();
        do {
            do {
                System.out.print("Nhập QR code sản phẩm: ");
                code = scanner.nextInt();
                if (code == 0) {
                    break;
                }
            }while(checkQrCode(code));
            if (code == 0) {
                break;
            }
            System.out.print("Nhập Số Lượng: ");
            int number = scanner.nextInt();
            Goods addGoods = new Goods();
            for (Goods goods : goodsArrayList) {
                if (goods.getQrcode() == code) {
                    addGoods.setName(goods.getName());
                    addGoods.setPrice(goods.getPrice());
                    addGoods.setQuantity(number);
                    addGoods.setDiscount(goods.getDiscount());
                    addGoods.setCode(goods.getCode());
                    addGoods.setQrcode(goods.getQrcode());
                    addGoods.setCategory(goods.getCategory());
                    AddGoods.add(addGoods);
                }
            }
        }
        while (true);
        double totalBill = 0;
        for (Goods goods : AddGoods) {
            totalBill += goods.getPrice()*goods.getQuantity();
        }
        LocalDate date = LocalDate.now();
        String payBy = null;
        int choice;
        do {
            System.out.println("Chọn Hình Thức Thanh Toán");
            System.out.println("1. Tiền Mặt");
            System.out.println("2. Thẻ");
            choice = scanner.nextInt();
            if (choice == 1) {
                payBy = "Tiền Mặt";
            }
            if (choice == 2) {
                payBy = "Thẻ";
            }
        }
        while (checkChoice(choice));
        return new Bill(id, name, AddGoods, totalBill, payBy, date);
    }

    public void addBill(Scanner scanner, String name) {
        Bill bill = createBill(scanner, name);
        displayBill(bill);
        int choice;
        String choiceString;
        do {
            System.out.println("1. In Bill");
            System.out.println("2. Huỷ Bill");
            System.out.println("3. Thay Đổi Thông Tin Bill");
            System.out.println("Nhập lựa chọn của bạn: ");
            choiceString = scanner.next();
        }
        while(checkChoice(choiceString));
        choice = Integer.parseInt(choiceString);
        switch (choice) {
            case 1 -> {
                billArrayList.add(bill);
                id++;
                displayBill(bill);
                System.out.println("In Bill Thành Công!!!");
                inventory(bill,goodsArrayList);
                writeGoodsFile(goodsArrayList);
                writeBillsFile(billArrayList);
            }
            case 2 -> System.out.println("Huỷ Bill Thành Công!!!");
            case 3 -> {
                int edit;
                do{
                    editBill(scanner,bill);
                    int totalEdit=0;
                    for (Goods goods : bill.getGoodsArrayList()) {
                        totalEdit += goods.getPrice()*goods.getQuantity();
                    }
                    bill.setTotalBill(totalEdit);
                    displayBill(bill);
                    System.out.println("1. In Bill");
                    System.out.println("2. Huỷ Bill");
                    System.out.println("3. Thay Đổi Thông Tin Bill");
                    System.out.println("Nhập lựa chọn của bạn: ");
                    edit = scanner.nextInt();
                }
                while(edit==3);
                if(edit==1){
                    billArrayList.add(bill);
                    id++;
                    displayBill(bill);
                    System.out.println("In Bill Thành Công!!!");
                    inventory(bill,goodsArrayList);
                    writeGoodsFile(goodsArrayList);
                    writeBillsFile(billArrayList);
                }else{
                    System.out.println("Huỷ Bill Thành Công!!!");
                }
            }
        }
        readGoodsFile();
    }

    public void writeGoodsFile(ArrayList<Goods> arrayList) {
        File file = new File("Goods");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Không Ghi Được File!!!");
        }
    }

    public void readGoodsFile() {
        File file = new File("Goods");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            goodsArrayList = (ArrayList<Goods>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("Không Đọc Được File!!!");
        }
    }

    public boolean checkChoice(int choice) {
        if (choice >= 1 && choice <= 2) {
            return false;
        } else {
            System.out.println("Dữ Liệu Nhập Vào Không Đúng Hãy Chọn Lại");
        }
        return true;
    }

    public void displayBill(Bill bill) {
        System.out.println("============================= Hoá Đơn =============================");
        System.out.println("Ngày Mua Hàng: "+bill.getBillDate());
        System.out.println("Số Hoá Đơn: "+bill.getId());
        System.out.println("Tên Nhân Viên: "+bill.getStaffName());
        System.out.printf("\n%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm","Số Lượng","Giảm Giá","Giá","Tổng");
        for (Goods goods: bill.getGoodsArrayList()) {
            displayGoods(goods);
        }
        System.out.println("Hình Thức Thanh Toán: " +bill.getPayBy());
        System.out.println("Tổng Cộng: " +bill.getTotalBill()+"\n");
        System.out.println("==================================================================\n");
    }

    public void displayGoods(Goods goods) {
        System.out.printf("%-15s%-15s%-15s%-15s%s\n", goods.getName(), goods.getQuantity(), goods.getDiscount(), goods.getPrice(), goods.getPrice()*goods.getQuantity());
    }

    public boolean checkQrCode(int code){
        for (Goods goods:goodsArrayList) {
            if(goods.getQrcode()==code){
                return false;
            }
        }
        System.out.println("Không Tìm Thấy Mã QrCode!!!");
        return true;
    }

    public boolean checkChoice(String choice) {
        if (Pattern.matches("([0-3]){1}",choice)){
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }

    public void editBill(Scanner scanner,Bill bill) {
        int index = 1;
        System.out.printf("%-15s%-20s%-20s%-15s%s\n\n", "STT", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá");
        for (Goods goods : bill.getGoodsArrayList()) {
            System.out.printf("%-15s%-20s%-20s%-15s%s\n", index, goods.getName(), goods.getPrice(), goods.getQuantity(), goods.getDiscount());
            index++;
        }
        System.out.println("Chọn Sản Phẩm Cần Điều Chỉnh: ");
        int choice = scanner.nextInt();
        System.out.println("Nhập Số Lượng Muốn Thay Đổi: ");
        int numberChange = scanner.nextInt();
        if (numberChange != 0) {
            bill.getGoodsArrayList().get(choice - 1).setQuantity(numberChange);
        } else {
            bill.getGoodsArrayList().remove(choice - 1);
        }
    }

        public void inventory(Bill bill, ArrayList<Goods> goodsArrayList){
            for (Goods goodsAdd:bill.getGoodsArrayList()) {
                for (Goods goods:goodsArrayList) {
                    if(goodsAdd.getQrcode()==goods.getQrcode()){
                        goods.setQuantity(goods.getQuantity()-goodsAdd.getQuantity());
                }
            }
        }
    }

    public void writeBillsFile(ArrayList<Bill> arrayList) {
        File file = new File("Bills");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Không Ghi Được File!!!");
        }
    }

    public void readBillsFile() {
        File file = new File("Bills");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            billArrayList = (ArrayList<Bill>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("Không Đọc Được File!!!");
        }
        id=billArrayList.size()+1;
    }

    public void checkBillByDate(Scanner scanner) {
        System.out.print("Nhập Ngày Cần Xem Bill (dd/mm/yyyy): ");
        scanner.nextLine();
        String day = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(day,formatter);
        for (Bill bill:billArrayList) {
            if(bill.getBillDate().equals(date)){
                displayBill(bill);
            }
        }
    }

    public void checkTurnoverByDate(Scanner scanner) {
        System.out.print("Nhập Ngày Cần Xem Bill (dd/mm/yyyy): ");
        scanner.nextLine();
        String day = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(day, formatter);
        double turnoverByCash=0;
        double turnoverByCard=0;
        for (Bill bill : billArrayList) {
            if (bill.getBillDate().equals(date)) {
                if(bill.getPayBy().equals("Tiền Mặt")){
                    turnoverByCash +=bill.getTotalBill();
                }
                if(bill.getPayBy().equals("Thẻ")){
                    turnoverByCard +=bill.getTotalBill();
                }
            }
        }
        System.out.println("Tổng doanh thu bằng tiền mặt: "+turnoverByCash);
        System.out.println("Tổng doanh thu bằng thẻ: "+turnoverByCard);
    }
}

