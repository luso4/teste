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


        for (int i = 1; i <= 2; i++) {
            long time = 300; 
            double credit = 1;
            //Change too double to allow the math in decimals - 40381
            double timeCredit = time/15;
            timeCredit = (double) (timeCredit * 0.1); 
            timeCredit = (double) (credit - timeCredit);
            timeCredit = (double) (timeCredit / 0.1); 
            timeCredit = (long)timeCredit*15;
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

        // Contabilizar o tempo de estacionamento e o valor a pagar (considera que a cada 15 min o aluno paga 0.10 €)
        String dataAtual = LocalDate.now().toString();
        Random random = new Random();
        int tempoEstacionamentoMinutos = random.nextInt(240); // Tempo aleatório - máximo 240 minutos
        double valorPago = (tempoEstacionamentoMinutos / 15) * 0.10; //valor a pagar  (0,10 € por cada 15 minutos)

        //O tempo de estacionamento deve ser guardado num ficheiro bem como o valor pago pelo aluno
        try (FileWriter file = new FileWriter("data/" + inputId + ".txt", true)) {
            file.write(dataAtual +"#"+tempoEstacionamentoMinutos+"#"+String.format("%.2f", valorPago) + "\n");
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
                currentTimeHours = currentTime/60;
                currentTimeMinutes = currentTime % 60;
                System.out.println("Time: " + currentTimeHours + " Hours : " + currentTimeMinutes + " Minutes");
                System.out.println("Credit: " + currentCredit + " €"); // Added the € symbol - 40381
                currentTimeHoursAvailable = (long) Math.floor(currentTimeCredit / 60);
                currentTimeMinutesAvailable = (long) currentTimeCredit % 60;
                System.out.println("Time Avaliable: " + currentTimeHoursAvailable + " Hours : " + currentTimeMinutesAvailable + " Minutes");
                System.out.println("Car Park: " + (currentCarPark == 0 ? "No" : "Yes"));
            }
        }
        if (isValid == false)
        {
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
            System.out.println("Your car is already parked.");
        }
        //Add more credit to the student so he can pay - 40381
        if (currentTimeCredit<0)
        {
            System.out.println("You currently have less credit that you need to pay do you wish to add credit");
            System.out.println("Y/N");

        }

        String inputMoreCredit = scanner.next();

        if(inputMoreCredit.equals("Y"))
        {


            //Math to see whats the minimum the user need to add in order to remove the car - 40381

            currentTimeCredit = currentTimeCredit/15;
            currentTimeCredit = (currentTimeCredit * 0.1); 
            currentTimeCredit = (currentTimeCredit - currentCredit);
            currentTimeCredit = currentTimeCredit *(-1);
            //Text that only show up if the credit is negative - 40381
            if(currentTimeCredit>0)
            {
                System.out.println("You need to add " + currentTimeCredit + " €");
            }
            System.out.println("How much credit do you want to deposit");
            int inputNumberCredit = scanner.nextInt();

            for (Student student : students) {
                if (student.id == currentId) { 
                    student.credit = currentCredit + inputNumberCredit; 
                    currentCredit = student.credit;
                }
            }

            System.out.println("Your current credit is :" + currentCredit + " €"); // Added the € symbol - 40381
        }
       
    }
}
