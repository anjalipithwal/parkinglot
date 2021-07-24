package commands;

import models.ParkingLot;

public class LeaveSlotCommand implements Command{
    private String command;
    private int slotNumber;
    public LeaveSlotCommand(String command) {
        this.command = command;
    }

    @Override
    public boolean validate() {
        String ERROR_MESSAGE = "Command: [" + command + "] is invalid";
        String[] commandWords = this.command.split("\\s");
        boolean isValid = false;
        if(commandWords.length == 2) {
            isValid = true;
        }
        if(!isValid) {
            System.out.println(ERROR_MESSAGE);
        } else {
            this.slotNumber = Integer.parseInt(commandWords[1]);
        }
        return isValid;
    }

    private boolean validateSlotNumber(int slotNumber, int maxSlots) {
        return slotNumber > 0 && slotNumber <= maxSlots;
    }

    @Override
    public void execute(ParkingLot parkingLot) {
        if(validateSlotNumber(this.slotNumber, parkingLot.getNumberOfSlots())) {
            parkingLot.vehicleLeavesParkingLot(this.slotNumber);
        } else {
            System.out.println("Slot does not exist in this parking lot");
        }
    }
}
