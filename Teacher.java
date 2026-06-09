public class Teacher {
    int id;
    String name, dept, mobile, email, pass, subject;

    public Teacher(int id, String name, String dept, String mobile,
                   String email, String pass, String subject) {
        this.id = id; this.name = name; this.dept = dept;
        this.mobile = mobile; this.email = email; this.pass = pass;
        this.subject = subject;
    }
}