package SuperMarket.Menu;

import java.util.Scanner;

public class MenuMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuAccount menuAccount = new MenuAccount();
        menuAccount.menu(scanner);
    }
}
