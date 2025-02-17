package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

import java.util.ArrayList;
import java.util.Collections;

public class PrintFieldDescendingMetersAboveSeaLevel extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingMetersAboveSeaLevel(Console console, CollectionManager collectionManager) {
        super("print_field_descending_meters_above_sea_level", "вывести значения поля metersAboveSeaLevel всех элементов в порядке убывания");
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

        StringBuilder sb = new StringBuilder();
        ArrayList<Float> sea = new ArrayList<>();
        for (var e: collectionManager.getCollection()) {
            sea.add(e.getMetersAboveSeaLevel());
        }
        Collections.sort(sea, Collections.reverseOrder());
        for (var e: sea) {
            sb.append(e.toString()+ "\n");
        }
        return new ExecutionResponse(true, sb.toString());



    }
}
