package commands;

import data.City;
import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

public class MaxByPopulation extends Command {
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
    public MaxByPopulation(Console console, CollectionManager collectionManager) {
        super("max_by_population", "вывести любой объект из коллекции, значение поля population которого является максимальным");
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
        City max = collectionManager.getFirst();
        for (var e : collectionManager.getCollection()) {
            if (max.getPopulation() < e.getPopulation()) max = e;
        }
        return new ExecutionResponse(max.toString());
    }
}
