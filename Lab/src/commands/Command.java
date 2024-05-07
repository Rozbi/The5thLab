package commands;

import java.io.FileNotFoundException;

public class Command {
    private String name;
    private String description;
    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean execute(String arg) throws FileNotFoundException {
        return false;
    }
}
