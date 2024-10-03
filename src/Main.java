import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      
        System.out.println("Please insert your user id to enter");

        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= 49999; i++) {
            long time = System.currentTimeMillis();  
            double credit = Math.random() * 100;
            double timeCredit = time/15;
            timeCredit = timeCredit*0.1;
            timeCredit = credit-timeCredit;
            timeCredit = timeCredit/0.1;
            timeCredit = timeCredit*15;
            
            students.add(new Student(i, time, credit, timeCredit));
        }

        Scanner scanner = new Scanner(System.in);
        int inputId = scanner.nextInt();

        int currentId;
        long currentTime;
        double currentCredit;
        double currentTimeCredit = 0;

        boolean isValid = false;
        for (Student student : students) {
            if (student.id == inputId) {
                isValid = true;
                currentId = student.id;
                currentTime = student.time;
                currentCredit = student.credit;
                currentTimeCredit = student.timeCredit;
                System.out.println("Login successful");
                System.out.println("Time: " + currentTime);
                System.out.println("Price: " + currentCredit);
                System.out.println("Time Avaliable: " + currentTimeCredit);
            }
        }
        if (isValid == false)
        {
            System.out.println("Login failed");
        }
        scanner.close();

        
        //Add more credit to the student so he can pay 
        /*
        if (currentTimeCredit<0)
        {
            System.out.println("You currently have less credit that you need to pay do you wish to add credit");
            System.out.println("Y/N");
        }

        Scanner scannerMoreCredit = new Scanner(System.in);
        String inputMoreCredut = scannerMoreCredit.nextLine();

        if(inputMoreCredut.equals("Y"))
        {
            System.out.println("How much credit do you want to deposit");
        }
       */
    }
}