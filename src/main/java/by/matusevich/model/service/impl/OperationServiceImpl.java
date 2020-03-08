package by.matusevich.model.service.impl;

import by.matusevich.dao.OperationDAO;
import by.matusevich.dao.exception.DAOException;
import by.matusevich.dao.factory.DAOFactory;
import by.matusevich.model.entity.Operation;
import by.matusevich.model.entity.OperationType;
import by.matusevich.model.service.OperationService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.validator.Validator;

import java.util.List;
import java.util.Objects;

public class OperationServiceImpl implements OperationService {

    public static final String SUCCESSFULLY = "ok";

    @Override
    public void save(String login, String typeOperation, double sum) throws ServiceException {

        if (!Validator.operationValidator(login, typeOperation, sum)) {
            throw new ServiceException("Incorrect data");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            OperationDAO operationDAO = daoObjectFactory.getOperationDAO();
            Operation operation = new Operation(sum, typeOperation);
            operationDAO.save(login, operation);
        } catch (DAOException e) {
            throw new ServiceException("Errors during the registration procedure", e);
        }
    }

    @Override
    public String findAll(String login) throws ServiceException {
        String responce;
        if (Objects.isNull(login)|| login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            OperationDAO operationDAO = daoObjectFactory.getOperationDAO();
            List<Operation> operations = operationDAO.findAll(login);
            responce = SUCCESSFULLY + " " + operations.size() + " ";
            for (Operation operation : operations) {
                responce += operation.getSum() + " " + operation.getType() + " ";
            }
        } catch (DAOException e) {
            throw new ServiceException("Errors during the view procedure", e);
        }

        return responce;
    }

    @Override
    public void delete(String login, int num) throws ServiceException{
        if (Objects.isNull(login) || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        if (Validator.validatorNumOperation(num)){
            throw new ServiceException("Incorrect number of operation");
        }
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            OperationDAO operationDAO = daoObjectFactory.getOperationDAO();
            operationDAO.delete(login,num);
        } catch (DAOException e) {
            throw new ServiceException("Errors during the deletion procedure", e);
        }
    }

    @Override
    public void update(String login, int numOperation,double sum) throws ServiceException{
        if (Objects.isNull(login) || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        if (Validator.validatorNumOperation(numOperation)){
            throw new ServiceException("Incorrect number of operation");
        }
        if (Validator.validatorSum(sum)){
            throw new ServiceException("Incorrect sum operation");
        }
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            OperationDAO operationDAO = daoObjectFactory.getOperationDAO();
            operationDAO.update(login,numOperation,sum);
        } catch (DAOException e) {
            throw new ServiceException("Errors during the operation change procedure", e);
        }
    }

    @Override
    public String viewTotalProfitExpenses(String login) throws ServiceException{
        double profit=0,expenses=0;
        String response;

        if (Objects.isNull(login) || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            OperationDAO operationDAO = daoObjectFactory.getOperationDAO();
            List<Operation> operations = operationDAO.findAll(login);
            for (Operation operation : operations) {
                if (operation.getType().equals("" + OperationType.INCOME)) {
                    profit += operation.getSum();
                } else {
                    expenses += operation.getSum();
                }
            }
            response = SUCCESSFULLY + " "+profit+" "+expenses;

        } catch (DAOException e) {
            throw new ServiceException("Errors during the view procedure", e);
        }

        return response;
    }
}


