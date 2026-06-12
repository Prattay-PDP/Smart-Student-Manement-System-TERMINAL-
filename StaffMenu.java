import java.util.*;
import java.io.*;

public class StaffMenu {
    static ArrayList<String> staff = new ArrayList<>();
    static final String FILE = "staff.txt";

    static void show(Scanner sc) {
        loadStaff();
        while (true) {
            System.out.println("\n----- HR / STAFF -----");
            System.out.println("1. Add Staff");
            System.out.println("2. Delete Staff");
            System.out.println("3. Mark Attendance");
            System.out.println("4. View Attendance");
            System.out.println("5. View Staff");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": add(sc); break;
                case "2": delete(sc); break;
                case "3": markAtt(sc); break;
                case "4": viewAtt(); break;
                case "5": viewStaff(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void add(Scanner sc) {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Role/Position: "); String role = sc.nextLine();
        System.out.print("Mobile: "); String mobile = sc.nextLine();
        System.out.print("Salary: "); String salary = sc.nextLine();

        int id = staff.size() + 1;
        String entry = id + " | " + name + " | " + role + " | " + mobile + " | Salary: " + salary;
        staff.add(entry);
        save();
        System.out.println("✅ Staff Added");
    }

    static void viewStaff() {
        if (staff.isEmpty()) { System.out.println("No staff found."); return; }
        for (int i = 0; i < staff.size(); i++) System.out.println((i+1) + ". " + staff.get(i));
    }

    static void delete(Scanner sc) {
        viewStaff();
        if (staff.isEmpty()) return;
        System.out.print("Enter staff number to delete: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            staff.remove(idx);
            save();
            System.out.println("✅ Deleted");
        } catch (Exception e) { System.out.println("Invalid"); }
    }

    static void markAtt(Scanner sc) {
        viewStaff();
        if (staff.isEmpty()) return;
        System.out.print("Enter staff number: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            String s = staff.get(idx);
            String[] parts = s.split("\\|");

            System.out.print("Status (Present/Absent/Leave/Late): ");
            String status = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter("staff_attendance.txt", true));
            bw.write(parts[0].trim()+","+parts[1].trim()+","+status+","+java.time.LocalDate.now());
            bw.newLine();
            bw.close();
            System.out.println("✅ Attendance Marked: " + status);
        } catch (Exception e) { System.out.println("Error"); }
    }

    static void viewAtt() {
        System.out.println(String.format("%-5s %-20s %-10s %-12s","ID","NAME","STATUS","DATE"));
        System.out.println("=".repeat(55));
        try {
            File f = new File("staff_attendance.txt");
            if (!f.exists()) { System.out.println("No attendance records"); return; }
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

    static void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (String s : staff) { bw.write(s); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Save Error"); }
    }

    static void loadStaff() {
        if (!staff.isEmpty()) return;
        try {
            File f = new File(FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) staff.add(line);
            br.close();
        } catch (Exception e) { System.out.println("Load Error"); }
    }
}