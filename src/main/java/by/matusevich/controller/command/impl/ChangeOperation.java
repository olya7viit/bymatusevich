package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandHelper;
import by.matusevich.model.service.OperationService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class ChangeOperation implements Command {

    @Override
    public String execute(String request) {

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationService operationService = serviceFactory.getOperationService();

        String[] splitLine = request.split(CommandHelper.PARAM_DELIMITER);
        String login = splitLine[CommandHelper.LOGIN_POSITION];
        int numOperation = Integer.parseInt(splitLine[CommandHelper.TYPE_POSITION]);
        double sum = Double.parseDouble(splitLine[CommandHelper.SUM_POSITION]);

        try {
            operationService.update(login,numOperation,sum);
            response = "Successfully";
        } catch (ServiceException e) {
            response = "Errors during the operation change procedure";
        }
        return response;
    }
}
