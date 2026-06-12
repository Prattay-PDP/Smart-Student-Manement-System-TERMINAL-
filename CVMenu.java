import java.util.*;
import java.io.*;

public class CVMenu {
    static void show(Scanner sc, StudentManager manager) {
        while (true) {
            System.out.println("\n----- CV & CERTIFICATES -----");
            System.out.println("1. Generate CV");
            System.out.println("2. Issue Certificate");
            System.out.println("3. View Certificates");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            String c = sc.nextLine().trim();
            switch (c) {
                case "1": generateCV(sc, manager); break;
                case "2": issueCert(sc, manager); break;
                case "3": viewCerts(); break;
                case "0": return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void generateCV(Scanner sc, StudentManager manager) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.println(
                "================================================\n"+
                "                CURRICULUM VITAE\n"+
                "================================================\n\n"+
                "Name        : "+s.name+"\n"+
                "Student ID  : "+s.id+"\n"+
                "Age         : "+s.age+"\n"+
                "Department  : "+s.dept+"\n"+
                "Semester    : "+s.semester+"\n"+
                "Section     : "+s.section+"\n\n"+
                "Mobile      : "+s.mobile+"\n"+
                "Email       : "+s.email+"\n\n"+
                "CGPA        : "+s.cgpa+"\n"+
                "Attendance  : "+String.format("%.2f",s.getAttPer())+"%\n"+
                "Status      : "+s.status+"\n\n"+
                "Institution : University of Science and Technology Chittagong (USTC)\n"+
                "================================================"
            );
        } catch (Exception ex) { System.out.println("Invalid ID"); }
    }

    static void issueCert(Scanner sc, StudentManager manager) {
        System.out.print("Student ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Student s = manager.searchStudent(id);
            if (s == null) { System.out.println("Student Not Found"); return; }

            System.out.print("Certificate Type (Course Completion/Merit/Participation/Achievement/Character): ");
            String type = sc.nextLine();
            System.out.print("Reason: ");
            String reason = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter("certificates.txt", true));
            bw.write(s.id+","+s.name+","+type+","+reason+","+java.time.LocalDate.now());
            bw.newLine();
            bw.close();

            System.out.println(
                "================================================\n"+
                "                  CERTIFICATE\n"+
                "================================================\n\n"+
                "This is to certify that\n\n"+
                "    "+s.name+" (ID: "+s.id+")\n\n"+
                "Department: "+s.dept+"\n\n"+
                "has been awarded:\n"+
                "    "+type+"\n\n"+
                "Reason: "+reason+"\n\n"+
                "Date: "+java.time.LocalDate.now()+"\n"+
                "Issued by: USTC Administration\n"+
                "================================================"
            );
            System.out.println("✅ Certificate Issued");
        } catch (Exception ex) { System.out.println("Invalid Input"); }
    }

    static void viewCerts() {
        System.out.println(String.format("%-6s %-20s %-25s %-20s %-12s","ID","NAME","TYPE","REASON","DATE"));
        System.out.println("=".repeat(90));
        try {
            File f = new File("certificates.txt");
            if (!f.exists()) { System.out.println("No certificates issued yet"); return; }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 5)
                    System.out.println(String.format("%-6s %-20s %-25s %-20s %-12s",d[0],d[1],d[2],d[3],d[4]));
            }
            br.close();
        } catch (Exception e) { System.out.println("Error reading certificates"); }
    }
}