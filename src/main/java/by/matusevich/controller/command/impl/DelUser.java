package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandHelper;
import by.matusevich.model.service.UserService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class DelUser implements Command {

    @Override
    public String execute(String request) {
        int num;
        String[] splitLine;
        String response,login;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        splitLine = request.split(CommandHelper.PARAM_DELIMITER);
        login = splitLine[CommandHelper.LOGIN_POSITION];
        num = Integer.parseInt(splitLine[CommandHelper.NUM_POSITION]);

        try {
            userService.delete(login,num);
            response = "Successfully";
        } catch (ServiceException e) {
            response = "Errors during the removal procedure of the operation";
        }
        return response;
    }
}

