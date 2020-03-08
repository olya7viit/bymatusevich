package by.matusevich.model.validator;

import by.matusevich.model.entity.OperationType;

import java.util.Objects;

public final class Validator {

    private Validator() {
    }

    public static boolean operationValidator(String login, String typeOperation, double sum) {
        boolean result = true;

        if (Objects.nonNull(login)){
            if(login.isEmpty()){
                result = false;
            }
        }
        if (sum < 0) {
            result = false;
        }
        if (typeOperation.equals(OperationType.EXPENSES) || typeOperation.equals(OperationType.INCOME)) {
            result = false;
        }
        return result;
    }

    public static boolean validatorNumOperation(int num){
        return num<1;
    }

    public static boolean validatorSum(double sum){
        return sum<0;
    }

}
