package by.matusevich.controller.command;

import by.matusevich.model.service.exception.ServiceException;

public interface Command {

    String execute(String request) throws ServiceException;
}

