package by.matusevich.dao.factory;

import by.matusevich.dao.OperationDAO;
import by.matusevich.dao.UserDAO;
import by.matusevich.dao.impl.FileOperationDAO;
import by.matusevich.dao.impl.FileUserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final OperationDAO fileBookImpl = new FileOperationDAO();
    private final UserDAO fileUserImpl = new FileUserDAO();
    private DAOFactory(){}
    public static DAOFactory getInstance(){
        return instance;
    }
    public OperationDAO getOperationDAO(){return fileBookImpl;}
    public UserDAO getUserDAO(){
        return fileUserImpl;
    }
}
