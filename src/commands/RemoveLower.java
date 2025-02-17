package commands;

import data.City;
import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveLower extends Command {
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

    public RemoveLower(Console console, CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
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
        if (args.length >2 && args[1].isEmpty() || args.length <=1)
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        long id = -1;
        try {
            id = Long.parseLong(args[1].trim());
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "ID не распознан");
        }

        if (collectionManager.byId(id) == null || !collectionManager.getCollection().contains(collectionManager.byId(id)))
            return new ExecutionResponse(false, "Не существующий ID");
        Iterator<City> iterator = collectionManager.getCollection().iterator();
        List<Long> arr = new ArrayList<>();
        while (iterator.hasNext()) {
            var e = iterator.next();
            if (e.getId() == id) break;
            else {
                arr.add(e.getId());
            }
        }
        for (var e : arr) {
            collectionManager.remove(e);
        }

        collectionManager.update();
        return new ExecutionResponse("Все меньшие города удалены!");
    }
}