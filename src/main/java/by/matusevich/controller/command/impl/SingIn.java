package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandHelper;
import by.matusevich.model.service.UserService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class SingIn implements Command {



    @Override
    public String execute(String request) {

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String[] splitLine = request.split(CommandHelper.PARAM_DELIMITER);
        String role = splitLine[CommandHelper.ROLE_POSITION_SINGIN];
        String login = splitLine[CommandHelper.LOGIN__POSITION_SINGIN];
        String password = splitLine[CommandHelper.PASSWORD__POSITION_SINGIN];
        try {
            userService.singIn(login, password,role);
            response = "Welcome";
        } catch (ServiceException e) {
            response = "Error during login procedure";
        }
        return response;
    }
}

