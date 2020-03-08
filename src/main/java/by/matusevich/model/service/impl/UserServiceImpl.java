package by.matusevich.model.service.impl;

import by.matusevich.dao.UserDAO;
import by.matusevich.dao.exception.DAOException;
import by.matusevich.dao.factory.DAOFactory;
import by.matusevich.model.entity.RoleName;
import by.matusevich.model.entity.User;
import by.matusevich.model.service.UserService;
import by.matusevich.model.service.exception.ServiceException;
import by.matusevich.model.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    public static final String SUCCESSFULLY = "ok";

    @Override
    public void singIn(String login, String password,String role) throws ServiceException {

        if (Objects.isNull(login) || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signIn(login, password,role);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public String findAll() throws ServiceException {
        String responce;
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            List<User> users = userDAO.findAll();
            responce = SUCCESSFULLY + " " + users.size() + " ";
            for (User user : users) {
                responce += user.getRole() + " " + user.getLogin() + " ";
            }
        } catch (DAOException e) {
            throw new ServiceException("Errors during the view procedure", e);
        }
        return responce;
    }

    @Override
    public void signUp(String login, String password) throws ServiceException {

        if (Objects.isNull(login) || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            User user = new User(login,password, ""+RoleName.USER,0, new ArrayList<>());
            userDAO.signUp(user);
        } catch (DAOException e) {
            throw new ServiceException("Errors during the registration procedure",e);
        }
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
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.delete(login,num);
        } catch (DAOException e) {
            throw new ServiceException("Errors during the deletion procedure", e);
        }
    }

}
