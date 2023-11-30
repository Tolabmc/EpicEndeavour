package src;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        src.IDandPasswords idandPasswords = new IDandPasswords();
        LoginPage loginPage;
        loginPage = new LoginPage(idandPasswords.getLoginInfo());
    }
}
