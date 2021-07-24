import commands.CommandExecutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLotSystem {
    public static void main(String[] args) {

        CommandExecutor commandExecutor = new CommandExecutor();

        try {
            FileInputStream fileInputStream = new FileInputStream("./src/main/input.txt");
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                commandExecutor.addCommand(command);
            }
            commandExecutor.executeCommands();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
