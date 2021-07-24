package models;

public class Vehicle {
    private String vehicleRegNo;
    private int driverAge;

    public Vehicle(String vehicleRegNo, int driverAge) {
        this.vehicleRegNo = vehicleRegNo;
        this.driverAge = driverAge;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public int getDriverAge() {
        return driverAge;
    }
}
