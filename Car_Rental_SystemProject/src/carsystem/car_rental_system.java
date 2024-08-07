package carsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class car_rental_system 
{
	 private List<car> cars;
	    private List<customer> customers;
	    private List<rental> rentals;

	    public car_rental_system() {
	        cars = new ArrayList<>();
	        customers = new ArrayList<>();
	        rentals = new ArrayList<>();
	    }

	    public void addCar(car car) {
	        cars.add(car);
	    }

	    public void addCustomer(customer customer) {
	        customers.add(customer);
	    }

	    public void rentCar(car car, customer customer, int days) {
	        if (car.isAvailable()) {
	            car.rent();
	            rentals.add(new rental(car, customer, days));

	        } else {
	            System.out.println("Car is not available for rent.");
	        }
	    }

	    public void returnCar(car car) {
	        car.returnCar();
	        rental rentalToRemove = null;
	        for (rental rental : rentals) {
	            if (rental.getCar() == car) {
	                rentalToRemove = rental;
	                break;
	            }
	        }
	        if(rentalToRemove != null) {
	            rentals.remove(rentalToRemove);

	        } else {
	            System.out.println("Car was not rented.");
	        }
	    }

	    public void menu() {
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("===== Car Rental System =====");
	            System.out.println("1. Rent a Car");
	            System.out.println("2. Return a Car");
	            System.out.println("3. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            if (choice == 1) {
	                System.out.println("\n == Rent a Car == \n");
	                System.out.print("Enter your name: ");
	                String customerName = scanner.nextLine();

	                System.out.println("\n Available Cars:");
	                for (car car : cars) {
	                    if (car.isAvailable()) {
	                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
	                    }
	                }

	                System.out.print("\n Enter the car ID you want to rent: ");
	                String carId = scanner.nextLine();

	                System.out.print("Enter the number of days for rental: ");
	                int rentalDays = scanner.nextInt();
	                scanner.nextLine(); // Consume newline

	                customer newCustomer = new customer("CUS" + (customers.size() + 1), customerName);
	                addCustomer(newCustomer);

	                car selectedCar = null;
	                for (car car : cars) {
	                    if (car.getCarId().equals(carId) && car.isAvailable()) {
	                        selectedCar = car;
	                        break;
	                    }
	                }

	                if (selectedCar != null) {
	                    double totalPrice = selectedCar.calculatePrice(rentalDays);
	                    System.out.println("\n== Rental Information ==\n");
	                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
	                    System.out.println("Customer Name: " + newCustomer.getName());
	                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
	                    System.out.println("Rental Days: " + rentalDays);
	                    System.out.printf("Total Price: $%.2f%n", totalPrice);

	                    System.out.print("\nConfirm rental (Y/N): ");
	                    String confirm = scanner.nextLine();

	                    if (confirm.equalsIgnoreCase("Y")) {
	                        rentCar(selectedCar, newCustomer, rentalDays);
	                        System.out.println("\n Car rented successfully.");
	                    } else {
	                        System.out.println("\nRental canceled.");
	                    }
	                } else {
	                    System.out.println("\nInvalid car selection or car not available for rent.");
	                }
	            } else if (choice == 2) {
	                System.out.println("\n== Return a Car ==\n");
	                System.out.print("Enter the car ID you want to return: ");
	                String carId = scanner.nextLine();

	                car carToReturn = null;
	                for (car car : cars) {
	                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
	                        carToReturn = car;
	                        break;
	                    }
	                }

	                if (carToReturn != null) {
	                    customer customer = null;
	                    for (rental rental : rentals) {
	                        if (rental.getCar() == carToReturn) {
	                            customer = rental.getCustomer();
	                            break;
	                        }
	                    }

	                    if (customer != null) {
	                        returnCar(carToReturn);
	                        System.out.println("Car returned successfully by " + customer.getName());
	                    } else {
	                        System.out.println("Car was not rented or rental information is missing.");
	                    }
	                } else {
	                    System.out.println("Invalid car ID or car is not rented.");
	                }
	            } else if (choice == 3) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }

	        System.out.println("\n Thank you for using the Car Rental System!");
	    }
}
