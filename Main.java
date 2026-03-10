// Name: Logan Mooneyham
// Date: 3/9/26
// Course: CPSC-39
// This is the main class to read all the car data

import java.util.*;
import java.io.*;

public class Main {

    // This reads the CSV file and stores cars in an ArrayList
    public static ArrayList<Car> loadCars(String filename) {

        ArrayList<Car> cars = new ArrayList<>();

        try {

            Scanner file = new Scanner(new File(filename));

            // Skip the header row
            file.nextLine();

            while (file.hasNextLine()) {

                String line = file.nextLine();

                String[] parts = line.split(",");

                // Skip any bad rows
                if (parts.length != 7) continue;

                int id = Integer.parseInt(parts[0]);
                String brand = parts[1];
                String model = parts[2];
                int year = Integer.parseInt(parts[3]);
                String fuel = parts[4];
                String color = parts[5];
                double mileage = Double.parseDouble(parts[6]);

                // This will add new car object
                cars.add(new Car(id, brand, model, year, fuel, color, mileage));
            }

            file.close();

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        System.out.println("Cars loaded: " + cars.size());

        return cars;
    }

    // This is a SELECTION SORT by brand
    public static void selectionSortBrand(ArrayList<Car> list) {

        for (int i = 0; i < list.size() - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < list.size(); j++) {

                if (list.get(j).getBrand()
                        .compareToIgnoreCase(list.get(minIndex).getBrand()) < 0) {

                    minIndex = j;
                }
            }

            // Swap
            Car temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }

        // Show the first 10 cars
        System.out.println("\nFirst 10 cars:");

        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }
    }

    // This is a BINARY SEARCH for the brand
    public static int binarySearchBrand(ArrayList<Car> list, String target) {

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            String brand = list.get(mid).getBrand();

            int compare = brand.compareToIgnoreCase(target);

            if (compare == 0)
                return mid;

            else if (compare < 0)
                low = mid + 1;

            else
                high = mid - 1;
        }

        return -1;
    }

    // This will show statistics
    public static void showStats(ArrayList<Car> list) {

        double totalMileage = 0;

        int petrol = 0;
        int diesel = 0;
        int electric = 0;
        int hybrid = 0;

        for (Car c : list) {

            totalMileage += c.getMileage();

            String fuel = c.getFuelType().toLowerCase();

            if (fuel.equals("petrol")) petrol++;
            else if (fuel.equals("diesel")) diesel++;
            else if (fuel.equals("electric")) electric++;
            else if (fuel.equals("hybrid")) hybrid++;
        }

        double average = totalMileage / list.size();

        System.out.println("\nAverage Mileage: " + average);

        System.out.println("\nFuel Counts:");
        System.out.println("Petrol: " + petrol);
        System.out.println("Diesel: " + diesel);
        System.out.println("Electric: " + electric);
        System.out.println("Hybrid: " + hybrid);
    }

    public static void main(String[] args) {

        // Load the cars
        ArrayList<Car> cars = loadCars("Car_Data.csv");

        // Create a working list
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\nCar Data Analyzer");
            System.out.println("1. Sort by Brand");
            System.out.println("2. Search by Brand");
            System.out.println("3. Show Stats");
            System.out.println("4. Exit");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {

                selectionSortBrand(working);

            } else if (choice == 2) {

                System.out.print("Enter brand: ");
                String brand = input.nextLine();

                int index = binarySearchBrand(working, brand);

                if (index != -1)
                    System.out.println(working.get(index));
                else
                    System.out.println("Brand not found.");

            } else if (choice == 3) {

                showStats(working);

            } else if (choice == 4) {

                System.out.println("Program ended.");
                break;
            }
        }
    }
}