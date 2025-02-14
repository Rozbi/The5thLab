package commands;

import managers.CollectionManager;
import managers.JsonManager;
import managers.OutputManager;
import managers.InputManager;
import spaceMarine.SpaceMarine;
import utility.Ask;
import utility.SpaceMarineComparator;

public class Update extends Command{
    private static String name;
    private static String description;
    private OutputManager outputManager;
    private CollectionManager collectionManager;
    private Ask ask;
    private InputManager inputManager;
    private boolean k;
    public Update(String name, String description, CollectionManager collectionManager, OutputManager outputManager, InputManager inputManager, Ask ask) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.name = name;
        this.description=description;
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.inputManager = inputManager;
        this.ask = ask;
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
        if (arg.isEmpty()) {
            outputManager.println("Неправильное количество аргументов ");
            outputManager.println("Использование: '" + getName() + "'");
            return false;
        }
        SpaceMarineComparator comparator = new SpaceMarineComparator();
        SpaceMarine element = ask.getSpaceMarine(outputManager, collectionManager, inputManager);
        if (element != null && element.validate()) {
            for (var spaceMarine : collectionManager.getCollection()) {
                if ((spaceMarine.getId()) == Long.parseLong(arg)) {
                    spaceMarine.setName(element.getName());
                    spaceMarine.setCoordinates(element.getCoordinates());
                    spaceMarine.setHealth(element.getHealth());
                    spaceMarine.setHeartCount(element.getHeartCount());
                    spaceMarine.setMeleeWeapon(element.getMeleeWeapon());
                    spaceMarine.setChapter(element.getChapter());
                    spaceMarine.setCreationDate(element.getCreationDate());
                    spaceMarine.setCategory(element.getCategory());
                    outputManager.print("Элемент обновлен. ");
                    k = true;
                    return true;
                }
            } if (!k) {
                outputManager.println("Нет элемента с таким id ");
            }
        } else {
            outputManager.print("Поля не валидны. Элемент не добавлен в коллекцию. ");
            return false;
        }return false;
    }
}
