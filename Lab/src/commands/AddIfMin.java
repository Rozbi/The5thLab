package commands;

import managers.CollectionManager;
import managers.InputManager;
import managers.OutputManager;
import spaceMarine.SpaceMarine;
import utility.Ask;

import java.util.PriorityQueue;

public class AddIfMin extends Command{
    private static String name;
    private static String description;
    private OutputManager outputManager;
    private CollectionManager collectionManager;
    private InputManager inputManager;
    private Ask ask;
    public AddIfMin(String name, String description, Ask ask, InputManager inputManager, OutputManager outputManager, CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");this.name = name;
        this.description=description;
        this.name = name;
        this.ask=ask;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;
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
    public boolean execute(String arg) {
         if (!arg.isEmpty()) {
             outputManager.print("Неправильное количество аргументов");
             return false;
         }
         SpaceMarine element = ask.getSpaceMarine(outputManager, collectionManager, inputManager);
         if (element != null && element.validate()) {
             Long minElement = 1000L;
             for (var spaceMarine : collectionManager.getCollection()) {
                 if (minElement > spaceMarine.getHealth()) {
                     minElement = spaceMarine.getHealth();
                 }
             }
             if (minElement > element.getHealth()) {
                     collectionManager.add(element);
                     outputManager.println("Элемент добавлен в коллекцию");
                     return true;
                 } else {
                     outputManager.println("Здоровье не минимальное. Элемент не добавлен" + "\n");
                     return false;
                 }
             }
             else {
             outputManager.println("Поля элемента не валидны. Элемент не добавлен в коллекцию");
             return false;
         }
     }
}
