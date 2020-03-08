package by.matusevich.dao.impl;

import by.matusevich.dao.OperationDAO;
import by.matusevich.dao.exception.DAOException;
import by.matusevich.dao.util.UtilDao;
import by.matusevich.model.entity.Operation;
import by.matusevich.model.entity.User;

import java.util.List;

public class FileOperationDAO implements OperationDAO {

    private final static String FILE_NAME = "src/main/resources/userBase.txt";

    @Override
    public void save(String login, Operation operation) throws DAOException {
        List<User> users = UtilDao.reedUsers(FILE_NAME);
        List<Operation> operationsThisUser = UtilDao.reedOperations(FILE_NAME,login);

        operationsThisUser.add(operation);
        UtilDao.overwritingOperations(users, operationsThisUser, login);
        UtilDao.writeAllUsers(FILE_NAME,users);
    }

    @Override
    public void delete(String login, int num) throws DAOException {
        List<User> users = UtilDao.reedUsers(FILE_NAME);
        List<Operation> operationsThisUser = UtilDao.reedOperations(FILE_NAME, login);
        if (num > operationsThisUser.size()) {
            throw new DAOException("Invalid operation number");
        }
        operationsThisUser.remove(num - 1);
        UtilDao.overwritingOperations(users, operationsThisUser, login);
        UtilDao.writeAllUsers(FILE_NAME, users);
    }

    @Override
    public void update(String login, int num,double sum) throws DAOException {
        List<User> users = UtilDao.reedUsers(FILE_NAME);
        List<Operation> operationsThisUser = UtilDao.reedOperations(FILE_NAME,login);
        if (num > operationsThisUser.size()) {
            throw new DAOException("Invalid operation number");
        }
        operationsThisUser.get(num-1).setSum(sum);
        UtilDao.overwritingOperations(users, operationsThisUser, login);
        UtilDao.writeAllUsers(FILE_NAME,users);
    }

    @Override
    public List<Operation> findAll(String login) throws DAOException {
        return UtilDao.reedOperations(FILE_NAME,login);
    }
}


