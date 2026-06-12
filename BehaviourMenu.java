import java.util.*;
import java.io.*;

public class BehaviourMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- BEHAVIOUR & RESULT -----");
            System.out.println("1. Add Result");
            System.out.println("2. Add Behaviour Note");
            System.out.println("3. View Records");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": addResult(sc, manager); break;
                case "2": addBehaviour(sc, manager); break;
                case "3": viewRecords(sc); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void addResult(Scanner sc, StudentManager manager) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("Subject: "); String sub = sc.nextLine();
            System.out.print("Marks: "); String marks = sc.nextLine();
            System.out.print("Grade: "); String grade = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt", true));
            bw.write(s.id+","+s.name+","+sub+","+marks+","+grade+","+java.time.LocalDate.now());
            bw.newLine();
            bw.close();
            System.out.println("✅ Result Added");
        } catch (Exception ex) { System.out.println("Invalid Input"); }
    }

    static void addBehaviour(Scanner sc, StudentManager manager) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("Rating (Excellent/Good/Average/Needs Improvement/Poor): ");
            String rating = sc.nextLine();
            System.out.print("Note: ");
            String note = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter("behaviour.txt", true));
            bw.write(s.id+","+s.name+","+rating+","+note+","+java.time.LocalDate.now());
            bw.newLine();
            bw.close();
            System.out.println("✅ Behaviour Note Added");
        } catch (Exception ex) { System.out.println("Invalid Input"); }
    }

    static void viewRecords(Scanner sc) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("===== RESULTS =====");
            readFile("results.txt", id, new String[]{"ID","Name","Subject","Marks","Grade","Date"});

            System.out.println("\n===== BEHAVIOUR =====");
            readFile("behaviour.txt", id, new String[]{"ID","Name","Rating","Note","Date"});
        } catch (Exception ex) { System.out.println("Invalid ID"); }
    }

    static void readFile(String filename, int id, String[] cols) {
        try {
            File f = new File(filename);
            if (!f.exists()) { System.out.println("No records."); return; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line; boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length > 0 && d[0].equals(String.valueOf(id))) {
                    found = true;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < d.length; i++) sb.append(cols[i]).append(": ").append(d[i]).append("  ");
                    System.out.println(sb);
                }
            }
            br.close();
            if (!found) System.out.println("No records found.");
        } catch (Exception e) { System.out.println("Error reading file"); }
    }
}