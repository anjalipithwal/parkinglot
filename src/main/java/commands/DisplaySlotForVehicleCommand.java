package commands;

import models.ParkingLot;

public class DisplaySlotForVehicleCommand implements Command{
    private String command;
    private String vehicleRegNo;
    public DisplaySlotForVehicleCommand(String command) {
        this.command = command;
    }

    @Override
    public boolean validate() {
        String ERROR_MESSAGE = "Command: [" + command + "] is invalid";
        String[] commandWords = this.command.split("\\s");
        boolean isValid;
        if(commandWords.length == 2) {
            isValid = validateVehicleRegNo(commandWords[1]);
        } else {
            isValid = false;
        }
        if(!isValid) {
            System.out.println(ERROR_MESSAGE);
        } else {
            this.vehicleRegNo = commandWords[1];
        }
        return isValid;
    }

    private boolean validateVehicleRegNo(String vehicleRegNo) {
        return true;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.displaySlotForVehicle(this.vehicleRegNo);
    }
}
