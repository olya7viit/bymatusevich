package by.matusevich.dao;

import by.matusevich.dao.exception.DAOException;
import by.matusevich.model.entity.Operation;

import java.util.List;

public interface OperationDAO {
    void save(String login, Operation operation) throws DAOException;

    List<Operation> findAll(String login) throws DAOException;

    void delete(String login, int num) throws DAOException;

    void update(String login, int num, double sum) throws DAOException;
}

