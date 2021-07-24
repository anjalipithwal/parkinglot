package commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ParkVehicleCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final String INVALID_COMMAND_ERROR_MESSAGE = "Command: [%s] is invalid\n";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMissingArguments() {
        String command = "Park";
        ParkVehicleCommand parkVehicleCommand = new ParkVehicleCommand(command);
        parkVehicleCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testMissingDriverAge() {
        String command = "Park PB-01-TG-2341 driver_age";
        ParkVehicleCommand parkVehicleCommand = new ParkVehicleCommand(command);
        parkVehicleCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidAge() {
        String command = "Park PB-01-TG-2341 driver_age 10";
        ParkVehicleCommand parkVehicleCommand = new ParkVehicleCommand(command);
        parkVehicleCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }
}
