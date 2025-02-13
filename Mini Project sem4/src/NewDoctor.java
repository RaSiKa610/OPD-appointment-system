import javax.swing.*;
import java.awt.*;
import java.sql.*;

class NewDoctor extends JFrame {
    NewDoctor() {
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.PLAIN, 18);

        JLabel l1 = new JLabel("Set Username");
        JTextField t1 = new JTextField(10);

        JLabel l2 = new JLabel("Set Password");
        JTextField t2 = new JTextField(10);

        JLabel l3 = new JLabel("Confirm Password");
        JTextField t3 = new JTextField(10);

        JLabel l4 = new JLabel("Phone");
        JTextField t4 = new JTextField(15);

        JLabel l5 = new JLabel("Email");
        JTextField t5 = new JTextField(20);

        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        JLabel title = new JLabel("Signup", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        l3.setFont(f2);
        t3.setFont(f2);
        l4.setFont(f2);
        t4.setFont(f2);
        l5.setFont(f2);
        t5.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 200, fieldX = 400, yStart = 80, width = 150, height = 30, gap = 40;

        title.setBounds(300, 10, 200, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);

        l3.setBounds(labelX, yStart + 2 * gap, width, height);
        t3.setBounds(fieldX, yStart + 2 * gap, width, height);

        l4.setBounds(labelX, yStart + 3 * gap, width, height);
        t4.setBounds(fieldX, yStart + 3 * gap, width, height);

        l5.setBounds(labelX, yStart + 4 * gap, width, height);
        t5.setBounds(fieldX, yStart + 4 * gap, width, height);

        b1.setBounds(250, yStart + 8 * gap, 120, 40);
        b2.setBounds(400, yStart + 8 * gap, 120, 40);

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l3);
        c.add(t3);
        c.add(l4);
        c.add(t4);
        c.add(l5);
        c.add(t5);
        c.add(b1);
        c.add(b2);

        b2.addActionListener(
                a -> {
                    new Dnew_old();
                    dispose();
                }
        );

        b1.addActionListener(
                a -> {
                    if (t2.getText().equals(t3.getText())) {
                        String url = "jdbc:mysql://localhost:3306/hospital";
                        try (Connection con = DriverManager.getConnection(url, "root", "Shlok2401@")) {
                            String sql = "INSERT INTO doctors(username, password, phone, email) VALUES(?, ?, ?, ?)";
                            try (PreparedStatement pst = con.prepareStatement(sql)) {
                                pst.setString(1, t1.getText()); // Username
                                pst.setString(2, t2.getText()); // Password
                                pst.setString(3, t4.getText()); // Phone
                                pst.setString(4, t5.getText()); // Email
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Signup Successful");
                                dispose();
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Passwords do not match");
                    }
                }
        );

        setVisible(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("New Doctor Signup");
    }

    public static void main(String[] args) {
        new NewDoctor();
    }
}