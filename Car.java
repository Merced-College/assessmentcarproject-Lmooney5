// Name: Logan Mooneyham
// Date: 3/9/26
// Course: CPSC-39
// This is the car class for all my car data

public class Car {

    // These are the car data fields
    private int carID;
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private String color;
    private double mileage;

    // This is a constructor that creates a car object
    public Car(int carID, String brand, String model, int year,
               String fuelType, String color, double mileage) {

        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.color = color;
        this.mileage = mileage;
    }

    // These are getter methods
    public int getCarID() { return carID; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getFuelType() { return fuelType; }
    public String getColor() { return color; }
    public double getMileage() { return mileage; }

    // This will print car information
    public String toString() {
        return carID + " | " + brand + " | " + model + " | " +
               year + " | " + fuelType + " | " + color + " | " + mileage;