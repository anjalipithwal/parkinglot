package commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CommandExecutorTest {
    CommandExecutor commandExecutor = new CommandExecutor();
    // Error messages
    final String COMMAND_INVALID_ERROR_MESSAGE = "Command: [] is invalid\n";
    final String NON_EXISTENT_COMMAND_ERROR_MESSAGE = "Command: [exit_vehicle_from_parking_lot 3] is invalid\n";
    final String NO_COMMANDS_TO_EXECUTE_ERROR_MESSAGE = "No commands present to execute\n";
    final String CREATE_PARKING_LOT_FIRST_ERROR_MESSAGE = "Create_parking_lot must be the first command\n";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testEmptyCommand() {
        commandExecutor.addCommand("");
        assertEquals(COMMAND_INVALID_ERROR_MESSAGE, outContent.toString());
    }

    @Test
    public void testNonExistentCommand() {
        commandExecutor.addCommand("exit_vehicle_from_parking_lot 3");
        assertEquals(NON_EXISTENT_COMMAND_ERROR_MESSAGE, outContent.toString());
    }

    @Test
    public void testEmptyCommandsExecution() {
        commandExecutor.executeCommands();
        assertEquals(NO_COMMANDS_TO_EXECUTE_ERROR_MESSAGE, outContent.toString());
    }

    @Test
    public void testParkingBeforeCreatingParkingLot() {
        commandExecutor.addCommand("Park KA-01-HH-1234 driver_age 21");
        commandExecutor.executeCommands();
        assertEquals(CREATE_PARKING_LOT_FIRST_ERROR_MESSAGE, outContent.toString());
    }

    @Test
    public void testCreateParkLeaveVehicle() {
        String CREATE_PARK_LEAVE_MESSAGE = "Created parking of 6 slots\n" +
                "Car with vehicle registration number \"KA-01-HH-1234\" has been parked at slot number1\n" +
                "Slot number 1 vacated, the car with vehicle registration number \"KA-01-HH-1234\" left the space, the driver of the car was of age 21\n";
        commandExecutor.addCommand("Create_parking_lot 6");
        commandExecutor.addCommand("Park KA-01-HH-1234 driver_age 21");
        commandExecutor.addCommand("Leave 1");
        commandExecutor.executeCommands();
        assertEquals(CREATE_PARK_LEAVE_MESSAGE, outContent.toString());
    }
}
