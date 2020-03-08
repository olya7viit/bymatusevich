package by.matusevich.model.service;

import by.matusevich.model.service.exception.ServiceException;

public interface UserService {

    void singIn(String login, String password, String role) throws ServiceException;

    String findAll() throws ServiceException;

    void signUp(String login, String password) throws ServiceException;

    void delete(String login, int numOperation) throws ServiceException;
}


