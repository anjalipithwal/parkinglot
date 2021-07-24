package commands;

import models.ParkingLot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LeaveSlotCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final String INVALID_COMMAND_ERROR_MESSAGE = "Command: [%s] is invalid\n";
    private final String INVALID_SLOT_ERROR_MESSAGE = "Slot does not exist in this parking lot\n";
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMissingSlotNumber() {
        String command = "Leave";
        LeaveSlotCommand leaveSlotCommand = new LeaveSlotCommand(command);
        leaveSlotCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidNumberOfArguments() {
        String command = "Leave 1 2";
        LeaveSlotCommand leaveSlotCommand = new LeaveSlotCommand(command);
        leaveSlotCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidSlotNumber() {
        ParkingLot parkingLot = new ParkingLot(6);
        String command = "Leave -1";
        LeaveSlotCommand leaveSlotCommand = new LeaveSlotCommand(command);
        leaveSlotCommand.execute(parkingLot);
        assertEquals(String.format(INVALID_SLOT_ERROR_MESSAGE, command), outContent.toString());
    }
}
