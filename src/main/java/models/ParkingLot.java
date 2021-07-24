package models;

import java.util.*;

public class ParkingLot {
    private static Stack<Integer> availableSlots;
    private int numberOfSlots;
    private static ParkingLot parkingLot;
    private HashMap<Integer, Vehicle> slotVehicleHashMap;
    String ERROR_MESSAGE = "No parked car matches the query";

    public ParkingLot(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        slotVehicleHashMap = new HashMap<Integer, Vehicle>();
        availableSlots = new Stack<>();
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public static ParkingLot createParkingLot(int numberOfSlots) {
        if(parkingLot == null) {
            parkingLot = new ParkingLot(numberOfSlots);
            System.out.println("Created parking of " + numberOfSlots + " slots");
            for(int i = numberOfSlots; i >= 1; i--) {
                availableSlots.push(i);
            }
        } else {
            System.out.println("Parking lot already exists");
        }
        return parkingLot;
    }

    public void parkVehicleInParkingLot(Vehicle vehicle) {
        if(availableSlots.size() == 0) {
            System.out.println("Parking lot is full");
        } else {

            int newSlot = availableSlots.pop();
            slotVehicleHashMap.put(newSlot, vehicle);
            System.out.println("Car with vehicle registration number \"" + vehicle.getVehicleRegNo() +
                    "\" has been parked at slot number" + newSlot);
        }
    }

    public void vehicleLeavesParkingLot(int slotNumber) {
        if(slotVehicleHashMap.containsKey(slotNumber)) {
            Vehicle vehicleLeaving = slotVehicleHashMap.get(slotNumber);
            slotVehicleHashMap.remove(slotNumber);
            availableSlots.push(slotNumber);

            System.out.println("Slot number " + slotNumber + " vacated, the car with vehicle registration number \"" +
                    vehicleLeaving.getVehicleRegNo() + "\" left the space, the driver of the car was of age " +
                    vehicleLeaving.getDriverAge());
        } else {
            System.out.println("No vehicle is associated with this slot number");
        }
    }

    public void displaySlotsForDriverAge(int driverAge) {
        List<Integer> slotsForAge = new LinkedList<>();
        for(Map.Entry<Integer, Vehicle> entry: slotVehicleHashMap.entrySet()) {
            int slotNumber = entry.getKey();
            Vehicle vehicle = entry.getValue();
            int age = vehicle.getDriverAge();
            if(age == driverAge) {
                slotsForAge.add(slotNumber);
            }
        }
        if(slotsForAge.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
        } else {
            for(int slot: slotsForAge) {
                System.out.print(slot + " ");
            }
            System.out.println();
        }
    }

    public void displaySlotForVehicle(String vehicleRegistrationNumber) {
        int slotNumberOfVehicle = -1;
        for(Map.Entry<Integer, Vehicle> entry: slotVehicleHashMap.entrySet()) {
            int slotNumber = entry.getKey();
            Vehicle vehicle = entry.getValue();
            String registrationNumber = vehicle.getVehicleRegNo();
            if(registrationNumber.equals(vehicleRegistrationNumber)) {
                slotNumberOfVehicle = slotNumber;
                break;
            }
        }
        if(slotNumberOfVehicle == -1) {
            System.out.println(ERROR_MESSAGE);
        } else {
            System.out.println(slotNumberOfVehicle);
        }
    }

    public void displayVehiclesForDriverAge(int driverAge) {
        List<String> vehiclesForAge = new LinkedList<>();
        for(Vehicle vehicle: slotVehicleHashMap.values()) {
            int age = vehicle.getDriverAge();
            String registrationNumber = vehicle.getVehicleRegNo();
            if(age == driverAge) {
                vehiclesForAge.add(registrationNumber);
            }
        }
        if(vehiclesForAge.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
        } else {
            for(String registrationNumber: vehiclesForAge) {
                System.out.print(registrationNumber + " ");
            }
            System.out.println();
        }
    }
}
