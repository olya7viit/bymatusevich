package by.matusevich.controller;

import by.matusevich.controller.command.Command;
import by.matusevich.model.service.exception.ServiceException;

public final class Controller {

    private final CommandProvider provider = new CommandProvider();

    private static final String PARAM_DELIMITER = " ";

    private static final int COMMAND_NAME_POSITION = 0;

    public String executeTask(String request) throws ServiceException {
        String commandName;
        Command executionCommand;
        commandName = request.substring(COMMAND_NAME_POSITION, request.indexOf(PARAM_DELIMITER));
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }
}


