package SuperMarket.Menu;

import SuperMarket.Bill.BillManagement;
import SuperMarket.Goods.GoodsManagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuGoodsManagement {

    public void menuStaff(Scanner scanner,String name){
        GoodsManagement goodsManagement = new GoodsManagement();
        goodsManagement.readGoodsFile();
        goodsManagement.readFile();
        BillManagement billManagement = new BillManagement();
        billManagement.readBillsFile();
        billManagement.readGoodsFile();
        int choice;
        String choiceString;
        do {
                System.out.println("================================MENU================================");
                System.out.println("*           1. Thêm Sản Phẩm                                       *");
                System.out.println("*           2. Hiển Thị Tất Cả Sản Phẩm                            *");
                System.out.println("*           3. Tìm Kiếm Sản Phẩm Theo QR Code                      *");
                System.out.println("*           4. Tìm Kiếm Sản Phẩm Theo Ngành Hàng                   *");
                System.out.println("*           5. Thanh Toán                                          *");
                System.out.println("*           6. Xem Hoá Đơn Theo Ngày                               *");
                System.out.println("*           7. Xem Doanh Thu Theo Ngày                             *");
                System.out.println("*           8. Đăng Xuất                                           *");
                System.out.println("====================================================================");
                do {
                    System.out.print("Nhập lựa chọn: ");
                    choiceString=scanner.next();
                }
                while(checkChoice(choiceString));
                choice = Integer.parseInt(choiceString);
            switch (choice) {
                case 1 -> goodsManagement.addGoods(scanner);
                case 2 -> goodsManagement.displayAllGoods();
                case 3 -> goodsManagement.searchGoodsByQrCode(scanner);
                case 4 -> goodsManagement.searchGoodsByCategory(scanner);
                case 5 -> billManagement.addBill(scanner,name);
                case 6 -> billManagement.checkBillByDate(scanner);
                case 7 -> billManagement.checkTurnoverByDate(scanner);
            }
        }
        while (choice!=8);
    }

    public void menuAdmin(Scanner scanner,String name){
        GoodsManagement goodsManagement = new GoodsManagement();
        goodsManagement.readGoodsFile();
        goodsManagement.readFile();
        BillManagement billManagement = new BillManagement();
        billManagement.readBillsFile();
        billManagement.readGoodsFile();
        int choice;
        String choiceString;
        do {
                System.out.println("================================MENU================================");
                System.out.println("*           1. Thêm Sản Phẩm                                       *");
                System.out.println("*           2. Sửa Sản Phẩm                                        *");
                System.out.println("*           3. Xoá Sản Phẩm                                        *");
                System.out.println("*           4. Hiển Thị Tất Cả Sản Phẩm                            *");
                System.out.println("*           5. Tìm Kiếm Sản Phẩm Theo QR Code                      *");
                System.out.println("*           6. Tìm Kiếm Sản Phẩm Theo Ngành Hàng                   *");
                System.out.println("*           7. Sale Sản Phẩm Theo QR Code                          *");
                System.out.println("*           8. Sale Sản Phẩm Theo Ngành Hàng                       *");
                System.out.println("*           9. Sale Sản Phẩm Theo Mã Sản Phẩm                      *");
                System.out.println("*           10. Thanh Toán                                         *");
                System.out.println("*           11. Xem Hoá Đơn Theo Ngày                              *");
                System.out.println("*           12. Xem Doanh Thu Theo Ngày                            *");
                System.out.println("*           13. Kiểm Tra Hàng Sắp Hết Hạn                          *");
                System.out.println("*           14. Đăng Xuất                                          *");
                System.out.println("====================================================================");
            do {
                System.out.print("Nhập lựa chọn: ");
                choiceString=scanner.next();
            }
            while(checkChoiceAdmin(choiceString));
            choice = Integer.parseInt(choiceString);
            switch (choice) {
                case 1 -> goodsManagement.addGoods(scanner);
                case 2 -> goodsManagement.editGoods(scanner);
                case 3 -> goodsManagement.deleteGoodsByQRcode(scanner);
                case 4 -> goodsManagement.displayAllGoods();
                case 5 -> goodsManagement.searchGoodsByQrCode(scanner);
                case 6 -> goodsManagement.searchGoodsByCategory(scanner);
                case 7 -> goodsManagement.setSaleByQrCode(scanner);
                case 8 -> goodsManagement.setSaleByCategory(scanner);
                case 9 -> goodsManagement.setSaleByCode(scanner);
                case 10 -> billManagement.addBill(scanner,name);
                case 11 -> billManagement.checkBillByDate(scanner);
                case 12 -> billManagement.checkTurnoverByDate(scanner);
                case 13 -> goodsManagement.checkGoodsOutOfDate();
            }
        }
        while (choice!=14);
    }

    public boolean checkChoice(String choice) {
        if (Pattern.matches("([0-8]){1}",choice)){
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }

    public boolean checkChoiceAdmin(String choice) {
        if (Pattern.matches("([0-1][0-4]){1}|([0-9]){1}",choice)){
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }
}
