package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;
import managers.AskManager;

public class Update extends Command {
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

    public Update(Console console, CollectionManager collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
        try {
            if (args.length >2 && args[1].isEmpty() || args.length <=1)
                return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            long id = -1;
            try {
                id = Long.parseLong(args[1].trim());
            } catch (NumberFormatException e) {
                return new ExecutionResponse(false, "ID не распознан");
            }

            var old = collectionManager.byId(id);
            if (old == null || !collectionManager.getCollection().contains(old)) {
                return new ExecutionResponse(false, "Не существующий ID");
            }

            console.println("* Создание нового города:");
            var d = AskManager.askCity(console, old.getId());
            if (d != null && d.validate()) {
                collectionManager.remove(old.getId());
                collectionManager.add(d);
                collectionManager.update();
                return new ExecutionResponse("Обновлено!");
            } else {
                return new ExecutionResponse(false, "Поля не валидны!Город не создан!");
            }
        } catch (AskManager.AskBreak e) {
            return new ExecutionResponse(false, "Поля не валидны! Город не создан!");
        }
    }
}