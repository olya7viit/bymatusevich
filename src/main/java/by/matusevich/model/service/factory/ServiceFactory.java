package by.matusevich.model.service.factory;

import by.matusevich.model.service.OperationService;
import by.matusevich.model.service.UserService;
import by.matusevich.model.service.impl.OperationServiceImpl;
import by.matusevich.model.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();

    private final OperationService operationService = new OperationServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public OperationService getOperationService() {
        return operationService;
    }
}
