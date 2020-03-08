package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.model.service.UserService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class FindAllUsers implements Command {
    @Override
    public String execute(String request) {
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            response = userService.findAll();
        } catch (ServiceException e) {
            response = "Errors during the procedure for adding an operation";
        }
        return response;
    }
}
