import java.util.*;
import java.io.*;

public class FinanceMenu {
    static void show(Scanner sc) {
        while (true) {
            System.out.println("\n----- INCOME & EXPENSE -----");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Report");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": addEntry(sc, "INCOME", "income.txt"); break;
                case "2": addEntry(sc, "EXPENSE", "expense.txt"); break;
                case "3": viewReport(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void addEntry(Scanner sc, String type, String file) {
        System.out.print(type + " Title: ");
        String title = sc.nextLine();
        System.out.print("Amount: ");
        try {
            double amt = Double.parseDouble(sc.nextLine());
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(title + "," + amt + "," + java.time.LocalDate.now());
            bw.newLine();
            bw.close();
            System.out.println("✅ " + type + " Added");
        } catch (Exception ex) { System.out.println("Invalid Amount"); }
    }

    static void viewReport() {
        StringBuilder sb = new StringBuilder();
        double inc = sumFile("income.txt", sb, "INCOME");
        double exp = sumFile("expense.txt", sb, "EXPENSE");
        System.out.println(sb.toString());
        System.out.println(String.format("Total Income  : %.2f", inc));
        System.out.println(String.format("Total Expense : %.2f", exp));
        System.out.println(String.format("Net Balance   : %.2f", inc-exp));
    }

    static double sumFile(String filename, StringBuilder sb, String label) {
        double total = 0;
        sb.append("--- " + label + " ---\n");
        try {
            File f = new File(filename);
            if (!f.exists()) { sb.append("No records.\n"); return 0; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 2) {
                    sb.append(d[0] + " : " + d[1] + "\n");
                    total += Double.parseDouble(d[1]);
                }
            }
            br.close();
        } catch (Exception e) { sb.append("Error reading file\n"); }
        return total;
    }
}