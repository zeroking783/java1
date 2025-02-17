package commands;

import data.City;
import managers.CollectionManager;
import managers.AskManager;
import utility.Console;
import utility.ExecutionResponse;
/**
 * Класс команды для добавления нового элемента в коллекцию
 */
public class Add extends Command{
    /**
     * Консоль
     */
    private final Console console;
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор
     *
     * @param console           консоль
     * @param collectionManager менеджер коллекции
     */
    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды
     *
     * @param argument с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String argument) {
        String[] args = argument.split(" ");
        if (args.length > 1 && (args.length > 2 || !args[1].isEmpty()))return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        try {
            if (argument.isEmpty()) return new ExecutionResponse(false,
                    "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

            console.println("* Создание нового города:");
            City a = AskManager.askCity(console, collectionManager.getFreeId());

            if (a != null && a.validate()) {
                collectionManager.add(a);
                return new ExecutionResponse("Город успешно добавлен!");
            } else
                return new ExecutionResponse(false, "Поля города не валидны! Город не создан!");
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }


}
