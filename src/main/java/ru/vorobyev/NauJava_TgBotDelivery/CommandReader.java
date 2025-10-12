package ru.vorobyev.NauJava_TgBotDelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Scanner;

/**
 * Компонент для чтения команд из консоли и передачи их на обработку.
 *
 * <p>Использует интерфейс {@link CommandProcess} для обработки введённых пользователем команд.
 */

@Configuration
public class CommandReader {
    private final CommandProcess commandProcessor;

    @Autowired
    public CommandReader(CommandProcessorUser commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args ->{
            try (Scanner scanner = new Scanner(System.in)) {
                while (true){
                    System.out.print("> ");
                    String command = scanner.nextLine();
                    if(Objects.equals(command, "exit")){
                        System.exit(0);
                    }

                    commandProcessor.processCommand(command);
                }
            }
        };
    }
}
