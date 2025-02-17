package commands;

import utility.ExecutionResponse;
/**
 * Интерфейс, предназначенный для исполнения
 */
public interface Executable {
    /**
     * Выполнить что-либо.
     *
     * @param arguments Аргумент для выполнения
     * @return результат выполнения
     */
    ExecutionResponse apply(String arguments);
}
