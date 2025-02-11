import javax.swing.*;
import java.awt.*;

class Dnew_old extends JFrame {
    Dnew_old() {
        Font f = new Font("futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Doctor Login", JLabel.CENTER);
        JButton b1 = new JButton("Existing Doctor");
        JButton b2 = new JButton("New Doctor");
        JButton b3 = new JButton("Back");


        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);


        Container c = getContentPane();
        c.setLayout(null);

        l1.setBounds(50, 30, 700, 50);
        b1.setBounds(250, 150, 250, 50);
        b2.setBounds(250, 230, 250, 50);
        b3.setBounds(250,310,250,50);

        c.add(l1);
        c.add(b1);
        c.add(b2);
        c.add(b3);

        b3.addActionListener(
                a->
                {
                    new Home();
                    dispose();
                }
        );

        b1.addActionListener(
                a->{
                    new Dlogin();
                    dispose();
                }
        );

        b2.addActionListener(
                a->{
                    new NewDoctor();
                    dispose();
                }
        );



        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Doctor Login");
    }

    public static void main(String[] args) {
        Dnew_old a = new Dnew_old();
    }
}