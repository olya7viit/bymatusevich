package by.matusevich.controller;

import by.matusevich.controller.command.Command;
import by.matusevich.controller.command.CommandName;
import by.matusevich.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SingIn());
        repository.put(CommandName.REGISTRATION, new Register());
        repository.put(CommandName.ADD_OPERATION, new AddOperation());
        repository.put(CommandName.FIND_ALL_OPERATIONS, new FindAllOperation());
        repository.put(CommandName.DEL_OPERATION, new DelOperation());
        repository.put(CommandName.CHANGE_OPERATION, new ChangeOperation());
        repository.put(CommandName.FIND_ALL_USERS, new FindAllUsers());
        repository.put(CommandName.DEL_USER, new DelUser());
        repository.put(CommandName.VIEW_TOTAL_PROFIT_EXPENSES, new View_total_profit_expenses());
    }

    Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
//write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
