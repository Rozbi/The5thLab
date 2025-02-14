package managers;

import commands.Command;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import commands.*;
import exeptions.InvalidInputException;
import utility.Ask;
import utility.Runner;
/**Менеджер для запуска команд**/
public class CommandManager {
    private CollectionManager collectionManager;
    private OutputManager outputManager;
    private Ask ask;
    private InputManager inputManager;
    private Runner runner;
    private final HashMap<String, Command> commandMap = new HashMap<>();
    public CommandManager(CollectionManager collectionManager, OutputManager outputManager, Ask ask, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.ask = ask;
        this.inputManager = inputManager;
        this.runner = runner;
    }

    /**добавление команд*/
    /*
     * @return - commandMap - HashMap c командами
     */
    public HashMap<String, Command> addCommands() {
        commandMap.put("help", new Help("help", "вывести справку по доступным командам", commandMap, outputManager));
        commandMap.put("add", new Add("add", "добавить новый элемент в коллекцию", collectionManager, outputManager, ask, inputManager));
        commandMap.put("info", new Info("info", "вывести в стандартный поток вывода информацию о коллекции", collectionManager, outputManager));
        commandMap.put("clear", new Clear("clear", "очистить коллекцию", outputManager, collectionManager));
        commandMap.put("group_counting_by_health", new CountingByHealth("group_counting_by_health", "сгруппировать элементы коллекции по значению поля health, вывести количество элементов в каждой группе", collectionManager, outputManager));
        commandMap.put("add_if_min", new AddIfMin("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", ask, inputManager, outputManager, collectionManager));
        commandMap.put("execute_script", new ExecuteScript("execute_script", "считать и исполнить скрипт из указанного файла.", outputManager, collectionManager));
        commandMap.put("print_ascending", new PrintAscending("print_ascending", "вывести элементы коллекции в порядке возрастания", outputManager, collectionManager));
        commandMap.put("print_descending", new PrintDescending("print_descending", "вывести элементы коллекции в порядке убывания", outputManager, collectionManager));
        commandMap.put("remove_by_id", new RemoveById("remove_by_id {id}", "удалить элемент из коллекции по его id", outputManager, collectionManager));
        commandMap.put("remove_lower", new RemoveLower("remove_lower {id}", "удалить из коллекции элементы, меньшие, чем заданный", outputManager, collectionManager));
        commandMap.put("exit", new Exit("exit", "завершить программу", outputManager));
        commandMap.put("show", new Show("show", "вывести содержимое коллекции", collectionManager, outputManager));
        commandMap.put("update", new Update("update {id}", "обновить значение элемента коллекции, id которого равен заданному", collectionManager, outputManager, inputManager, ask));
        commandMap.put("history", new History("history", "вывести последние 14 команд", collectionManager, outputManager));
        commandMap.put("save", new Save("save", "сохранить коллекцию в файл", collectionManager, outputManager));
        return commandMap;
    }
    /**запуск команд*/
    public void execute(String name, String args) throws InvalidInputException, FileNotFoundException {
        while (true) {
            Command command = commandMap.get(name);
            if (command == null) {
                System.out.println("Такой команды не существует");
                System.out.println("Введите название команды или help для просмотра доступных команд");
                name = inputManager.read();
            } else {
                collectionManager.history(command.getName());
                command.execute(args);
                break;
                }
        }
    }
    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
