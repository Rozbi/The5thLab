package commands;

import managers.CollectionManager;
import managers.OutputManager;
import spaceMarine.SpaceMarine;
import managers.InputManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PrintDescending extends Command{
    private static String name;
    private static String description;
    private OutputManager outputManager;
    private CollectionManager collectionManager;
    public PrintDescending(String name, String description, OutputManager outputManager, CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.name = name;
        this.description=description;
        this.collectionManager=collectionManager;
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
        if (!arg.isEmpty()){
            outputManager.print("Неправильное количество аргументов");
            return false;
        }
         ArrayList<SpaceMarine> reverseCollection= new ArrayList<SpaceMarine>();
        for (var spaceMarine : collectionManager.getCollection()){
            reverseCollection.add(spaceMarine);
            reverseCollection.sort(Collections.reverseOrder());
        }
        outputManager.println(String.valueOf(reverseCollection));
        return true;
         }
    }
