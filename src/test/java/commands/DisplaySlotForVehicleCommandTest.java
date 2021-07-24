package commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DisplaySlotForVehicleCommandTest {
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
    public void testMissingRegNo() {
        String command = "Slot_number_for_car_with_number";
        DisplaySlotForVehicleCommand displaySlotForVehicleCommand = new DisplaySlotForVehicleCommand(command);
        displaySlotForVehicleCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidNumberOfArguments() {
        String command = "Slot_number_for_car_with_number KA-01-HH-1234 KA-02-HH-1234";
        DisplaySlotForVehicleCommand displaySlotForVehicleCommand = new DisplaySlotForVehicleCommand(command);
        displaySlotForVehicleCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }
}
