package commands;

import models.ParkingLot;
import models.Vehicle;

public class ParkVehicleCommand implements Command{
    private String command;
    private String vehicleRegNo;
    private int driverAge;

    public ParkVehicleCommand(String command) {
        this.command = command;
    }

    @Override
    public boolean validate() {
        String ERROR_MESSAGE = "Command: [" + command + "] is invalid";
        String[] commandWords = this.command.split("\\s");
        boolean isValid;
        if(commandWords.length == 4) {
            isValid = validateVehicleRegNo(commandWords[1]) && validateDriverAge(commandWords[2], commandWords[3]);
        } else {
            isValid = false;
        }
        if(!isValid) {
            System.out.println(ERROR_MESSAGE);
        } else {
            this.vehicleRegNo = commandWords[1];
            this.driverAge = Integer.parseInt(commandWords[3]);
        }
        return isValid;
    }

    private boolean validateDriverAge(String driverAgeCommand, String driverAge) {
        int age = Integer.parseInt(driverAge);
        if(driverAgeCommand.equals("driver_age") && (age >= 18 && age <= 75)) {
            return true;
        }
        return false;
    }

    private boolean validateVehicleRegNo(String vehicleRegNo) {
        // Assumption: entered vehicle registration number will be valid(Based on FAQ)
        return true;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        Vehicle vehicle = new Vehicle(vehicleRegNo, driverAge);
        parkingLot.parkVehicleInParkingLot(vehicle);
    }
}
