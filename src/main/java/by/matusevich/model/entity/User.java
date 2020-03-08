package by.matusevich.model.entity;

import java.util.List;

public class User extends AbstractEntity {

    private String login;
    private String password;
    private String role;
    private int countOperation;
    private List<Operation> operations;

    public User() {
    }

    public User(String login, String password, String role, int countOperation, List<Operation> operations) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.countOperation = countOperation;
        this.operations = operations;operations.equals(new Object());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCountOperation() {
        return countOperation;
    }

    public void setCountOperation(int countOperation) {
        this.countOperation = countOperation;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        User user = (User) o;

        return countOperation == user.countOperation
                && (login == user.login || (login != null && login.equals(user.getLogin())))
                && (role == user.role || (role != null && role.equals(user.getRole())))
                && (password == user.password || (password != null && password .equals(user.getPassword())))
                && (operations == user.operations || (operations != null && operations.equals(user.getOperations())));
    }

    @Override
    public int hashCode() {
        return (int)(31 * countOperation +
                ((login == null) ? 0 : login.hashCode()) +
                ((password == null) ? 0 : password.hashCode()) +
                ((role == null) ? 0 : role.hashCode()) +
                ((operations == null) ? 0 : operations.hashCode())
        );
    }

    @Override
    public String toString() {
        return getClass() + "{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", countOperation=" + countOperation +
                ", operations=" + operations +
                '}';
    }
}
