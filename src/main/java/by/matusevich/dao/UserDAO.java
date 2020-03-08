package by.matusevich.dao;

import by.matusevich.dao.exception.DAOException;
import by.matusevich.model.entity.User;
import java.util.List;

public interface UserDAO {
    void signIn(String login, String password, String role) throws DAOException;
    void signUp(User user) throws DAOException;
    List<User> findAll() throws DAOException;
    void delete(String login, int num) throws DAOException;
}
