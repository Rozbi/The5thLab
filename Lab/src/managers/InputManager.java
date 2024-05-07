package managers;

import com.google.gson.internal.NonNullElementWrapperList;
import commands.Command;
import exeptions.InvalidInputException;
import spaceMarine.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import utility.Ask;

import commands.*;
/**менеджер для чтения данных пользователя**/
public class InputManager {
    private Scanner scanner;
    private OutputManager outputManager;
    private boolean finish = false;
    private int flag;

    public InputManager(OutputManager outputManager, Scanner scanner) throws FileNotFoundException {
        this.outputManager = outputManager;
        this.scanner = scanner;

    }

    /**
     * чтение данных пользователя
     */
    public String read() throws InvalidInputException {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim().toLowerCase();
            return input;
        }else{
            outputManager.print("Достигнут конец файла");
            }
        return null;
    }
}
