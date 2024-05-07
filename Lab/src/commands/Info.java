package commands;

import managers.CollectionManager;
import managers.OutputManager;
import managers.InputManager;

import java.text.CollationKey;
import java.util.PriorityQueue;

public class Info extends Command{
    private OutputManager outputManager;
    private CollectionManager collectionManager;
    private static String name;
    private static String description;
    public Info(String name, String description, CollectionManager collectionManager, OutputManager outputManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");this.name = name;
        this.description=description;
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public boolean execute(String arg){
        if (!arg.isEmpty()) {
            outputManager.println("Неправильное количество аргументов ");
            outputManager.println("Использование: '" + getName() + "'");
            return false;
        }
        outputManager.println("Тип коллекции: " + collectionManager.getCollection().getClass().toString() + "\n" +
                "Количество элементов: " + collectionManager.getCollection().size() + "\n" +
                "Время загрузки " + collectionManager.getLastInitTime() + "\n" +
                "Время сохранения " + collectionManager.getLastSaveTime());
    return true;
    }
}
