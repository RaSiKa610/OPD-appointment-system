import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Mapp extends JFrame {
    Mapp() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Your Appointment", JLabel.CENTER);
        JLabel l1 = new JLabel("Enter Name");
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);



        Container c = getContentPane();
        c.setLayout(null);

        title.setBounds(240, 30, 350, 50);
        l1.setBounds(250, 100, 300, 30);
        t1.setBounds(250, 140, 300, 30);
        b1.setBounds(300, 300, 200, 40);
        b2.setBounds(300, 360, 200, 40);

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(b1);
        c.add(b2);



        b2.addActionListener(
                a->
                {
                    new Home();
                    dispose();
                }
        );

        b1.addActionListener(
                a->
                {
                    new MAppointment("Sans");
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");
    }

    public static void main(String[] args) {
        new Mapp();
    }
}