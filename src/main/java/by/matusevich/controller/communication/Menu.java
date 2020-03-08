package by.matusevich.controller.communication;

import by.matusevich.controller.scanner.DataScanner;
import by.matusevich.view.Printer;

public class Menu {

    private final static int numberMainMenu = 4;
    private final static int numberAdminMenu = 3;
    private final static int numberUserMenu = 6;
    private final static int numberTypeOperationMenu = 3;

    private int choice;

    public int mainMenu() {
        do {
            Printer.print("1 - Войти как администратор");
            Printer.print("2 - Войти как пользователь");
            Printer.print("3 - Зарегистрироваться");
            Printer.print("4 - Выйти");
            choice = DataScanner.enterIntFromConcol();
        } while (choice < 1 || choice > numberMainMenu);
        return choice;
    }

    public int adminMenu(){
        do {
            Printer.print("1 - Посмотреть пользователей");
            Printer.print("2 - Удалить пользователя");
            Printer.print("3 - Выйти");
            choice = DataScanner.enterIntFromConcol();
        } while (choice < 1 || choice > numberAdminMenu);
        return choice;
    }

    public int userMenu(){
        do {
            Printer.print("1 - Добавить операцию");
            Printer.print("2 - Изменить операцию");
            Printer.print("3 - Удалить операцию");
            Printer.print("4 - Посмотреть операции");
            Printer.print("5 - Посмотреть общую прибыль и расходы");
            Printer.print("6 - Выйти");
            choice = DataScanner.enterIntFromConcol();
        } while (choice < 1 || choice > numberUserMenu);
        return choice;
    }

    public int typeOperationMenu(){
        do {
            System.out.println("Выберите тип операции:");
            System.out.println("1 - Доход");
            System.out.println("2 - Расход");
            choice = DataScanner.enterIntFromConcol();
        } while (!(choice > 0 && choice < numberTypeOperationMenu));
        return choice;
    }
}
