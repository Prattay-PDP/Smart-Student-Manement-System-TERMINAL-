import java.util.*;
import java.io.*;

public class ReportMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- SYSTEM REPORTS -----");
            System.out.println("1. Student Report");
            System.out.println("2. Fee Report");
            System.out.println("3. Finance Report");
            System.out.println("4. Attendance Report");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": studentReport(manager); break;
                case "2": feeReport(manager); break;
                case "3": financeReport(); break;
                case "4": attendanceReport(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void studentReport(StudentManager manager) {
        System.out.println("Total Students  : " + manager.totalStudents());
        System.out.println(String.format("Average CGPA    : %.2f", manager.avgCGPA()));
        System.out.println("Low Attendance  : " + manager.lowAttendance() + " students");
        if (manager.topper() != null)
            System.out.println("Top Student     : " + manager.topper().name + " (CGPA: " + manager.topper().cgpa + ")");

        System.out.println(String.format("\n%-6s %-20s %-12s %-8s %-8s %-8s","ID","NAME","DEPT","CGPA","ATT%","STATUS"));
        System.out.println("=".repeat(70));
        for (Student s : manager.list) {
            System.out.println(String.format("%-6d %-20s %-12s %-8.2f %-8.1f %-8s",
                    s.id,s.name,s.dept,s.cgpa,s.getAttPer(),s.status));
        }
    }

    static void feeReport(StudentManager manager) {
        System.out.println(String.format("Total Due (all students) : %.2f\n", manager.totalDue()));
        System.out.println(String.format("%-6s %-20s %-10s %-10s %-10s","ID","NAME","TOTAL","PAID","DUE"));
        System.out.println("=".repeat(60));
        for (Student s : manager.list) {
            System.out.println(String.format("%-6d %-20s %-10.0f %-10.0f %-10.0f",
                    s.id,s.name,s.totalFee,s.paidFee,s.dueFee()));
        }
    }

    static void financeReport() {
        StringBuilder sb = new StringBuilder();
        double inc = readSum("income.txt", sb, "INCOME");
        double exp = readSum("expense.txt", sb, "EXPENSE");
        System.out.println(sb.toString());
        System.out.println(String.format("Total Income  : %.2f", inc));
        System.out.println(String.format("Total Expense : %.2f", exp));
        System.out.println(String.format("Net Balance   : %.2f", inc-exp));
    }

    static double readSum(String filename, StringBuilder sb, String label) {
        double total = 0;
        sb.append("--- "+label+" ---\n");
        try {
            File f = new File(filename);
            if (!f.exists()) { sb.append("No records.\n"); return 0; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 2) {
                    sb.append(d[0]+" : "+d[1]+"\n");
                    total += Double.parseDouble(d[1]);
                }
            }
            br.close();
        } catch (Exception e) { sb.append("Error reading file\n"); }
        return total;
    }

    static void attendanceReport() {
        System.out.println(String.format("%-5s %-20s %-10s %-12s","ID","NAME","STATUS","DATE"));
        System.out.println("=".repeat(55));
        try {
            File f = new File("staff_attendance.txt");
            if (!f.exists()) { System.out.println("No attendance records found."); return; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 4)
                    System.out.println(String.format("%-5s %-20s %-10s %-12s",d[0],d[1],d[2],d[3]));
            }
            br.close();
        } catch (Exception e) { System.out.println("Error reading attendance"); }
    }
}