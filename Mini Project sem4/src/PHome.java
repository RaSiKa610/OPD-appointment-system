import javax.swing.*;
import java.awt.*;

class PHome extends JFrame {
    PHome(String patient_name) {
        Font f = new Font("futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Patient Home Page", JLabel.CENTER);
        JButton b1 = new JButton("Schedule Appointment");
        JButton b2 = new JButton("Existing Appointment");
        JButton b3 = new JButton("Back");

        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);


        Container c = getContentPane();
        c.setLayout(null);

        l1.setBounds(50, 30, 700, 50);
        b1.setBounds(250, 170, 250, 50);
        b2.setBounds(250, 260, 250, 50);
        b3.setBounds(250,350,250,50);


        c.add(l1);
        c.add(b1);
        c.add(b2);
        c.add(b3);


        b1.addActionListener(
                a->{
                    //opens Alogin page and disposes current page
                    new NewAptmnt();
                    dispose();
                }
        );

        b2.addActionListener(
                a->{
                    new MAppointment(patient_name);
                    dispose();
                }
        );

        b3.addActionListener(
                a->
                {
                    new Home();
                    dispose();
                }
        );


        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Patient Home");
    }

    public static void main(String[] args) {
        PHome a = new PHome("Sans");
    }
}