import javax.swing.*;
import java.awt.*;

class NewAptmnt extends JFrame
{
    NewAptmnt(){
        Font f1 = new Font("Arial",Font.BOLD,40);
        Font f2 = new Font("Calibri",Font.BOLD,26);
        Font f3 = new Font("Calibri",Font.PLAIN,18);

        JLabel title = new JLabel("New Appointment", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        JTextArea a1 = new JTextArea();
        JLabel l1 = new JLabel("Patient's Name:");
        JTextField t1 = new JTextField(10);


        JLabel l2 = new JLabel("Doctor:");
        String[] menu = {"Rasika Shinde","Soham Shimpi","Saukhya Patil"};
        JComboBox<String> box = new JComboBox<>(menu);



        JButton b1 = new JButton("Back");
        JButton b2 = new JButton("Proceed");

        t1.setFont(f3);
        l1.setFont(f2);
        l2.setFont(f2);
        box.setFont(f3);
        b1.setFont(f2);
        b2.setFont(f2);
        a1.setFont(f2);
        a1.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(150, 220, 500, 200);

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 200, fieldX = 400, yStart = 80, width = 200, height = 30, gap = 55;

        title.setBounds(200, 10, 400, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);
        l2.setBounds(labelX, yStart + gap, width, height);
        box.setBounds(fieldX, yStart + gap, width, height);
        b2.setBounds(320, yStart +  2*gap, 150, 40);
        b1.setBounds(320,yStart + 3*gap,150,40);


        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(box);
        c.add(b1);
        c.add(b2);

        b1.addActionListener(
                a->
                {
                    new PHome("Sans");
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("New Appointment");
    }
    public static void main(String[] args) {
    NewAptmnt obj = new NewAptmnt();
  }
}



