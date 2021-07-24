package commands;

import models.ParkingLot;

public class CreateParkingLotCommand implements Command{
    private static final int MAX_PARKING_LIMIT = 1000;
    private String command;
    public int numberOfSlots;

    public CreateParkingLotCommand(String command) {
        this.command = command;
    }

    @Override
    public boolean validate() {
        String ERROR_MESSAGE = "Command: [" + command + "] is invalid";
        String[] commandWords = this.command.split("\\s");
        boolean isValid;
        if(commandWords.length == 2) {
            isValid = validateParkingLotSlots(commandWords[1]);
        } else {
            isValid = false;
        }
        if(!isValid) {
            System.out.println(ERROR_MESSAGE);
        } else {
            this.numberOfSlots = Integer.parseInt(commandWords[1]);
        }
        return isValid;
    }

    private boolean validateParkingLotSlots(String commandWord) {
        int numberOfSlots = Integer.parseInt(commandWord);
        return numberOfSlots >= 0 && numberOfSlots <= MAX_PARKING_LIMIT;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        parkingLot.createParkingLot(numberOfSlots);
    }
}
