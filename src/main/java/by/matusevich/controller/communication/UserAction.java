package by.matusevich.controller.communication;

import by.matusevich.controller.Controller;
import by.matusevich.controller.ControllerHelper;
import by.matusevich.controller.util.UtilClasses;
import by.matusevich.controller.scanner.DataScanner;
import by.matusevich.model.entity.OperationType;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.view.Printer;

public class UserAction extends PersonAction {

    String responce,typeOperation;

    Menu menu;

    Controller controller;

    @Override
    public void action(String login) throws ServiceException {

        int choice;

        controller = new Controller();

        menu = new Menu();

        while (true) {

            choice = menu.userMenu();

            if (choice == ControllerHelper.EXIT_USER) {
                break;
            }

            switch (choice) {
                case ControllerHelper.ADD_NEW:
                    add(login);
                    break;
                case ControllerHelper.UPDATE:
                    update(login);
                    break;
                case ControllerHelper.DEL:
                    del(login);
                    break;
                case ControllerHelper.FIND_ALL:
                    findAll(login);
                    break;
                case ControllerHelper.FIND_INF:
                    findInf(login);
                    break;
            }
        }
    }

    private void add(String login) throws ServiceException {
        double sum;
        typeOperation = getTypeOperation(menu.typeOperationMenu());
        Printer.print("Введите сумму: ");
        sum = DataScanner.enterDoubleFromConcol();

        responce = controller.executeTask("add_operation " + login +
                " " + typeOperation + " " + sum);
        Printer.print(responce);
    }

    private void update(String login) throws ServiceException {

        double sum;
        String[] splitLine;

        responce = controller.executeTask("find_all_operations " + login);
        splitLine = responce.split(ControllerHelper.PARAM_DELIMITER);

        if(splitLine[ControllerHelper.OK_POSITION].equals(ControllerHelper.SUCCESSFULLY)){

            UtilClasses.viewOperations(splitLine);

            Printer.print("Введите номер операции для изменения: ");
            int num = DataScanner.enterIntFromConcol();
            Printer.print("");

            Printer.print("Введите сумму: ");
            sum = DataScanner.enterDoubleFromConcol();
            Printer.print("");

            responce = controller.executeTask("change_operation " + login + " "+num + " "+sum);
        }else Printer.print(responce);
    }

    private void del(String login)throws ServiceException{

        String[] splitLine;

        responce = controller.executeTask("find_all_operations " + login);
        splitLine = responce.split(" ");
        if(splitLine[ControllerHelper.OK_POSITION].equals(ControllerHelper.SUCCESSFULLY)){
            UtilClasses.viewOperations(splitLine);
            Printer.print("Введите номер операции для удаления: ");
            int num = DataScanner.enterIntFromConcol();
            Printer.print("");
            responce = controller.executeTask("del_operation " + login + " "+num);
            Printer.print(responce);
        }else Printer.print(responce);
    }

    private void findAll(String login)throws ServiceException{

        String[] splitLine;

        responce = controller.executeTask("find_all_operations " + login);
        splitLine = responce.split(ControllerHelper.PARAM_DELIMITER);
        if(splitLine[ControllerHelper.OK_POSITION].equals(ControllerHelper.SUCCESSFULLY)){
            UtilClasses.viewOperations(splitLine);
        }else Printer.print(responce);
    }

    private void findInf(String login)throws ServiceException{

        String[] splitLine;

        responce = controller.executeTask("view_total_profit_expenses " + login);
        splitLine = responce.split(ControllerHelper.PARAM_DELIMITER);
        if(splitLine[ControllerHelper.OK_POSITION].equals(ControllerHelper.SUCCESSFULLY)){
            Printer.print("Общий доход: " + splitLine[ControllerHelper.INCOME_POSITION]);
            Printer.print("Общае расходы: " + splitLine[ControllerHelper.EXPENSES_POSITION]);
        }else Printer.print(responce);
    }

    private static String getTypeOperation(int type) {
        String typeOperation;
        if (type == ControllerHelper.NUM_INCOME_TYPE) {
            typeOperation = "" + OperationType.INCOME;
        } else {
            typeOperation = "" + OperationType.EXPENSES;
        }
        return typeOperation;
    }
}
