import javax.swing.*;
import java.awt.*;

class Home extends JFrame {
    Home() {
        Font f = new Font("futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Hospital-OPD Appointment System", JLabel.CENTER);
        JButton b1 = new JButton("Admin");
        JButton b2 = new JButton("Existing Patient");
        JButton b3 = new JButton("New Patient");
        JButton b4 = new JButton("Doctor Login");

        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);

        l1.setBounds(50, 30, 700, 50);
        b1.setBounds(250, 150, 250, 50);
        b2.setBounds(250, 230, 250, 50);
        b3.setBounds(250, 310, 250, 50);
        b4.setBounds(250,390,250,50);

        c.add(l1);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);

        b1.addActionListener(
                a->{
                    //opens Alogin page and disposes current page
                    new Alogin();
                    dispose();
                }
        );

        b2.addActionListener(
                a->{
                    new Elogin();
                    dispose();
                }
        );

        b3.addActionListener(
                a->{
                    new NewSignup();
                    dispose();
                }
        );

        b4.addActionListener(
                a->
                {
                    new Dnew_old();
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home");
    }

    public static void main(String[] args) {
        Home a = new Home();
    }
}