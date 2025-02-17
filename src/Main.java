import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import commands.*;
import data.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DumpManager;
import utility.Runner;
import utility.StandardConsole;


public class Main {


    public static void main(String[] args) {
        String value = System.getenv("PATH_TO_JSON");

        // Проверяем, если переменная окружения существует
        if (value != null) {
            System.out.println("Значение переменной окружения " + "PATH_TO_JSON" + ": " + value);
        } else {
            System.out.println("Переменная окружения " + "PATH_TO_JSON" + " не найдена.");
        }







        var console = new StandardConsole();

        if (args.length == 0) {
            console.println(
                    "Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }

        var dumpManager = new DumpManager(args[0], console);
        var collectionManager = new CollectionManager(dumpManager);
        if (!collectionManager.init()) {
            System.exit(1);
        }

        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new ExecuteScript(console));
            register("exit", new Exit(console));
            register("reorder", new Reorder(console, collectionManager));
            register("filter_by_meters_above_sea_level", new FilterByMetersAboveSeaLevel(console, collectionManager));
            register("max_by_population", new MaxByPopulation(console, collectionManager));
            register("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevel(console, collectionManager));
            register("remove_first", new RemoveFirst(console, collectionManager));
            register("remove_lower", new RemoveLower(console, collectionManager));
        }};

        new Runner(console, commandManager).interactiveMode();
    }
}

