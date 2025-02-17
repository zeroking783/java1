package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

public class RemoveFirst extends Command {
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
    public RemoveFirst(Console console, CollectionManager collectionManager) {
        super("remove_by_id <ID>", "удалить первый элемент из коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
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

        var element = collectionManager.getFirst();
        if (element == null) return new ExecutionResponse(false, "Ничего не удалено");

        collectionManager.remove(element.getId());
        collectionManager.update();
        return new ExecutionResponse("Первый город успешно удален!");
    }
}