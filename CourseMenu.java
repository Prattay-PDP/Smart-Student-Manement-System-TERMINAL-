import java.util.*;
import java.io.*;

public class CourseMenu {
    static ArrayList<String> courses = new ArrayList<>();
    static final String FILE = "courses.txt";

    static void show(Scanner sc, StudentManager manager, boolean editable) {
        loadCourses();
        while (true) {
            System.out.println("\n----- ONLINE COURSE" + (editable ? "" : " (VIEW ONLY)") + " -----");
            if (editable) {
                System.out.println("1. Add Course");
                System.out.println("2. Delete Course");
            }
            System.out.println("3. Enroll Student");
            System.out.println("4. View Enrollments");
            System.out.println("5. View Courses");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    if (editable) add(sc); else System.out.println("Invalid Choice!");
                    break;
                case "2":
                    if (editable) delete(sc); else System.out.println("Invalid Choice!");
                    break;
                case "3": enroll(sc, manager); break;
                case "4": viewEnrollments(); break;
                case "5": viewCourses(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void add(Scanner sc) {
        System.out.print("Course Name: "); String name = sc.nextLine();
        System.out.print("Instructor: "); String inst = sc.nextLine();
        System.out.print("Fee: "); String fee = sc.nextLine();
        String entry = name + " | Instructor: " + inst + " | Fee: " + fee;
        courses.add(entry);
        save();
        System.out.println("✅ Course Added");
    }

    static void viewCourses() {
        if (courses.isEmpty()) { System.out.println("No courses found."); return; }
        for (int i = 0; i < courses.size(); i++) System.out.println((i+1) + ". " + courses.get(i));
    }

    static void delete(Scanner sc) {
        viewCourses();
        if (courses.isEmpty()) return;
        System.out.print("Enter course number to delete: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            courses.remove(idx);
            save();
            System.out.println("✅ Deleted");
        } catch (Exception e) { System.out.println("Invalid"); }
    }

    static void enroll(Scanner sc, StudentManager manager) {
        viewCourses();
        if (courses.isEmpty()) return;
        System.out.print("Enter course number: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            String course = courses.get(idx);

            System.out.print("Student ID: ");
            int id = Integer.parseInt(sc.nextLine());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            BufferedWriter bw = new BufferedWriter(new FileWriter("enrollments.txt", true));
            bw.write(s.id+","+s.name+","+course+","+java.time.LocalDate.now());
            bw.newLine();
            bw.close();
            System.out.println("✅ Enrolled: " + s.name + " -> " + course);
        } catch (Exception ex) { System.out.println("Invalid Input"); }
    }

    static void viewEnrollments() {
        try {
            File f = new File("enrollments.txt");
            if (!f.exists()) { System.out.println("No enrollments yet"); return; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                System.out.println("ID: " + d[0] + " | Name: " + d[1] + " | Course: " + d[2] + " | Date: " + (d.length>3?d[3]:""));
            }
            br.close();
        } catch (Exception e) { System.out.println("Error reading enrollments"); }
    }

    static void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (String c : courses) { bw.write(c); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Save Error"); }
    }

    static void loadCourses() {
        courses.clear();
        try {
            File f = new File(FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) courses.add(line);
            br.close();
        } catch (Exception e) { System.out.println("Load Error"); }
    }
}