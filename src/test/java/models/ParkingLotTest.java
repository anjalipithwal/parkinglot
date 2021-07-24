package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class ParkingLotTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String PARKING_LOT_EXISTS_MESSAGE = "Parking lot already exists\n";
    private final String PARKING_LOT_FULL_MESSAGE = "Parking lot is full\n";
    private final String VEHICLE_NOT_PRESENT_MESSAGE = "No vehicle is associated with this slot number\n";
    private final String NO_RESULTS_TO_QUERY_MESSAGE = "No parked car matches the query\n";
    @Before
    public void setUpStreams() throws IllegalAccessException, NoSuchFieldException {
        System.setOut(new PrintStream(outContent));
        Field instance = ParkingLot.class.getDeclaredField("parkingLot");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCreatingTwoParkingLots() {
        ParkingLot parkingLot1 = ParkingLot.createParkingLot(10);
        outContent.reset();
        ParkingLot parkingLot2 = ParkingLot.createParkingLot(20);
        assertEquals(PARKING_LOT_EXISTS_MESSAGE, outContent.toString());
    }

    @Test
    public void testParkingCarInFilledParkingLot() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(1);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        Vehicle vehicle2 = new Vehicle("KA-01-HH-1211", 21);
        outContent.reset();
        parkingLot.parkVehicleInParkingLot(vehicle2);
        assertEquals(PARKING_LOT_FULL_MESSAGE, outContent.toString());
    }

    @Test
    public void testLeaveUnparkedVehicle() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        outContent.reset();
        parkingLot.vehicleLeavesParkingLot(1);
        assertEquals(VEHICLE_NOT_PRESENT_MESSAGE, outContent.toString());
    }

    @Test
    public void testDisplaySlotsForDriverAge() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        Vehicle vehicle2 = new Vehicle("KA-01-HH-1211", 21);
        parkingLot.parkVehicleInParkingLot(vehicle2);
        outContent.reset();
        parkingLot.displaySlotsForDriverAge(21);
        assertEquals("1 2 \n", outContent.toString());
    }

    @Test
    public void testEmptyResultDisplaySlotsForDriverAge() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        Vehicle vehicle2 = new Vehicle("KA-01-HH-1211", 21);
        parkingLot.parkVehicleInParkingLot(vehicle2);
        outContent.reset();
        parkingLot.displaySlotsForDriverAge(30);
        assertEquals(NO_RESULTS_TO_QUERY_MESSAGE, outContent.toString());
    }

    @Test
    public void testDisplaySlotForVehicle() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        Vehicle vehicle2 = new Vehicle("KA-01-HH-1211", 21);
        parkingLot.parkVehicleInParkingLot(vehicle2);
        outContent.reset();
        parkingLot.displaySlotForVehicle("KA-01-HH-1211");
        assertEquals("2\n", outContent.toString());
    }

    @Test
    public void testEmptyResultDisplaySlotForVehicle() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        outContent.reset();
        parkingLot.displaySlotForVehicle("KA-01-HH-1200");
        assertEquals(NO_RESULTS_TO_QUERY_MESSAGE, outContent.toString());
    }

    @Test
    public void testDisplayVehiclesForDriverAge() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        Vehicle vehicle2 = new Vehicle("KA-01-HH-1211", 21);
        parkingLot.parkVehicleInParkingLot(vehicle2);
        outContent.reset();
        parkingLot.displayVehiclesForDriverAge(21);
        assertEquals("KA-01-HH-1234 KA-01-HH-1211 \n", outContent.toString());
    }

    @Test
    public void testEmptyResultDisplayVehiclesForDriverAge() {
        ParkingLot parkingLot = ParkingLot.createParkingLot(10);
        Vehicle vehicle1 = new Vehicle("KA-01-HH-1234", 21);
        parkingLot.parkVehicleInParkingLot(vehicle1);
        outContent.reset();
        parkingLot.displayVehiclesForDriverAge(25);
        assertEquals(NO_RESULTS_TO_QUERY_MESSAGE, outContent.toString());
    }
}
