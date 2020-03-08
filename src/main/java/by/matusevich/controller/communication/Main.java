package by.matusevich.controller.communication;

import by.matusevich.controller.Controller;
import by.matusevich.controller.ControllerHelper;
import by.matusevich.model.entity.RoleName;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.view.Printer;

public class Main {

    private static final String PARAM_DELIMITER = " ";

    private static final int LOGIN_POSITION = 0;

    private static final int PASSWORD_POSITION = 1;

    private static final int OUTPUT_SELECTION = 4;

    public static void main(String[] args) throws ServiceException {

        String response,login,password;

        int choise;

        String[] splitLine;

        Menu menu = new Menu();

        Controller controller = new Controller();

        PersonAction personAction;

        while (true){
            choise = menu.mainMenu();
            if(choise==OUTPUT_SELECTION){
                break;
            }
            switch (choise) {
                case ControllerHelper.SINGIN_ADMIN:
                    personAction = new AdminAction();
                    splitLine = personAction.enteringAuthorization().split(PARAM_DELIMITER);
                    login = splitLine[LOGIN_POSITION];
                    password = splitLine[PASSWORD_POSITION];

                    if(personAction.isLoggedIn(controller,login,password, RoleName.ADMIN)){
                        personAction.action(login);
                    }
                    break;
                case ControllerHelper.SINGIN_USER:
                    personAction = new UserAction();
                    splitLine = personAction.enteringAuthorization().split(PARAM_DELIMITER);
                    login = splitLine[LOGIN_POSITION];
                    password = splitLine[PASSWORD_POSITION];
                    if(personAction.isLoggedIn(controller, login,password,RoleName.USER)){
                        personAction.action(login);
                    }
                    break;
                case ControllerHelper.SINGUP:
                    personAction = new UserAction();
                    splitLine = personAction.enteringAuthorization().split(PARAM_DELIMITER);
                    login = splitLine[LOGIN_POSITION];
                    password = splitLine[PASSWORD_POSITION];
                    response = controller.executeTask("registration " + login + " " + password);
                    Printer.print(response);
                    break;
            }

        }
    }
}
