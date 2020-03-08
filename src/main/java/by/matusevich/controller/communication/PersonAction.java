package by.matusevich.controller.communication;

import by.matusevich.controller.Controller;
import by.matusevich.controller.scanner.DataScanner;
import by.matusevich.model.entity.RoleName;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.view.Printer;

public abstract class PersonAction {

    public abstract void action(String login) throws ServiceException;

    public boolean  isLoggedIn(Controller controller,String login,String password,RoleName roleName)throws ServiceException {

        boolean result = false;

        String response;

        response = controller.executeTask("sign_in " + roleName + " " + login + " " + password);
        Printer.print(response);
        if(response.equals("Welcome")){
            result = true;
        }
        return result;
    }

    public String enteringAuthorization(){
        Printer.print("Введите логин: ");
        String login = DataScanner.enterStringFromConcol();
        Printer.print("Введите пароль: ");
        String password = DataScanner.enterStringFromConcol();
        return login + " " + password;
    }
}
