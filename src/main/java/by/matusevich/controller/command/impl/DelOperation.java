package by.matusevich.controller.command.impl;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandHelper;
import by.matusevich.model.service.OperationService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.service.factory.ServiceFactory;

public class DelOperation implements Command {

    @Override
    public String execute(String request) {
        int numOperation;
        String[] splitLine;
        String response,login;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OperationService operationService = serviceFactory.getOperationService();

        splitLine = request.split(CommandHelper.PARAM_DELIMITER);
        login = splitLine[CommandHelper.LOGIN_POSITION];
        numOperation = Integer.parseInt(splitLine[CommandHelper.NUM_POSITION]);

        try {
            operationService.delete(login,numOperation);
            response = "Successfully";
        } catch (ServiceException e) {
            response = "Errors during the removal procedure of the operation";
        }
        return response;
    }
}
