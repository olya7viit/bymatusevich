package by.matusevich.controller.communication;

import by.matusevich.controller.Controller;
import by.matusevich.controller.ControllerHelper;
import by.matusevich.controller.util.UtilClasses;
import by.matusevich.controller.scanner.DataScanner;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.view.Printer;

public class AdminAction extends PersonAction {

    String responce;

    Controller controller;

    String [] splitLine;

    public void action(String login) throws ServiceException {

        int choice;

        controller = new Controller();

        Menu menu = new Menu();

        while (true) {

            choice = menu.adminMenu();

            if(choice == ControllerHelper.EXIT_ADMIN) {
                break;
            }

            switch (choice){
                case ControllerHelper.FIND_ALL_USERS:
                    responce = controller.executeTask("find_all_users ");
                    splitLine = responce.split(ControllerHelper.PARAM_DELIMITER);
                    UtilClasses.viewUsers(responce,splitLine);
                    break;
                case ControllerHelper.DEL_USER:
                    del(login);
                    break;
            }
        }

    }

    private void del(String login) throws ServiceException {
        responce = controller.executeTask("find_all_users ");
        splitLine = responce.split(ControllerHelper.PARAM_DELIMITER);
        UtilClasses.viewUsers(responce,splitLine);
        Printer.print("Введите номер операции для удаления: ");
        int numUser = DataScanner.enterIntFromConcol();
        Printer.print("");
        responce = controller.executeTask("del_user " + login + " "+numUser);
        Printer.print(responce);
    }

}
