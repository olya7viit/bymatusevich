package by.matusevich.dao.impl;

import by.matusevich.dao.UserDAO;
import by.matusevich.dao.exception.DAOException;
import by.matusevich.dao.util.UtilDao;
import by.matusevich.model.entity.Operation;
import by.matusevich.model.entity.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUserDAO implements UserDAO {

    private final static String FILE_NAME = "src/main/resources/userBase.txt";

    @Override
    public void signIn(String login, String password, String role) throws DAOException {
        boolean result = false;
        List<User> users = UtilDao.reedUsers(FILE_NAME);
        for (User user : users) {
            if (user.getLogin().equals(login) &&
                    user.getPassword().equals(password) &&
                    user.getRole().equals(role)) {
                result = true;
            }
        }
        if (!result) {
            throw new DAOException("Incorrect login");
        }
    }

    @Override
    public void signUp(User user) throws DAOException {

        if (!UtilDao.loginExists(FILE_NAME, user.getLogin())) {

            try {
                File file = new File(FILE_NAME);
                //file.createNewFile();
                FileWriter writer = new FileWriter(file, true);

                writer.write(user.getRole() + " ");
                writer.write(user.getLogin() + " ");
                writer.write(user.getPassword() + "\n");
                writer.write(user.getCountOperation() + "\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new DAOException("Errors during the registration procedure", e);
            }
        } else {
           throw new DAOException("Incorrect login");
        }
    }

    @Override
    public List<User> findAll() throws DAOException {
        return UtilDao.reedUsers(FILE_NAME);
    }

    @Override
    public void delete(String login, int num) throws DAOException {

        List<User> users = UtilDao.reedUsers(FILE_NAME);

        List<Operation> operationsThisUser = UtilDao.reedOperations(FILE_NAME, login);

        if (num > users.size()) {
            throw new DAOException("Invalid operation number");
        }

        if(users.get(num-1).getLogin().equals(login)){
            throw new DAOException("Errors during the deletion procedure");
        }

        users.remove(num - 1);

        UtilDao.overwritingOperations(users, operationsThisUser, login);
        UtilDao.writeAllUsers(FILE_NAME, users);
    }
}

