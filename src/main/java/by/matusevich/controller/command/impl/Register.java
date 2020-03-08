package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandHelper;
import by.matusevich.model.service.UserService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class Register implements Command {

    @Override
    public String execute(String request) {

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String[] splitLine = request.split(CommandHelper.PARAM_DELIMITER);

        String login = splitLine[CommandHelper.LOGIN_POSITION];
        String password = splitLine[CommandHelper.PASSWORD_POSITION];

        try {
            userService.signUp(login, password);
            response = "Successfully";
        } catch (ServiceException e) {
            response = "Errors during the registration procedure";
        }
        return response;
    }
}

