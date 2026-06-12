import java.util.*;
import java.io.*;

public class ExamMenu {
    static ArrayList<String> exams = new ArrayList<>();
    static final String FILE = "exams.txt";

    static void show(Scanner sc, StudentManager manager, boolean editable) {
        loadExams();
        while (true) {
            System.out.println("\n----- ONLINE EXAM" + (editable ? "" : " (VIEW ONLY)") + " -----");
            if (editable) {
                System.out.println("1. Create Exam");
                System.out.println("2. Delete Exam");
                System.out.println("3. Submit Marks");
            }
            System.out.println("4. View Results");
            System.out.println("5. View Exams");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1":
                    if (editable) create(sc); else System.out.println("Invalid Choice!");
                    break;
                case "2":
                    if (editable) delete(sc); else System.out.println("Invalid Choice!");
                    break;
                case "3":
                    if (editable) submit(sc, manager); else System.out.println("Invalid Choice!");
                    break;
                case "4": results(); break;
                case "5": viewExams(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void create(Scanner sc) {
        System.out.print("Exam Name: "); String name = sc.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = sc.nextLine();
        System.out.print("Total Marks: "); String tm = sc.nextLine();
        String entry = name + " | Date: " + date + " | Total Marks: " + tm;
        exams.add(entry);
        save();
        System.out.println("✅ Exam Created");
    }

    static void viewExams() {
        if (exams.isEmpty()) { System.out.println("No exams found."); return; }
        for (int i = 0; i < exams.size(); i++) System.out.println((i+1) + ". " + exams.get(i));
    }

    static void delete(Scanner sc) {
        viewExams();
        if (exams.isEmpty()) return;
        System.out.print("Enter exam number to delete: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            exams.remove(idx);
            save();
            System.out.println("✅ Deleted");
        } catch (Exception e) { System.out.println("Invalid"); }
    }

    static void submit(Scanner sc, StudentManager manager) {
        viewExams();
        if (exams.isEmpty()) return;
        System.out.print("Enter exam number: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            String exam = exams.get(idx);

            System.out.print("Student ID: ");
            int id = Integer.parseInt(sc.nextLine());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("Marks Obtained: ");
            String marks = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter("exam_results.txt", true));
            bw.write(s.id+","+s.name+","+exam+","+marks+","+java.time.LocalDate.now());
            bw.newLine();
            bw.close();
            System.out.println("✅ Marks Submitted");
        } catch (Exception ex) { System.out.println("Invalid Input"); }
    }

    static void results() {
        System.out.println(String.format("%-6s %-20s %-30s %-8s %-12s","ID","NAME","EXAM","MARKS","DATE"));
        System.out.println("=".repeat(90));
        try {
            File f = new File("exam_results.txt");
            if (!f.exists()) { System.out.println("No results yet"); return; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 5)
                    System.out.println(String.format("%-6s %-20s %-30s %-8s %-12s",d[0],d[1],d[2],d[3],d[4]));
            }
            br.close();
        } catch (Exception e) { System.out.println("Error reading results"); }
    }

    static void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (String e : exams) { bw.write(e); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Save Error"); }
    }

    static void loadExams() {
        exams.clear();
        try {
            File f = new File(FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) exams.add(line);
            br.close();
        } catch (Exception e) { System.out.println("Load Error"); }
    }
}