package by.matusevich.dao.util;

import by.matusevich.dao.exception.DAOException;
import by.matusevich.model.entity.Operation;
import by.matusevich.model.entity.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UtilDao {

    private final static String PARAM_DELIMETER = " ";

    private static final int LOGIN_POSITION = 1;

    public static List<User> reedUsers(String fileName) throws DAOException {
        String login, password, userRole;
        String[] stringToSeparate;
        int countOperation;

        File file = new File(fileName);
        FileReader fr = null;
        User user;
        List<Operation> operations = new ArrayList<Operation>();
        List<User> users = new ArrayList<User>();

        try {
            fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                stringToSeparate = scan.nextLine().split(PARAM_DELIMETER);
                userRole = stringToSeparate[0];
                login = stringToSeparate[1];
                password = stringToSeparate[2];

                countOperation = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < countOperation; i++) {
                    stringToSeparate = scan.nextLine().split(PARAM_DELIMETER);
                    operations.add(new Operation(Double.parseDouble(stringToSeparate[0]), stringToSeparate[1]));
                }


                user = new User(login, password, userRole, countOperation, operations);
                users.add(user);
                operations = new ArrayList<>();
            }
        } catch (IOException e) {
            throw new DAOException("Errors during the registration procedure", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DAOException("Errors during the registration procedure", e);
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                throw new DAOException("Errors during the registration procedure", e);
            }
            return users;
        }
    }

    public static List<Operation> reedOperations(String fileName,String login) throws DAOException {
        FileReader fr = null;
        boolean flag = false;
        String currentLogin;
        String[] stringToSeparate;
        int countOperation;
        File file = new File(fileName);
        List<Operation> operations = new ArrayList<Operation>();

        try {
            fr = new FileReader(file);
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                stringToSeparate = scan.nextLine().split(PARAM_DELIMETER);
                currentLogin = stringToSeparate[1];

                countOperation = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < countOperation; i++) {
                    stringToSeparate = scan.nextLine().split(PARAM_DELIMETER);
                    if (login.equals(currentLogin)) {
                        operations.add(new Operation(Double.parseDouble(stringToSeparate[0]), stringToSeparate[1]));
                        flag = true;
                    }
                }
                if (flag) break;
            }
        } catch (IOException e) {
            throw new DAOException(e);
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                throw new DAOException(e);
            }
        }
        return operations;
    }

    public static void writeAllUsers(String fileName, List<User> users)throws DAOException {
        double sumOperation;
        String typeOperation;
        File file = new File(fileName);
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            for (User user : users) {
                writer.write(user.getRole() + " ");
                writer.write(user.getLogin() + " ");
                writer.write(user.getPassword() + "\n");
                writer.write(user.getCountOperation() + "\n");
                for (int j = 0; j < user.getCountOperation(); j++) {
                    sumOperation = user.getOperations().get(j).getSum();
                    writer.write(sumOperation + " ");
                    typeOperation = user.getOperations().get(j).getType();
                    writer.write(typeOperation + "\n");
                }
            }

        } catch (IOException e) {
            throw new DAOException("Errors during the procedure for adding an operation", e);
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new DAOException(e);
            }
        }
    }

    public static List<User> overwritingOperations(List<User> users,List<Operation> operationsThisUser,String login){
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                user.setOperations(operationsThisUser);
                user.setCountOperation(operationsThisUser.size());
            }
        }
        return users;
    }

    public static boolean loginExists(String fileName, String login) throws DAOException {

        String[] stringToSeparate;

        int countOperation;

        boolean result = false;

        File file = new File(fileName);
        FileReader fr = null;

        try {
            file.createNewFile();
            fr = new FileReader(file);
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {

                stringToSeparate = scan.nextLine().split(PARAM_DELIMETER);

                if(stringToSeparate[LOGIN_POSITION].equals(login)){
                    result= true;
                }
                countOperation = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < countOperation; i++) {
                    scan.nextLine();
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new DAOException("Errors during the registration procedure",e);
        }
        finally {
            try {
                fr.close();
            } catch (IOException e) {
                throw new DAOException(e);
            }
        }
        return result;
    }
}
