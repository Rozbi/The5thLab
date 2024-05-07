package utility;

import managers.CommandManager;
import managers.InputManager;
import managers.OutputManager;

import java.util.Scanner;

public interface Runnable {
    /**интерфейс для запуска интерактивного режима**/
    public void letsGo();
     public void letsGoScript(String arg, CommandManager comman, InputManager inputManager, OutputManager outputManager, Scanner scanner);
}
