//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

import managers.CollectionManager;
import managers.CommandManager;
import managers.InputManager;
import managers.JsonManager;
import managers.OutputManager;
import spaceMarine.SpaceMarine;
import utility.Ask;
import utility.Runner;
import utility.SpaceMarineComparator;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = null;
        final Scanner scanner = new Scanner(System.in);
        OutputManager outputManager = new OutputManager();
        JsonManager jsonManager = new JsonManager(outputManager);
        CollectionManager collectionManager = new CollectionManager(jsonManager);
        InputManager inputManager = new InputManager(outputManager, scanner);
        Ask ask = new Ask(inputManager, outputManager);
        SpaceMarineComparator comparator = new SpaceMarineComparator();
        PriorityQueue<SpaceMarine> priora = new PriorityQueue(comparator);
        priora.addAll(collectionManager.getCollection());
        collectionManager.setCollection(priora);
        CommandManager commandManager = new CommandManager(collectionManager, outputManager, ask, inputManager);
        commandManager.addCommands();
        Runner runner = new Runner(outputManager, inputManager, commandManager);
        runner.letsGo();

    }
}
