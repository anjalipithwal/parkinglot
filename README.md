# Parking Lot System

## Prerequisites
1. Java 1.7
2. Maven 3.6.3
3. An IDE to run the project

## Project Structure

### Implementation
- The program execution begins in ParkingLotSystem.java. Here we read all the commands from the input.txt file and add these as commands into the CommandExecutor.
- After adding all the commands, we can execute them one by one.
- Command is an interface and is implemented in the following classes:
  1. CreateParkingLotCommand: "Create_parking_lot"
  2. ParkVehicleCommand: "Park"
  3. DisplaySlotsDriverAgeCommand: "Slot_numbers_for_driver_of_age"
  4. DisplaySlotForVehicleCommand: "Slot_number_for_car_with_number"
  5. LeaveSlotCommand: "Leave"
  6. DisplayVehicleRegNoForDriverAge: "Vehicle_registration_number_for_driver_of_age"
- Each of these command types have a validate method, to validate the input command, and an execute method, to execute the command.
- ParkingLot class contains all the implementation for these commands
- Vehicle is a data structure that holds the vehicle registration number and the age of the driver

### Testing
- junit version 4.12 is used to add all the corner cases for each class file that is added.

To begin code execution, please run the main method in the class ParkingLotSystem. Input commands can be modified in ./src/main/input.txt
