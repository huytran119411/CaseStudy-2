package SuperMarket.Menu;

import SuperMarket.Account.AccountManagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuAccount {

    public void menu(Scanner scanner) {
        AccountManagement accountManagement = new AccountManagement();
        accountManagement.readAccountFile();
        accountManagement.createAdmin();
        int choice;
        String choiceString;
        do {

                System.out.println("----------Menu----------");
                System.out.println("1. Đăng Nhập");
                System.out.println("2. Đăng Ký");
                System.out.println("3. Thoát");
                do { System.out.print("Nhập Lựa Chọn: ");
                choiceString = scanner.next();
                }
            while(checkChoice(choiceString));
            choice=Integer.parseInt(choiceString);
            switch (choice) {
                case 1 -> accountManagement.login(scanner);
                case 2 -> accountManagement.registerAccount(scanner);
            }
        }
            while (choice!=3);
        }

    public boolean checkChoice(String choice) {
        if (Pattern.matches("([0-3]){1}",choice)){
            return false;
        }
        System.out.println("Dữ Liệu Nhập Vào Bị Sai Vui Lòng Nhập Lại");
        return true;
    }
}
