package SuperMarket.Account;

import SuperMarket.Menu.MenuGoodsManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {
    public ArrayList<Account> arrayList = new ArrayList<>();
    public ArrayList<AccountAdmin> adminArrayList = new ArrayList<>();

    public void login (Scanner scanner) {
        MenuGoodsManagement menuGoodsManagement = new MenuGoodsManagement();
        int check=0;
        for (int i = 1; i <= 3; i++) {
                System.out.print("Nhập Tên Người Dùng: ");
                String userName = scanner.next();
            System.out.print("Nhập Mật Khẩu: ");
            String passWord = scanner.next();
            String staffName = null;
            for (Account accounts : arrayList) {
                if (accounts.getUsername().equals(userName) && accounts.getPassword().equals(passWord)) {
                    staffName = accounts.getName();
                }
            }
            for (AccountAdmin accountAdmins : adminArrayList) {
                if (accountAdmins.getPassword().equals(passWord) && accountAdmins.getUsername().equals(userName)) {
                    staffName = accountAdmins.getName();
                    }
                }
            if(checkAccount(userName,passWord)) {
                menuGoodsManagement.menuStaff(scanner,staffName);
                break;
            }
            if(checkAccountAdmin(userName,passWord)){
                menuGoodsManagement.menuAdmin(scanner,staffName);
                break;
            }
            System.out.println("Tài Khoản Hoặc Mật Khẩu Không Đúng!!!");
            check++;
        }
        if(check==3){
        System.out.println("Hãy Liên Hệ Admin Để Được Cấp Lại Tài Khoản!!!");
        }
    }

    public boolean checkAccount(String userName, String passWord) {
        for (Account accounts : arrayList) {
            if (accounts.getUsername().equals(userName) && accounts.getPassword().equals(passWord)) {
                System.out.println("-----Đăng Nhập Tài Khoản Thành Công-----");
                return true;
            }
        }
        return false;
    }

    public boolean checkAccountAdmin(String userName, String passWord) {
        for (AccountAdmin accountAdmins : adminArrayList) {
            if (accountAdmins.getPassword().equals(passWord) && accountAdmins.getUsername().equals(userName)) {
                System.out.println("-----Đăng Nhập Tài Khoản Admin Thành Công-----");
                return true;
            }
        }
        return false;
    }

    public void createAdmin(){
        adminArrayList.add(new AccountAdmin("admin","admin","Tran Trong Huy"));
    }

    public void registerAccount(Scanner scanner){
        String userName;
        do {
            System.out.print("Nhập Tên Người Dùng: ");
            userName = scanner.next();
        }
        while(checkIdAccount(userName));
        System.out.print("Nhập Mật Khẩu: ");
        scanner.nextLine();
        String passWord = scanner.nextLine();
        System.out.print("Nhập Họ Tên Nhân Viên: ");
        String name = scanner.nextLine();
        Account account = new Account(userName,passWord,name);
        arrayList.add(account);
        writeAccountFile(arrayList);
        System.out.println("Bạn Đã Đăng Ký Thành Công!!!");
    }

    public void displayAccount(){
        for (Account account : arrayList) {
            System.out.println(account);
        }
    }

    public void writeAccountFile(ArrayList<Account> arrayList){
        File file = new File("Account");
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

    public void readAccountFile(){
        File file = new File("Account");
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            arrayList = (ArrayList<Account>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch(Exception e){
            System.out.println("Không Đọc Được File!!!");
        }
    }

    public boolean checkIdAccount(String id){
        for (Account acount :arrayList) {
            if(acount.getUsername().equals(id)){
                System.out.println("Tên Đăng Nhập Đã Tồn Tại Vui Lòng Nhập Lại!!!");
                return true;
            }
        }
        for(AccountAdmin accountAdmin :adminArrayList) {
            if (accountAdmin.getUsername().equals(id)) {
                System.out.println("Tên Đăng Nhập Đã Tồn Tại Vui Lòng Nhập Lại!!!");
                return true;
            }
        }
        return false;
    }
}

