package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

public class Reorder extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Reorder(Console console, CollectionManager collectionManager) {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String arguments) {
        String[] args = arguments.split(" ");
        if (args.length>1) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        //collectionManager.add("reorder", true);
        collectionManager.setIsAscendingSort( true);
        collectionManager.update();
        collectionManager.setIsAscendingSort(false);
        return new ExecutionResponse(true, "Отсортирлвано в обратном порядке");


//        return true;
    }
}