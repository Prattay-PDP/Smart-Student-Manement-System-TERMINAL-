import java.util.Scanner;
 
public class Input {
 
    static Scanner sc = new Scanner(System.in);
 
    static String str(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }
 
    static int num(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (Exception e) {
                System.out.println("  ❌ Invalid! Enter a number.");
            }
        }
    }
 
    static double decimal(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (Exception e) {
                System.out.println("  ❌ Invalid! Enter a decimal number.");
            }
        }
    }
 
    static void pause() {
        System.out.print("\nPress ENTER to continue...");
        sc.nextLine();
    }
 
    static void line() {
        System.out.println("=".repeat(50));
    }
 
    static void header(String title) {
        System.out.println();
        line();
        System.out.println("  " + title);
        line();
    }
}