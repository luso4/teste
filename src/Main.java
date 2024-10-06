import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Please insert your user id to enter");

        //Create data for the student - 40381
        List<Student> students = new ArrayList<>();

        // Maximum capacity of 500 cars in the parking lot - 42872
        int maxCarCapacity = 500;
        int carsInPark = 0;  // Counter for currently parked cars - 42872


        for (int i = 1; i <= 800; i++) {
            long time = 300;
            double credit = Math.random() * (10 - 1);
            credit = ((int)(credit * 100)) / 100.0;
            //Change too double to allow the math in decimals - 40381
            double timeCredit = time / 15;
            timeCredit = (double) (timeCredit * 0.1);
            timeCredit = (double) (credit - timeCredit);
            timeCredit = (double) (timeCredit / 0.1);
            timeCredit = (long) timeCredit * 15;
            int carPark = (int) (Math.random() * 2);
            // Count how many cars are initially parked - 42872
            if (carPark == 1) {
                carsInPark++;
            }
            students.add(new Student(i, time, credit, timeCredit, carPark));
        }
        // Read the response of the student - 40381
        Scanner scanner = new Scanner(System.in);
        int inputId = scanner.nextInt();

        // Count the parking time and the amount to be paid (considering that every 15 minutes the student pays €0.10) - 43305
        String currentData  = LocalDate.now().toString();
        Random random = new Random();
        int parkingTimeMinutes = random.nextInt(240); // random - máx 240 minutes - 43305
        double amountPaid = (parkingTimeMinutes / 15) * 0.10; //amount to pay (€0.10 for every 15 minutes) - 43305

        //The parking time and the amount paid by the student must be saved in a file- 43305
        try (FileWriter file = new FileWriter("data/" + inputId + ".txt", true)) {
            file.write(currentData  +"#"+parkingTimeMinutes+"#"+String.format("%.2f", amountPaid) + "\n");
        } catch (IOException e) {

        }

        int currentId = 0;
        long currentTime = 0;
        double currentCredit = 0;
        double currentTimeCredit = 0;
        int currentCarPark = 0;
        // Variables to check the time in hours and minutes - 40381
        long currentTimeHours = 0;
        long currentTimeMinutes = 0;
        long currentTimeHoursAvailable = 0;
        long currentTimeMinutesAvailable = 0;

        // Login of the student - 40381
        boolean isValid = false;
        for (Student student : students) {
            if (student.id == inputId) {
                isValid = true;
                currentId = student.id;
                currentTime = student.time;
                currentCredit = student.credit;
                currentTimeCredit = student.timeCredit;
                currentCarPark = student.carPark;
                System.out.println("Login successful");
                //Convert the time from minutes to hours and minutes in order for the student to check propelly the time - 40381
                currentTimeHours = currentTime / 60;
                currentTimeMinutes = currentTime % 60;
                System.out.println("Time: " + currentTimeHours + " Hours : " + currentTimeMinutes + " Minutes");
                System.out.println("Credit: " + currentCredit + " €"); // Added the € symbol - 40381
                currentTimeHoursAvailable = (long) Math.floor(currentTimeCredit / 60);
                currentTimeMinutesAvailable = (long) currentTimeCredit % 60;
                System.out.println("Time Avaliable: " + currentTimeHoursAvailable + " Hours : " + currentTimeMinutesAvailable + " Minutes");
                System.out.println("Car Park: " + (currentCarPark == 0 ? "No" : "Yes"));
            }
        }
        if (isValid == false) {
            System.out.println("Login failed");
            scanner.close();
        }


        //Add the car of the student to the park - 40381


        if (currentCarPark == 0) {
            // Check if there are available parking spots
            if (carsInPark >= maxCarCapacity) {
                System.out.println("The parking lot is full. No more cars can be parked.");
            } else {
                System.out.println("Do you wish to park your car?");
                System.out.println("Y/N");
                String inputCarPark = scanner.next();

                if (inputCarPark.equalsIgnoreCase("Y")) {
                    currentCarPark = 1;
                    carsInPark++;  // Increment the number of parked cars
                    System.out.println("The car was parked.");
                } else {
                    System.out.println("OK");
                }
            }
        } else {
            // If the car is already parked, the user have the option to remove it - 42872
            if (currentTimeCredit >= 0) {
                System.out.println("Your car is already parked. Do you wish to remove your car from the park?");
                System.out.println("Y/N");
                String inputRemoveCarPark = scanner.next();
                if (inputRemoveCarPark.equalsIgnoreCase("Y")) {
                    currentCarPark = 0;
                    carsInPark--;
                    for (Student student : students) {
                        if (student.id == currentId) {
                            student.carPark = 0;
                        }
                    }
                    System.out.println("The car was removed from the park.");
                } else {
                    System.out.println("OK");
                }
            } else {
                // If the student doesn't have enough credit - 42872
                System.out.println("You don't have enough credit to remove your car.");
                System.out.println("Do you wish to add credit? Y/N");
                String inputAddCredit = scanner.next();

                if (inputAddCredit.equalsIgnoreCase("Y")) {
                    //Math to see whats the minimum the user need to add in order to remove the car - 40381
                    currentTimeCredit = currentTimeCredit / 15;
                    currentTimeCredit = (currentTimeCredit * 0.1);
                    currentTimeCredit = (currentTimeCredit - currentCredit);
                    currentTimeCredit = currentTimeCredit * (-1); //Text that only show up if the credit is negative - 40381
                    currentTimeCredit = ((int)(currentTimeCredit * 100)) / 100.0;
                    System.out.println("You need to add " + currentTimeCredit + " €");
                    System.out.println("How much credit do you want to deposit?");
                    int inputNumberCredit = scanner.nextInt();


                    // Update the student's credit - 42872
                    for (Student student : students) {
                        if (student.id == currentId) {
                            student.credit = currentCredit + inputNumberCredit;
                            currentCredit = student.credit;
                        }
                    }

                    System.out.println("Your current credit is: " + currentCredit);

                    System.out.println("You now have enough credit. Do you wish to remove your car from the parking?");
                    System.out.println("Y/N");
                    String inputRemoveCar = scanner.next();

                    if (inputRemoveCar.equalsIgnoreCase("Y")) {
                        currentCarPark = 0;
                        carsInPark--;  // Decrement the number of parked cars
                        for (Student student : students) {
                            if (student.id == currentId) {
                                student.carPark = 0;
                            }
                        }
                        System.out.println("The car was removed from the parking.");
                    } else {
                        System.out.println("OK");
                    }
                }
            }


        }


        //See the student that pay the most for parking - 43305
        //Read a text file containing student parking payment information. - 43305
        //Format: yyyy-mm-dd#minutes#amount paid - 43305
        Student topStudent = null;
        double maxSpending = 0.0;
        for (Student student : students) {
            String fileName = "data/" + student.id + ".txt";
            File file = new File(fileName);
            if (file.exists()) {
                double totalPayments = 0.0;
                try (Scanner scanner_file = new Scanner(file)) {
                    while (scanner_file.hasNextLine()) {
                        String line = scanner_file.nextLine();
                        String[] parts = line.split("#");
                        totalPayments += Double.parseDouble(parts[2]);
                    }
                } catch (IOException e) {

                }
                if (totalPayments > maxSpending) {
                    maxSpending = totalPayments;
                    topStudent = student;
                }
            }
        }
        if (topStudent != null) {
            System.out.println("The student who spent the most was " + topStudent.id + ". Total spent de €" + String.format("%.2f", maxSpending));
        } else {
            System.out.println("No students were found with payments.");
        }


    }
}
