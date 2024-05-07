package utility;

import commands.Command;
import commands.ExecuteScript;
import exeptions.InvalidInputException;
import managers.CommandManager;
import managers.InputManager;
import managers.OutputManager;
import spaceMarine.AstartesCategory;
import spaceMarine.MeleeWeapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**класс для вызова команд**/
public class Runner implements Runnable{
    private OutputManager outputManager;
    private InputManager inputManager;
    private CommandManager comman;

    public Runner(OutputManager outputManager, InputManager inputManager, CommandManager comman) {
        this.outputManager = outputManager;
        this.inputManager = inputManager;
        this.comman = comman;
    }

    /**метод для запуска интерактивного режима**/
    @Override
    public void letsGo() {
        outputManager.print("Введите название команды или команду help для просмотра доступных команд\n");
        while (true) {
            try {
                String input;
                input = inputManager.read();
                String[] command = (input.trim()+ " ").split(" ", 2);
                    String commandName = command[0];
                    switch (commandName) {
                        case "execute_script": {
                            String argument = command[1].trim();
                            comman.execute(commandName, argument);
                            break;
                        }
                        default: {
                            if (command[1].isEmpty()) {
                                command[1] = "";
                                comman.execute(commandName, command[1]);
                            } else {
                                String argument = command[1].trim();;
                                try {
                                    Integer.parseInt(argument);
                                } catch (NumberFormatException e) {
                                    outputManager.println("Неправильный аргумент");
                                }
                                comman.execute(commandName, argument);
                            }
                        }
                    }
                } catch (InvalidInputException e) {
                outputManager.println("Неверный ввод данных ");
            } catch (FileNotFoundException e) {
                outputManager.println("Файл скрипта не найден!" );
            }
        }
    }
   /**метод для запуска интерактивного режима в execute_script**/
    @Override
    public void letsGoScript(String arg, CommandManager comman, InputManager inputManager, OutputManager outputManager, Scanner scanner){

        comman.addCommands();
        while (scanner.hasNextLine()) {
            try {
                String input;
                input = inputManager.read();
                String[] command = (input.trim() + " ").split(" ", 2);
                String commandName = command[0];
                try {
                    Command commandExecute = comman.getCommandMap().get(commandName);
                    if (commandExecute == null) {
                        outputManager.println("Такой команды нет");
                    }
                    if (command[1].isEmpty() && !commandName.equals("execute_script")) {
                        command[1] = "";
                        commandExecute.execute(command[1]);
                    }
                    else if (!commandName.equals("execute_script")) {
                        String argument = command[1].trim();
                        try {
                        Integer.parseInt(argument);
                        } catch (NumberFormatException e) {
                            outputManager.println("Неправильный аргумент");
                        }
                    commandExecute.execute(argument);
                    } else {
                        outputManager.println("Скрипт не может вызываться рекурсивно");
                    }
                }catch(NullPointerException e){
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (InvalidInputException e) {
                outputManager.println("Неверный ввод данных ");
            }
        }
    }
}

