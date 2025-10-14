package ru.vorobyev.NauJava_TgBotDelivery;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vorobyev.NauJava_TgBotDelivery.entity.UserEntity;
import ru.vorobyev.NauJava_TgBotDelivery.service.UserService;
import ru.vorobyev.NauJava_TgBotDelivery.service.UserServiceImp;

/**
 * Компонент для обработки текстовых команд, поступающих в приложение.
 *
 * <p>Парсит входную команду и делегирует выполнение соответствующим
 * методам {@link UserServiceImp}. Предназначен для упрощённой обработки команд
 * из консоли или тестового интерфейса бота.
 */

@Component
public class CommandProcessorUser implements CommandProcess {

    private final UserService userService;
    private final MeterRegistry meterRegistry;

    @Autowired
    public CommandProcessorUser(UserServiceImp userServiceImp, MeterRegistry meterRegistry) {
        this.userService = userServiceImp;
        this.meterRegistry = meterRegistry;
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
                    userService.createUser(telegramId, tokens[2], tokens[3], tokens[4]);
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
                    userService.updateUsername(telegramId, tokens[2]);
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
                    userService.updateName(telegramId, tokens[2]);
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
                    userService.updateLastname(telegramId, tokens[2]);
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
                    userService.deleteUserById(telegramId);
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
                    UserEntity user = userService.getEntityById(telegramId);
                    System.out.println(user != null ? user : "User not found");
                } catch (NumberFormatException e) {
                    System.out.println("Error: telegramId must be a number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "help" ->
                System.out.println("""
                        create <telegramId> <username> <name> <lastname> - create a user
                        updateUsername <telegramId> <newUsername> - update username
                        updateName <telegramId> <newName> - update name
                        updateLastname <telegramId> <newLastname> - update lastname
                        delete <telegramId> - delete a user
                        read <telegramId> - get a user
                        metric <type metric> - show metrics
                        help - show this help
                        exit - close app
                        """);
            case "metric" ->{
                if (tokens.length != 2) {
                    System.out.println("Error: 'metric' requires 1 argument: metric name");
                    break;
                }

                meterRegistry.getMeters().stream()
                        .filter(m -> m.getId().getName().equals(tokens[1]))
                        .findFirst().ifPresentOrElse(m ->
                            m.measure().forEach(stat -> System.out.println(stat.getStatistic() + "=" + stat.getValue())),
                                () -> System.out.println("Metric "+ tokens[1] +" not found"));
            }
            default -> System.out.println("Invalid command. Type 'help' to see the list of commands.");
        }
    }
}
