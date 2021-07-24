package commands;

import models.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private List<Command> commandList;
    private ParkingLot parkingLot;

    public CommandExecutor() {
        commandList = new ArrayList<>();
    }

    public void addCommand(String command) {
        String ERROR_MESSAGE = "Command: [" + command + "] is invalid";
        if(command == null || command.trim().length() == 0) {
            System.out.println(ERROR_MESSAGE);
            return;
        }
        Command newCommand = null;
        String commandType = command.split("\\s")[0];
        if(commandType.equals("Create_parking_lot")) {
            newCommand = new CreateParkingLotCommand(command);
        } else if(commandType.equals("Park")) {
            newCommand = new ParkVehicleCommand(command);
        } else if(commandType.equals("Slot_numbers_for_driver_of_age")) {
            newCommand = new DisplaySlotsDriverAgeCommand(command);
        } else if(commandType.equals("Slot_number_for_car_with_number")) {
            newCommand = new DisplaySlotForVehicleCommand(command);
        } else if(commandType.equals("Leave")) {
            newCommand = new LeaveSlotCommand(command);
        } else if(commandType.equals("Vehicle_registration_number_for_driver_of_age")) {
            newCommand = new DisplayVehicleRegNoForDriverAge(command);
        }

        if(newCommand == null) {
            System.out.println(ERROR_MESSAGE);
        } else {
            boolean isCommandValid = newCommand.validate();
            if(isCommandValid) {
                commandList.add(newCommand);
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }

    public void executeCommands() {
        if(commandList.isEmpty()) {
            System.out.println("No commands present to execute");
        } else {
            Command firstCommand = commandList.get(0);
            if(firstCommand instanceof CreateParkingLotCommand) {
                this.parkingLot = ParkingLot.createParkingLot(((CreateParkingLotCommand) firstCommand).numberOfSlots);
                for(int i = 1; i < commandList.size(); i++) {
                    Command command = commandList.get(i);
                    command.execute(this.parkingLot);
                }
            } else {
                System.out.println("Create_parking_lot must be the first command");
            }
        }
    }
}
