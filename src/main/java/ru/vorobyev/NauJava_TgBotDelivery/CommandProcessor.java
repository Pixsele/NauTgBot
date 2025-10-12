package ru.vorobyev.NauJava_TgBotDelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;
import ru.vorobyev.NauJava_TgBotDelivery.service.UserServiceImp;

@Component
public class CommandProcessor {

    private final UserServiceImp userServiceImp;

    @Autowired
    public CommandProcessor(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    public void processCommand(String command) {
        String[] tokens = command.split(" ");
        if (tokens.length == 0) return;

        String cmd = tokens[0];

        switch (cmd) {
            case "create" -> {
                if (tokens.length != 5) {
                    System.out.println("Error: 'create' requires 4 arguments: telegramId username name lastname");
                    break;
                }
                try {
                    Long telegramId = Long.valueOf(tokens[1]);
                    userServiceImp.createUser(telegramId, tokens[2], tokens[3], tokens[4]);
                    System.out.println("User successfully created");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "updateUsername" -> {
                if (tokens.length != 3) {
                    System.out.println("Error: 'updateUsername' requires 2 arguments: telegramId newUsername");
                    break;
                }
                try {
                    Long telegramId = Long.valueOf(tokens[1]);
                    userServiceImp.updateUsername(telegramId, tokens[2]);
                    System.out.println("Username successfully updated");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "updateName" -> {
                if (tokens.length != 3) {
                    System.out.println("Error: 'updateName' requires 2 arguments: telegramId newName");
                    break;
                }
                try {
                    Long telegramId = Long.valueOf(tokens[1]);
                    userServiceImp.updateName(telegramId, tokens[2]);
                    System.out.println("Name successfully updated");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "updateLastname" -> {
                if (tokens.length != 3) {
                    System.out.println("Error: 'updateLastname' requires 2 arguments: telegramId newLastname");
                    break;
                }
                try {
                    Long telegramId = Long.valueOf(tokens[1]);
                    userServiceImp.updateLastname(telegramId, tokens[2]);
                    System.out.println("Lastname successfully updated");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "delete" -> {
                if (tokens.length != 2) {
                    System.out.println("Error: 'delete' requires 1 argument: telegramId");
                    break;
                }
                try {
                    Long telegramId = Long.valueOf(tokens[1]);
                    userServiceImp.deleteUserById(telegramId);
                    System.out.println("User deleted");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "read" -> {
                if (tokens.length != 2) {
                    System.out.println("Error: 'read' requires 1 argument: telegramId");
                    break;
                }
                try {
                    Long telegramId = Long.valueOf(tokens[1]);
                    UserEntity user = userServiceImp.getEntityById(telegramId);
                    System.out.println(user != null ? user : "User not found");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "help" -> {
                System.out.println("""
                        create <telegramId> <username> <name> <lastname> - create a user
                        updateUsername <telegramId> <newUsername> - update username
                        updateName <telegramId> <newName> - update name
                        updateLastname <telegramId> <newLastname> - update lastname
                        delete <telegramId> - delete a user
                        read <telegramId> - get a user
                        help - show this help
                        exit - close app
                        """);
            }
            default -> System.out.println("Invalid command. Type 'help' to see the list of commands.");
        }
    }
}
