package commands;

import models.ParkingLot;

public class DisplayVehicleRegNoForDriverAge implements Command{
    private String command;
    private int driverAge;
    public DisplayVehicleRegNoForDriverAge(String command) {
        this.command = command;
    }

    @Override
    public boolean validate() {
        String ERROR_MESSAGE = "Command: [" + command + "] is invalid";
        String[] commandWords = this.command.split("\\s");
        boolean isValid;
        if(commandWords.length == 2) {
            isValid = validateDriverAge(Integer.parseInt(commandWords[1]));
        } else {
            isValid = false;
        }
        if(!isValid) {
            System.out.println(ERROR_MESSAGE);
        } else {
            this.driverAge = Integer.parseInt(commandWords[1]);
        }
        return isValid;
    }

    private boolean validateDriverAge(int driverAge) {
        return driverAge >= 18 && driverAge <= 75;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.displayVehiclesForDriverAge(this.driverAge);
    }
}
