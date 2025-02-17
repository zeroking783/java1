package commands;

import utility.Console;
import utility.ExecutionResponse;
/**
 * Класс команды для выхода из интерактивного режима без сохранения
 */
public class Exit extends Command {
    /**
     * Консоль
     */
    private final Console console;

    /**
     * Конструктор
     *
     * @param console консоль
     */
    public Exit(Console console) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String arguments) {
        String[] args = arguments.split(" ");
        if (args.length > 1 && !args[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse("exit"); //"Завершение выполнения...");
    }
}
