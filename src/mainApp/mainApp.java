package mainApp;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import task.entity.Login;
import task.entity.Student;

public class mainApp {

    public mainApp() {
    }

    static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Login.class)
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    /*public static void main(String[] args) {

        try ( // create a session
            Session session = factory.getCurrentSession()) {

            // calling the main System method
            LoginAuthentication(session);

        } catch (Exception e) {
            throw (e);

        } finally {
            factory.close();
        }
    }*/
    public void LoginAuthentication(Session session, String usr, String psd, JFrame jf, JFrame jf1) {

        // first check for the username and password
        System.out.println("Checking for username: " + usr + "\n");
        String p;
        Login theUser = session.get(Login.class, usr);

        if (theUser != null) {
            if (psd.equals(theUser.getPsd())) {
                jf.dispose();
                jf1.setVisible(true);
                /*p = "Welcome to our System";
                System.out.println(p);
                JOptionPane.showMessageDialog(null, p);*/
            } else {
                p = "The Password you entered is incorrect !\nPlease reenter it correctly";
                System.out.println(p);
                JOptionPane.showMessageDialog(null, p);
            }
        } else {
            p = "The Username you entered is not valid !";
            System.out.println(p);
            JOptionPane.showMessageDialog(null, p);
        }
    }

    /*public void StudentSystem(Session session) {
        // create an object from each class to call the needed method

        // create an object from scanner to take the input
        Scanner sc = new Scanner(System.in);
        int chosen;
        begin:
        while (true) {

            System.out.println("\n-------------\nChoose an operation..");
            System.out.println("[1] Add a new Student.");
            System.out.println("[2] Search for an existing Student.");
            System.out.println("[3] Retrieve All existing Students.");
            System.out.println("[4] Delete an existing Student.");
            System.out.println("[5] Update info for an existing Student.");
            System.out.println("[0] Close the System.\n");
            chosen = sc.nextInt();
            sc.nextLine();
            if (chosen <= 5) {
                switch (chosen) {
                    case 1: {
                        session = factory.getCurrentSession();
                        session.beginTransaction();
                        // add a new student
                        System.out.println("Enter the Student Id..\n");
                        String Aid = sc.nextLine();
                        System.out.println("Enter the Student Name..\n");
                        String Aname = sc.nextLine();
                        System.out.println("Enter the Student Department..");
                        System.out.println("[(1)Computers, (2)Electric, (3)Architecture, (4)Civil]\n");
                        int deptNo = sc.nextInt();
                        sc.nextLine();
                        String Adept = "";
                        switch (deptNo) {
                            case 1:
                                Adept = "Computers";
                                break;
                            case 2:
                                Adept = "Electric";
                                break;
                            case 3:
                                Adept = "Architecture";
                                break;
                            case 4:
                                Adept = "Civil";
                                break;
                            default:
                                break;
                        }
                        System.out.println("Enter the Student Phone Number..\n");
                        String Aphone = sc.nextLine();
                        if (Aid.length() == 14) {
                            if (Aphone.length() == 11 && Aphone.startsWith("01")) {
                                boolean c = createStudent(session, Aid, Aname, Adept, Aphone);
                                if (c) {
                                    System.out.println("New Student has been saved");
                                } else {
                                    System.out.println("You can not add a new Student because there is an existing one with this Id: " + Aid);
                                }
                            } else {
                                System.out.println("Phone number should be of 11 digits and starts with '01' ");
                            }
                        } else {
                            System.out.println("ID should be of 14 digits");
                        }
                        session.close();
                        continue begin;
                    }
                    case 2: {

                        // search for an existing  student "only by id"
                        System.out.println("Enter a Student Name or Id..\n");
                        String Si = sc.nextLine();
                        session = factory.getCurrentSession();
                        session.beginTransaction();

                        // retrieve all student
                        getStudents(session, Si);

                        session.close();

                        continue begin;
                    }
                    case 3: {
                        session = factory.getCurrentSession();
                        session.beginTransaction();

                        // retrieve all student
                        displayAllStudents(session);

                        session.close();
                        continue begin;
                    }
                    case 4: {
                        // delete an existing student
                        System.out.println("Enter the Id for the Student you want to delete..\n");
                        String Did = sc.nextLine();
                        session = factory.getCurrentSession();
                        session.beginTransaction();
                        boolean x = deleteStudent(session, Did);
                        if (x) {
                            System.out.println("Student deleted Successfully!!");
                        } else {
                            System.out.println("The Student does not exist!");
                        }
                        session.close();
                        continue begin;
                    }
                    case 5: {
                        // update info for an existing student
                        System.out.println("Enter the Id for the Student you want to update..\n");
                        String Uid = sc.nextLine();
                        System.out.println("Enter the Student new/edited Name..\n");
                        String UName = sc.nextLine();
                        System.out.println("Enter the Student new/edited Department..");
                        System.out.println("[(1)Computers, (2)Electric, (3)Architecture, (4)Civil]\n");
                        int deptNo = sc.nextInt();
                        sc.nextLine();
                        String Udept = "";
                        switch (deptNo) {
                            case 1:
                                Udept = "Computers";
                                break;
                            case 2:
                                Udept = "Electric";
                                break;
                            case 3:
                                Udept = "Architecture";
                                break;
                            case 4:
                                Udept = "Civil";
                                break;
                            default:
                                break;
                        }
                        System.out.println("Enter the Student new/edited Phone number..\n");
                        String Uphone = sc.nextLine();
                        session = factory.getCurrentSession();
                        session.beginTransaction();
                        boolean u = updateStudent(session, Uid, UName, Udept, Uphone);
                        if (u) {
                            System.out.println("Student Updated Successfully!!");
                        } else {
                            System.out.println("The Student does not exist!");
                        }
                        session.getTransaction().commit();
                        session.close();
                        continue begin;
                    }
                    case 0: {
                        System.out.println("\nGoodbye!\n");
                        sc.close();
                        break begin;
                    }
                    default:
                        throw new IllegalArgumentException("Unexpected value: " + chosen);
                }
            } else {
                System.out.println("please, type a correct nymber from 0 to 5");
                continue begin;
            }
        }

    }
    */
    public boolean createStudent(Session session, String sid, String nme, String dept, String phn) {

        Student myStudent = session.get(Student.class, sid);
        Student newStudent = new Student();
        if (myStudent == null) {
            newStudent.setId(sid);
            newStudent.setName(nme);
            newStudent.setDepartment(dept);
            newStudent.setPhoneNumber(phn);
            session.save(newStudent);
            session.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    public void getStudents(Session session, String name) {
        // prints in consol
        System.out.println("---------------\nAll the students: ");
        //+ " or like '%" + name + "%'"
        String query = "FROM Student where name like '%" + name + "%'" + " or id like '%" + name + "%'";
        List<Student> theStudents = session.createQuery(query, Student.class).list();
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
            //return tempStudent;
        }
        System.out.println("---------------\n");
        // return null;
    }

    public void displayAllStudents(Session session) {
        //prints in consol
        System.out.println("---------------\nAll the students: ");
        List<Student> theStudents = session.createQuery("from Student").list();
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
        System.out.println("---------------\n");
    }

    public boolean updateStudent(Session session, String stId, String StName, String StDept, String StPhone) {
        // retrieve student based on id: primary key
        Student myStudent = session.get(Student.class, stId);
        if (myStudent != null) {
            if (StName.equals("")) {
                myStudent.setName(myStudent.getName());
            } else {
                myStudent.setName(StName);
            }
            if (StDept.equals("")) {
                myStudent.setDepartment(myStudent.getDepartment());
            } else {
                myStudent.setDepartment(StDept);
            }
            if (StPhone.equals("")) {
                myStudent.setPhoneNumber(myStudent.getPhoneNumber());
            } else {
                myStudent.setPhoneNumber(StPhone);
            }
            session.save(myStudent);
            session.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStudent(Session session, String id) {
        // delete based on id: primary key
        Student myStudent = session.get(Student.class, id);
        if (myStudent != null) {
            session.createQuery("delete from Student where id = " + id).executeUpdate();
            return true;
        } else {
            return false;
        }
    }

}
