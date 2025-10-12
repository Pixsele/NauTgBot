package ru.vorobyev.NauJava_TgBotDelivery;

/**
 * Интерфейс для обработки команд, поступающих в приложение.
 *
 * <p>Определяет общий контракт для классов-обработчиков,
 * которые принимают текстовую команду, анализируют её и выполняют соответствующее действие.
 */

public interface CommandProcess {

    void processCommand(String command);
}
