package commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CreateParkingLotCommandTest {
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
    public void testMissingNumberOfSlots() {
        String command = "Create_parking_lot";
        CreateParkingLotCommand createParkingLotCommand = new CreateParkingLotCommand(command);
        createParkingLotCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testInvalidNumberOfArguments() {
        String command = "Create_parking_lot 10 11 12";
        CreateParkingLotCommand createParkingLotCommand = new CreateParkingLotCommand(command);
        createParkingLotCommand.validate();
        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command), outContent.toString());
    }

    @Test
    public void testRangeOfSlots() {
        // Lower limit: 0, Upper limit: 1000(configurable using variable: MAX_PARKING_LIMIT)
        String command1 = "Create_parking_lot -1";
        CreateParkingLotCommand createParkingLotCommand1 = new CreateParkingLotCommand(command1);
        createParkingLotCommand1.validate();

        String command2 = "Create_parking_lot 2000";
        CreateParkingLotCommand createParkingLotCommand2 = new CreateParkingLotCommand(command2);
        createParkingLotCommand2.validate();

        assertEquals(String.format(INVALID_COMMAND_ERROR_MESSAGE, command1) +
                String.format(INVALID_COMMAND_ERROR_MESSAGE, command2) , outContent.toString());
    }
}
