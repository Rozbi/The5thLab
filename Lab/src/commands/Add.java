package commands;

import managers.CollectionManager;
import managers.InputManager;
import managers.OutputManager;
import spaceMarine.SpaceMarine;
import utility.Ask;

public class Add extends Command{
    private OutputManager outputManager;
    private static String name;
    private static String description;
    private CollectionManager collectionManager;
    private Ask ask;
    private InputManager inputManager;
    public Add(String name, String description, CollectionManager collectionManager, OutputManager outputManager, Ask ask, InputManager inputManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.name = name;
        this.description=description;
        this.collectionManager=collectionManager;
        this.outputManager=outputManager;
        this.ask=ask;
        this.inputManager = inputManager;
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
            outputManager.print("Неправильное количество аргументов");
            return false;
        }
        outputManager.println("* Создание Space Marine:");
        SpaceMarine sm = ask.getSpaceMarine(outputManager, collectionManager, inputManager);
        if (sm!=null && sm.validate()) {
            collectionManager.add(sm);
            outputManager.println("Элемент добавлен в коллекцию");
            return true;
        } else{
            outputManager.println("Поля не валидны. Элемент не добавлен");
            return false;
        }
    }
}
