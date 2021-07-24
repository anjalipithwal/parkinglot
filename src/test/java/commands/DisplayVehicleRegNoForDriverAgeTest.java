package commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DisplayVehicleRegNoForDriverAgeTest {
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
    public void testMissingDriverAge() {
        String command = "Vehicle_registration_number_for_driver_of_age";
        DisplayVehicleRegNoForDriverAge displayVehicleRegNoForDriverAge = new DisplayVehicleRegNoForDriverAge(command);
        displayVehicleRegNoForDriverAge.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidNumberOfArguments() {
        String command = "Vehicle_registration_number_for_driver_of_age 21 22 23";
        DisplayVehicleRegNoForDriverAge displayVehicleRegNoForDriverAge = new DisplayVehicleRegNoForDriverAge(command);
        displayVehicleRegNoForDriverAge.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidDriverAge() {
        String command = "Vehicle_registration_number_for_driver_of_age 10";
        DisplayVehicleRegNoForDriverAge displayVehicleRegNoForDriverAge = new DisplayVehicleRegNoForDriverAge(command);
        displayVehicleRegNoForDriverAge.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }
}
