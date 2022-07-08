package SuperMarket.Goods;

import SuperMarket.Category.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GoodsManagement {
    public ArrayList<Goods> goodsArrayList = new ArrayList<>();
    public ArrayList<GoodsCode> codeFMCGArrayList = new ArrayList<>();
    public ArrayList<GoodsCode> codeELHAArrayList = new ArrayList<>();
    public ArrayList<GoodsCode> codeFashionArrayList = new ArrayList<>();
    public ArrayList<GoodsCode> codeLifeStyleArrayList = new ArrayList<>();


    public Goods createGoods(Scanner scanner) {
        System.out.print("Nhập Tên Sản Phẩm: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        double price;
        String priceString;
        do {
            System.out.print("Nhập Giá Sản Phẩm: ");
            priceString = scanner.nextLine();
        }
        while (checkNumber(priceString));
        price = Integer.parseInt(priceString);
        int quantity;
        String quantityString;
        do {
            System.out.print("Nhập Số Lượng Sản Phẩm: ");
            quantityString = scanner.nextLine();
        }
        while (checkNumber(quantityString));
        quantity = Integer.parseInt(quantityString);
        double discount = 0;
        int qrCode;
        String qrCodeString;
        do {
            do {
                System.out.print("Nhập QR Code: ");
                qrCodeString = scanner.nextLine();
            }
            while (checkNumber(qrCodeString));
            qrCode = Integer.parseInt(qrCodeString);
        }
        while(checkQrCodeExist(qrCode));
        System.out.println("1. FMCG");
        System.out.println("2. ELHA");
        System.out.println("3. Fashion");
        System.out.println("4. LifeStyle");
        int choice;
        String choiceString;
        do{
            System.out.println("Chọn Ngành Hàng: ");
            choiceString = scanner.nextLine();
        }
        while(checkChoiceCategory(choiceString));
        choice = Integer.parseInt(choiceString);
        Goods goods = null;
        switch (choice) {
            case 1 -> {
                System.out.print("Nhập Ngày Hết Hạn (dd/mm/yyyy): ");
                String day = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(day,formatter);
                System.out.println("Chọn Mã Sản Phẩm: ");
                displayCode(codeFMCGArrayList);
                int number = scanner.nextInt();
                String fileName = "CodeFMCG";
                createCode(codeFMCGArrayList, number, scanner, fileName);
                String code = codeFMCGArrayList.get(number - 1).getCode();
                String categoryName = "FMCG";
                Category category = new FMCG(categoryName, date);
                goods = new Goods(name, price, quantity, discount, code, qrCode, category);
            }
            case 2 -> {
                System.out.print("Nhập Số Năm Bảo Hành: ");
                int warrantyPeriod = scanner.nextInt();
                System.out.println("Chọn Mã Sản Phẩm: ");
                displayCode(codeELHAArrayList);
                int number = scanner.nextInt();
                String fileName = "CodeELHA";
                createCode(codeELHAArrayList, number, scanner, fileName);
                String code = codeELHAArrayList.get(number - 1).getCode();
                String categoryName = "ELHA";
                Category category = new ELHA(categoryName, warrantyPeriod);
                goods = new Goods(name, price, quantity, discount, code, qrCode, category);
            }
            case 3 -> {
                System.out.print("Nhập Số Ngày Có Thể Đổi Trả Hàng: ");
                int numberDateToExchange = scanner.nextInt();
                System.out.println("Chọn Mã Sản Phẩm: ");
                displayCode(codeFashionArrayList);
                int number = scanner.nextInt();
                String fileName = "CodeFashion";
                createCode(codeFashionArrayList, number, scanner, fileName);
                String code = codeFashionArrayList.get(number - 1).getCode();
                String categoryName = "Fashion";
                Category category = new Fashion(categoryName, numberDateToExchange);
                goods = new Goods(name, price, quantity, discount, code, qrCode, category);
            }
            case 4 -> {
                System.out.print("Nhập Xuất Xứ: ");
                String origin = scanner.nextLine();
                System.out.println("Chọn Mã Sản Phẩm: ");
                displayCode(codeLifeStyleArrayList);
                int number = scanner.nextInt();
                String fileName = "CodeLifeStyle";
                createCode(codeLifeStyleArrayList, number, scanner, fileName);
                String code = codeLifeStyleArrayList.get(number - 1).getCode();
                String categoryName = "LifeStyle";
                Category category = new LifeStyle(categoryName, origin);
                goods = new Goods(name, price, quantity, discount, code, qrCode, category);
            }
        }
        return goods;
    }

    public void addGoods(Scanner scanner) {
        Goods goods = createGoods(scanner);
        goodsArrayList.add(goods);
        scanner.nextLine();
        writeGoodsFile(goodsArrayList);
    }

    public void readCodeArrayListFMCG() {
        File file = new File("CodeFMCG");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] string = value.split(",");
                GoodsCode goodsCode = new GoodsCode(string[0], string[1]);
                codeFMCGArrayList.add(goodsCode);
            }
        } catch (Exception e) {
            System.out.println("Không Đọc Được File");
        }
    }

    public void readCodeArrayListELHA() {
        File file = new File("CodeELHA");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] string = value.split(",");
                GoodsCode goodsCode = new GoodsCode(string[0], string[1]);
                codeELHAArrayList.add(goodsCode);
            }
        } catch (Exception e) {
            System.out.println("Không Đọc Được File");
        }
    }

    public void readCodeArrayListFashion() {
        File file = new File("CodeFashion");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] string = value.split(",");
                GoodsCode goodsCode = new GoodsCode(string[0], string[1]);
                codeFashionArrayList.add(goodsCode);
            }
        } catch (Exception e) {
            System.out.println("Không Đọc Được File");
        }
    }

    public void readCodeArrayListLifeStyle() {
        File file = new File("CodeLifeStyle");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] string = value.split(",");
                GoodsCode goodsCode = new GoodsCode(string[0], string[1]);
                codeLifeStyleArrayList.add(goodsCode);
            }
        } catch (Exception e) {
            System.out.println("Không Đọc Được File");
        }
    }

    public void writeCodeArrayList(String filename, ArrayList<GoodsCode> arrayList) {
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (GoodsCode goodsCode : arrayList) {
                bufferedWriter.write(goodsCode.getCode() + "," + goodsCode.getContent() + "\n");
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Không Ghi Được File");
        }
    }

    public void displayCode(ArrayList<GoodsCode> arrayList) {
        int index = 1;
        for (GoodsCode code : arrayList) {
            System.out.println(index + ". " + code.getCode() + ": " + code.getContent());
            index++;
        }
        System.out.println((arrayList.size() + 1) + ". Thêm mã sản phẩm");
    }


    public void displayAllGoods() {
        readGoodsFile();
        System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá", "Mã Sản Phẩm", "Mã QRcode", "Thông tin ngành hàng");
        for (Goods goods : goodsArrayList) {
            System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n", goods.getName(), goods.getPrice(), goods.getQuantity(), goods.getDiscount(), goods.getCode(), goods.getQrcode(), goods.getCategory());
        }
    }

    public void displayGoods(Goods goods) {
        System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n", goods.getName(), goods.getPrice(), goods.getQuantity(), goods.getDiscount(), goods.getCode(), goods.getQrcode(), goods.getCategory());
    }

    public void createCode(ArrayList<GoodsCode> arrayList, int number, Scanner scanner, String fileName) {
        while (number == (arrayList.size() + 1)) {
            String code;
            do {
                System.out.print("Nhập Mã SP Cần Thêm: ");
                code = scanner.next();
            }
            while(checkCodeExist(arrayList,code));
            System.out.print("Nhập Diễn Giải: ");
            String explaint = scanner.next();
            arrayList.add(new GoodsCode(code, explaint));
            writeCodeArrayList(fileName, arrayList);
            displayCode(arrayList);
            System.out.println("Chọn Mã Sản Phẩm: ");
            number = Integer.parseInt(scanner.nextLine());
        }
    }

    public void editGoods(Scanner scanner) {
        System.out.print("Nhập Mã QRcode Của Sản Phẩm Cần Thay Đổi Thông Tin: ");
        scanner.nextLine();
        int qrCode = scanner.nextInt();
        int check = 0;
        for (Goods goods : goodsArrayList) {
            if (goods.getQrcode() == qrCode) {
                System.out.println(goods);
                System.out.print("Nhập Tên Sản Phẩm Muốn Thay Đổi: ");
                scanner.nextLine();
                String goodsName = scanner.nextLine();
                if (goodsName.equals("")) {
                    goodsName = goods.getName();
                }
                System.out.print("Nhập Giá Muốn Thay Đổi: ");
                String priceString = scanner.nextLine();
                double price;
                if (priceString.equals("")) {
                    price = goods.getPrice();
                } else {
                    price = Double.parseDouble(priceString);
                }
                System.out.print("Nhập Số Lượng Muốn Thay Đổi: ");
                int quantity;
                String quantityString = scanner.nextLine();
                if (quantityString.equals("")) {
                    quantity = goods.getQuantity();
                } else {
                    quantity = Integer.parseInt(quantityString);
                }
                goods.setName(goodsName);
                goods.setPrice(price);
                goods.setQuantity(quantity);
                check++;
                writeGoodsFile(goodsArrayList);
            }
        }
        if (check == 0) {
            System.out.println("Không tìm được Sản Phẩm có mã QRcode trên!!!");
        }
    }

    public void deleteGoodsByQRcode(Scanner scanner) {
        System.out.print("Nhập Mã QRcode Của Sản Phẩm Cần Xoá: ");
        int qrCode = scanner.nextInt();
        int check = 0;
        for (int i = 0; i < goodsArrayList.size(); i++) {
            if (goodsArrayList.get(i).getQrcode() == qrCode) {
                System.out.println("Bạn Có Muốn Xoá Không");
                System.out.println("1. Có");
                System.out.println("2. Không");
                scanner.nextLine();
                int choice = scanner.nextInt();
                if (choice == 1) {
                    goodsArrayList.remove(i);
                    i--;
                }
                check++;
                writeGoodsFile(goodsArrayList);
            }
        }
        if (check == 0) {
            System.out.println("Không tìm được Sản Phẩm có mã QRcode trên!!!");
        }
    }

    public void searchGoodsByQrCode(Scanner scanner) {
        System.out.print("Nhập Mã QRcode Của Sản Phẩm Cần Tìm: ");
        int qrCode = scanner.nextInt();
        int check = 0;
        for (Goods goods : goodsArrayList) {
            if (goods.getQrcode() == qrCode) {
                System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá", "Mã Sản Phẩm", "Mã QRcode", "Thông tin ngành hàng");
                displayGoods(goods);
                check++;
            }
        }
        if (check == 0) {
            System.out.println("Không tìm được Sản Phẩm có mã QRcode trên!!!");
        }
    }

    public void searchGoodsByCategory(Scanner scanner) {
        System.out.println("1. FMCG");
        System.out.println("2. ELHA");
        System.out.println("3. LifeStyle");
        System.out.println("4. Fashion");
        scanner.nextLine();
        int choice;
        String choiceString;
        do{
            System.out.print("Chọn Ngành Hàng: ");
            choiceString = scanner.nextLine();
        }
        while(checkChoiceCategory(choiceString));
        choice = Integer.parseInt(choiceString);
        switch (choice) {
            case 1 -> {
                System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá", "Mã Sản Phẩm", "Mã QRcode", "Thông tin ngành hàng");
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof FMCG) {
                        displayGoods(goods);
                    }
                }
            }
            case 2 -> {
                System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá", "Mã Sản Phẩm", "Mã QRcode", "Thông tin ngành hàng");
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof ELHA) {
                        displayGoods(goods);
                    }
                }
            }
            case 3 -> {
                System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá", "Mã Sản Phẩm", "Mã QRcode", "Thông tin ngành hàng");
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof LifeStyle) {
                        displayGoods(goods);
                    }
                }
            }
            case 4 -> {
                System.out.printf("%-20s%-20s%-15s%-15s%-15s%-15s%s\n\n", "Tên Sản Phẩm", "Giá", "Số Lượng", "Giảm Giá", "Mã Sản Phẩm", "Mã QRcode", "Thông tin ngành hàng");
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof Fashion) {
                        displayGoods(goods);
                    }
                }
            }
        }
    }

    public void setSaleByCategory(Scanner scanner) {
        System.out.println("1. FMCG");
        System.out.println("2. ELHA");
        System.out.println("3. LifeStyle");
        System.out.println("4. Fashion");
        scanner.nextLine();
        int choice;
        String choiceString;
        do{
            System.out.print("Chọn Ngành Hàng: ");
            choiceString = scanner.nextLine();
        }
        while(checkChoiceCategory(choiceString));
        choice = Integer.parseInt(choiceString);
        System.out.print("Nhập % Sale: ");
        double discount = scanner.nextDouble();
        switch (choice) {
            case 1 -> {
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof FMCG) {
                        setDiscountPrice(discount, goods);
                    }
                }
            }
            case 2 -> {
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof ELHA) {
                        setDiscountPrice(discount, goods);
                    }
                }
            }
            case 3 -> {
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof LifeStyle) {
                        setDiscountPrice(discount, goods);
                    }
                }
            }
            case 4 -> {
                for (Goods goods : goodsArrayList) {
                    if (goods.getCategory() instanceof Fashion) {
                        setDiscountPrice(discount, goods);
                    }
                }
            }
        }
        writeGoodsFile(goodsArrayList);
    }

    public void setDiscountPrice(double discount, Goods goods) {
        if (discount == 0) {
            goods.setPrice(goods.getPrice() * 100 / (100 - goods.getDiscount()));
            goods.setDiscount(0);
        }
        else{
            goods.setDiscount(discount);
            goods.setPrice(goods.getPrice() * (100 - discount) / 100);
        }
    }

    public void setSaleByCode(Scanner scanner) {
        int index = 1;
        ArrayList<GoodsCode> codeList = new ArrayList<>();
        codeList.addAll(codeFMCGArrayList);
        codeList.addAll(codeELHAArrayList);
        codeList.addAll(codeFashionArrayList);
        codeList.addAll(codeLifeStyleArrayList);
        System.out.printf("%-15s%-15s%s\n\n", "STT", "Mã Sản Phẩm", "Diễn Giải");
        for (GoodsCode goodsCode : codeList) {
            System.out.printf("%-15s%-15s%s\n", index, goodsCode.getCode(), goodsCode.getContent());
            index++;
        }
        System.out.print("Chọn Mã SP Muốn Giảm Giá: ");
        int choice = scanner.nextInt();
        System.out.print("Nhập % Sale: ");
        double discount = scanner.nextDouble();
        for (Goods goods : goodsArrayList) {
            if (goods.getCode().equals(codeList.get(choice - 1).getCode())) {
                setDiscountPrice(discount, goods);
            }
        }
        writeGoodsFile(goodsArrayList);
    }

    public void setSaleByQrCode(Scanner scanner){
        int qrCode;
        do {
            System.out.print("Nhập mã QRcode Muốn Giảm Giá: ");
            qrCode = scanner.nextInt();
        }
        while(checkQrCode(qrCode));
        System.out.print("Nhập % Sale: ");
        double discount = scanner.nextDouble();
        for (Goods goods:goodsArrayList) {
            if(goods.getQrcode()==qrCode){
                setDiscountPrice(discount, goods);
            }
        }
        writeGoodsFile(goodsArrayList);
    }
    public void readFile(){
        readCodeArrayListFMCG();
        readCodeArrayListELHA();
        readCodeArrayListFashion();
        readCodeArrayListLifeStyle();
    }

    public boolean checkNumber(String string){
        if(Pattern.matches("\\d+", string)){
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }

    public boolean checkChoiceCategory(String choice) {
        if (Pattern.matches("([0-4]){1}",choice)){
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }

    public void writeGoodsFile(ArrayList<Goods> arrayList){
        File file = new File("Goods");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        }
        catch (Exception e) {
            System.out.println("Không Ghi Được File!!!");
        }
    }

    public void readGoodsFile(){
        File file = new File("Goods");
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            goodsArrayList = (ArrayList<Goods>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch(Exception e){
            System.out.println("Không Đọc Được File!!!1");
        }
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

    public boolean checkQrCodeExist(int qrCode) {
        for (Goods goods : goodsArrayList) {
            if (goods.getQrcode() == qrCode) {
                System.out.println("Mã QrCode Đã Tồn Tại");
                return true;
            }
        }
        return false;
    }

    public boolean checkCodeExist(ArrayList<GoodsCode> arrayList,String code){
        for (GoodsCode goodsCode: arrayList) {
            if(goodsCode.getCode().equals(code)){
                System.out.println("Mã Sản Phẩm Đã Tồn Tại");
                return true;
            }
        }
        return false;
    }

    public void checkGoodsOutOfDate() {
        LocalDate localDate = LocalDate.now();
        for (Goods goods : goodsArrayList) {
            if (goods.getCategory() instanceof FMCG) {
                if (((FMCG) goods.getCategory()).getExpirationDate().getDayOfYear() - localDate.getDayOfYear() < 15) {
                    displayGoods(goods);
                }
            }
        }
    }
}
