import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      
        System.out.println("Please insert your user id to enter");

        int[] ids = new int[50000];

        for (int i = 1; i <= 49999; i++) {
            ids[i] = i;
        }

        Scanner scanner = new Scanner(System.in);
        int inputId = scanner.nextInt();

        boolean isValid = false;
        for (int id : ids) {
            if (id == inputId) {
                isValid = true;
                break; // Exit the loop as soon as a match is found
            }
        }

        if (isValid) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

        scanner.close();
    }
}