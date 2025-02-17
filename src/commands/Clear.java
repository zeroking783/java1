package commands;

import data.City;
import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Iterator;
/**
 * Класс команды для очищения коллекции
 */
public class Clear extends Command {
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
    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами команды
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String arguments) {
        String[] args = arguments.split(" ");
        if (args.length > 1 && !args[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        Iterator<City> iterator = collectionManager.getCollection().iterator();

        while (iterator.hasNext()) {
            City city = iterator.next();
//            collectionManager.remove(city.getId());
            iterator.remove();
        }

        collectionManager.update();
        return new ExecutionResponse("Коллекция очищена!");
    }
}
