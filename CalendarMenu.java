import java.util.*;
import java.io.*;

public class CalendarMenu {
    static ArrayList<String> events = new ArrayList<>();
    static final String FILE = "calendar.txt";

    static void show(Scanner sc, boolean editable) {
        loadEvents();
        while (true) {
            System.out.println("\n----- ANNUAL CALENDAR" + (editable ? "" : " (VIEW ONLY)") + " -----");
            if (editable) {
                System.out.println("1. Add Event");
                System.out.println("2. Delete Event");
            }
            System.out.println("3. View Events");
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
                case "3": view(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void add(Scanner sc) {
        System.out.print("Date (YYYY-MM-DD): "); String date = sc.nextLine();
        System.out.print("Description: "); String desc = sc.nextLine();
        System.out.print("Type (Holiday/Exam/Class/Meeting/Other): "); String type = sc.nextLine();

        String entry = date + " | " + type + " | " + desc;
        events.add(entry);
        save();
        System.out.println("✅ Event Added");
    }

    static void view() {
        if (events.isEmpty()) { System.out.println("No events found."); return; }
        for (int i = 0; i < events.size(); i++) System.out.println((i+1) + ". " + events.get(i));
    }

    static void delete(Scanner sc) {
        view();
        if (events.isEmpty()) return;
        System.out.print("Enter event number to delete: ");
        try {
            int idx = Integer.parseInt(sc.nextLine()) - 1;
            events.remove(idx);
            save();
            System.out.println("✅ Deleted");
        } catch (Exception e) { System.out.println("Invalid"); }
    }

    static void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (String e : events) { bw.write(e); bw.newLine(); }
            bw.close();
        } catch (Exception e) { System.out.println("Save Error"); }
    }

    static void loadEvents() {
        events.clear();
        try {
            File f = new File(FILE);
            if (!f.exists()) return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) events.add(line);
            br.close();
        } catch (Exception e) { System.out.println("Load Error"); }
    }
}