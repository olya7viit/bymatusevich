package by.matusevich.model.service;


import by.matusevich.model.service.exception.ServiceException;

public interface OperationService {

    void save(String login, String typeOperation, double sum) throws ServiceException;

    void delete(String login, int numOperation) throws ServiceException;

    void update(String login, int numOperation, double sum) throws ServiceException;

    String findAll(String login) throws ServiceException;

    String viewTotalProfitExpenses(String login) throws ServiceException;//????
}

