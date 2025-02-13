import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Pinfo extends JFrame {
    Pinfo(String username) {
        Font titleFont = new Font("Futura", Font.BOLD, 40);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);

        JLabel title = new JLabel("Patient information", JLabel.CENTER);
        title.setFont(titleFont);
        title.setForeground(new Color(155, 155, 255));
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Date","Time", "Patient"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.black);
        table.setGridColor(new Color(224, 224, 224));

        JScrollPane scrollPane = new JScrollPane(table);

        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 61, 51));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        backButton.addActionListener(
                e ->
                {
                    new Apage();
                    dispose();
                }
        );


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(10, 102, 204));
        topPanel.add(title, BorderLayout.NORTH);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(backButton);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, BorderLayout.NORTH);
        c.add(scrollPane, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);

        String url = "jdbc:mysql://localhost:3306/batch2";

        try (Connection con = DriverManager.getConnection(url, "root", "Shlok2401@")) {
            String sql = "SELECT * FROM transactions WHERE username=? ORDER BY date DESC";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    String s1 = rs.getString("date");
                    String s2 = rs.getString("description");
                    double d1 = rs.getDouble("amount");
                    double d2 = rs.getDouble("balance");
                    tableModel.addRow(new Object[]{s1, s2, d1, d2});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching transactions: " + e.getMessage());
        }

        setTitle("Patients");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Pinfo("Nehal");
    }
}