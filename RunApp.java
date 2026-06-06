public class RunApp {
 
    public static void main(String[] args) {
 
        NoticeBoard.loadNotice();
 
        StudentManager manager = new StudentManager();
 
        System.out.println();
        System.out.println("  Loading data...");
        System.out.println("  Students loaded: " + manager.totalStudents());
 
        
        createDefaultStaff();
 
        new LoginUI(manager).show();
    }
 
    static void createDefaultStaff() {
        java.io.File f = new java.io.File("staff.txt");
        if (f.exists()) return;
 
        
        try {
            java.io.BufferedWriter bw =
                new java.io.BufferedWriter(new java.io.FileWriter(f));
 
            bw.write("admin,01700000000,admin123");     bw.newLine();
            bw.write("teacher,01711111111,teacher123"); bw.newLine();
            bw.write("accountant,01722222222,acc123");  bw.newLine();
            bw.write("receptionist,01733333333,rec123");bw.newLine();
            bw.write("librarian,01744444444,lib123");   bw.newLine();
 
            bw.close();
            System.out.println("  Default staff accounts created (staff.txt)");
            System.out.println();
            System.out.println("  DEFAULT STAFF LOGINS:");
            System.out.println("  Admin       -> Mobile: 01700000000  | Pass: admin123");
            System.out.println("  Teacher     -> Mobile: 01711111111  | Pass: teacher123");
            System.out.println("  Accountant  -> Mobile: 01722222222  | Pass: acc123");
            System.out.println("  Receptionist-> Mobile: 01733333333  | Pass: rec123");
            System.out.println("  Librarian   -> Mobile: 01744444444  | Pass: lib123");
 
        } catch (Exception e) {
            System.out.println("  Staff file create error.");
        }
    }
}