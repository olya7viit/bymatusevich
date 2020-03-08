package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandHelper;
import by.matusevich.model.service.OperationService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class FindAllOperation implements Command {

    @Override
    public String execute(String request) {
        String[] splitLine;
        String response,login;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationService operationService = serviceFactory.getOperationService();

        splitLine = request.split(CommandHelper.PARAM_DELIMITER);
        login = splitLine[CommandHelper.LOGIN_POSITION];

        try {
            response = operationService.findAll(login);
        } catch (ServiceException e) {
            response = "Errors during the procedure for adding an operation";
        }
        return response;
    }
}
