package managers;

import data.City;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.*;


public class CollectionManager {
    private long currentId = 0;
    private Map<Long, City> cityk = new HashMap<>();
    private ArrayList<City> collection = new ArrayList<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final DumpManager dumpManager;
    private boolean isAscendingSort;

    public CollectionManager(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    //Последнее время инициализации.

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }
    //Устанавливает будет ли сортировка в обратном порядке
    public void setIsAscendingSort(boolean isAscendingSort) {
        this.isAscendingSort = isAscendingSort;
    }

    //Последнее время сохранения.

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    //@return коллекция.

    public ArrayList<City> getCollection() {
        return collection;
    }

    //Получить по ID
    public City byId(long id) { return cityk.get(id); }

    //Содежит ли

    public boolean isСontain(City e) { return e == null || byId(e.getId()) != null; }

    //Получить свободный ID
    public long getFreeId() {
        while (byId(++currentId) != null);
        return currentId;
    }

    //Добавляет город

    public boolean add(City a) {
        if (isСontain(a)) return false;
        cityk.put(a.getId(), a);
        collection.add(a);
        update();
        return true;
    }


    //Обновляет City

    public boolean update(City a) {
        if (!isСontain(a)) return false;
        collection.remove(byId(a.getId()));
        cityk.put(a.getId(), a);
        collection.add(a);
        update();
        return true;
    }

    //Удаляет город по ID

    public boolean remove(long id) {
        var a = byId(id);
        if (a == null) return false;
        cityk.remove(a.getId());
        collection.remove(a);
        update();
        return true;
    }

    //Фиксирует изменения коллекции

    public void update() {
        Collections.sort(collection);
        if (isAscendingSort) Collections.reverse(collection);
    }

    //Загрузка коллекции из файла в менеджер

    public boolean init() {
        collection.clear();
        cityk.clear();
        collection = dumpManager.readCollection();
        lastInitTime = LocalDateTime.now();
        for (var e : collection)
            if (byId(e.getId()) != null) {
                collection.clear();
                cityk.clear();
                return false;
            } else {
                if (e.getId()>currentId) currentId = e.getId();
                cityk.put(e.getId(), e);
            }
        update();
        return true;
    }

    /**
     * Функция для получения первого элемента в коллекции
     * @return возвращает первый элемент коллекци
     */
    public City getFirst() {
        Iterator<City> iterator = collection.iterator();
        if (iterator.hasNext()) return iterator.next();
        return null;
    }

    //Сохраняет коллекцию в файл

    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (var a : collection) {
            info.append(a+"\n\n");
        }
        return info.toString().trim();
    }

}
