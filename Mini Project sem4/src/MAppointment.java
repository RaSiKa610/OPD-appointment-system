import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class  MAppointment extends JFrame {
    MAppointment(String patient_name) {
        Font titleFont = new Font("Futura", Font.BOLD, 40);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);

        JLabel title = new JLabel("Your Appointment", JLabel.CENTER);
        title.setFont(titleFont);
        title.setForeground(new Color(255, 255, 255));
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Doctor", "Date", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(224, 224, 224));

        JScrollPane scrollPane = new JScrollPane(table);



        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 51, 51));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        backButton.addActionListener(e ->
        {
            new PHome(patient_name);
            dispose();
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 102, 204));
        topPanel.add(title, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(backButton);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, BorderLayout.NORTH);
        c.add(scrollPane, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);


        String url = "jdbc:mysql://localhost:3306/hospital";
        try(Connection con = DriverManager.getConnection(url,"root","Shlok2401@"))
        {
            String sql = "select * from appointments where patient_name=?";
            try(PreparedStatement pst = con.prepareStatement(sql))
            {

                ResultSet rs = pst.executeQuery();
                while(rs.next())
                {
                    String s1 = rs.getString("doctor_name");
                    String s2 = rs.getString("patient_name");
                    String appointment_date = rs.getString("appointment_date");
                    String appointment_time = rs.getString("appointment_time");
                    tableModel.addRow(new Object[]{s1,s2,appointment_date,appointment_time});
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        setTitle("Your Appointment");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MAppointment("Sans");
    }
}