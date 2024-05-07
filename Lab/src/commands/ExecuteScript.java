package commands;

import exeptions.InvalidInputException;
import managers.CollectionManager;
import managers.CommandManager;
import managers.OutputManager;
import managers.InputManager;
import utility.Ask;
import utility.Runnable;
import utility.Runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScript extends Command {
    private static String name;
    private static String description;

    private OutputManager outputManager;
    private CollectionManager collectionManager;
    private String input;

    public ExecuteScript(String name, String description, OutputManager outputManager, CollectionManager collectionManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла.");
        this.name = name;
        this.description = description;
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean execute(String arg) throws FileNotFoundException {
        if (arg.isEmpty()) {
            outputManager.print("Неправильное количество аргументов");
            return false;
        }
        Scanner scanner = new Scanner(new File(arg));
        InputManager inputManager1 = new InputManager(outputManager, scanner);
        Ask ask1 = new Ask(inputManager1, outputManager);
        CommandManager commandManager = new CommandManager(collectionManager, outputManager, ask1, inputManager1);
        Runner runner = new Runner(outputManager, inputManager1, commandManager);
        runner.letsGoScript(arg, commandManager, inputManager1, outputManager, scanner);
        return true;
    }
}