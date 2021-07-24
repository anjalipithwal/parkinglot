package commands;

import models.ParkingLot;

public interface Command {

    public boolean validate();

    public void execute(ParkingLot parkingLot);
}