import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      
        System.out.println("Please insert your user id to enter");

        //Create the data of the student
        //Test the millestone
        //Test the millestone2
        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= 49999; i++) {
            long time = System.currentTimeMillis();  
            double credit = Math.random() * 100;
            double timeCredit = time/15;
            timeCredit = timeCredit*0.1;
            timeCredit = credit-timeCredit;
            timeCredit = timeCredit/0.1;
            timeCredit = timeCredit*15;
            int carPark = (int) (Math.random() * 2);
            students.add(new Student(i, time, credit, timeCredit, carPark));
        }

        Scanner scanner = new Scanner(System.in);
        int inputId = scanner.nextInt();

        int currentId = 0;
        long currentTime;
        double currentCredit = 0;
        double currentTimeCredit = 0;
        int currentCarPark = 0;

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
                System.out.println("Time: " + currentTime);
                System.out.println("Credit: " + currentCredit);
                System.out.println("Time Avaliable: " + currentTimeCredit);
                System.out.println("Car Park: " + (currentCarPark == 0 ? "No" : "Yes"));
            }
        }
        if (isValid == false)
        {
            System.out.println("Login failed");
            scanner.close();
        }
       

        //Add the car of the student to the park
        
       
        if (currentCarPark == 0)
        {
            
            System.out.println("Do you wish to park your car?");
            System.out.println("Y/N");
            String inputCarPark = scanner.next();
            
            if (inputCarPark.equalsIgnoreCase("Y"))
            {
                currentCarPark = 1;
                System.out.println("The Car was park");
            }
            else
            {
                currentCarPark = 0;
                System.out.println("OK");
            }
        }
        else
        {
            System.out.println("All Done For Now");
        }

        //Add more credit to the student so he can pay 
        
        if (currentTimeCredit<0)
        {
            System.out.println("You currently have less credit that you need to pay do you wish to add credit");
            System.out.println("Y/N");
        }

        String inputMoreCredit = scanner.next();

        if(inputMoreCredit.equals("Y"))
        {
            System.out.println("How much credit do you want to deposit");
            int inputNumberCredit = scanner.nextInt();

            for (Student student : students) {
                if (student.id == currentId) { 
                    student.credit = currentCredit + inputNumberCredit; 
                    currentCredit = student.credit;
                }
            }

            System.out.println("Your current credit is :" + currentCredit);
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