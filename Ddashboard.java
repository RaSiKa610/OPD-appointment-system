import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Ddashboard extends JFrame {
    Ddashboard(String username) {
        Font titleFont = new Font("Futura", Font.BOLD, 40);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);

        JLabel title = new JLabel("Doctor Dashboard", JLabel.CENTER);
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Doctor Name", "Available From", "Available To", "Start Time", "End Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(tableFont);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(224, 224, 224));

        JScrollPane scrollPane = new JScrollPane(table);

        JDateChooser startDateChooser = new JDateChooser();
        startDateChooser.setDateFormatString("yyyy-MM-dd");
        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("yyyy-MM-dd");

        SpinnerDateModel startTimeModel = new SpinnerDateModel();
        JSpinner startTimeSpinner = new JSpinner(startTimeModel);
        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
        startTimeSpinner.setEditor(startTimeEditor);

        SpinnerDateModel endTimeModel = new SpinnerDateModel();
        JSpinner endTimeSpinner = new JSpinner(endTimeModel);
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
        endTimeSpinner.setEditor(endTimeEditor);

        JButton b1 = new JButton("Schedule");
        b1.setFont(buttonFont);
        b1.setBackground(new Color(0, 153, 76));
        b1.setForeground(Color.WHITE);

        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(new Color(224, 224, 224));
        filterPanel.add(new JLabel("From:"));
        filterPanel.add(startDateChooser);
        filterPanel.add(new JLabel("To:"));
        filterPanel.add(endDateChooser);
        filterPanel.add(new JLabel("Start Time:"));
        filterPanel.add(startTimeSpinner);
        filterPanel.add(new JLabel("End Time:"));
        filterPanel.add(endTimeSpinner);
        filterPanel.add(b1);

        // Back Button
        JButton b2 = new JButton("Back");
        b2.setFont(buttonFont);
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(255, 51, 51));
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        b2.addActionListener(
                e -> {
                    new Home();
                    dispose();
                }
        );

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 102, 204));
        topPanel.add(title, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.add(b2);

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.add(filterPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(20, 20));
        c.add(topPanel, BorderLayout.NORTH);
        c.add(centerPanel, BorderLayout.CENTER);
        c.add(bottomPanel, BorderLayout.SOUTH);

        b1.addActionListener(
                a -> {
                    Date startDate = startDateChooser.getDate();
                    Date endDate = endDateChooser.getDate();

                    Date startTime = (Date) startTimeSpinner.getValue();
                    Date endTime = (Date) endTimeSpinner.getValue();

                    if (startDate == null || endDate == null || startTime == null || endTime == null) {
                        JOptionPane.showMessageDialog(null, "Please select both start and end dates and times.");
                        return;
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    String formattedStartDate = dateFormat.format(startDate);
                    String formattedEndDate = dateFormat.format(endDate);
                    String formattedStartTime = timeFormat.format(startTime);
                    String formattedEndTime = timeFormat.format(endTime);

                    String url = "jdbc:mysql://localhost:3306/hospital";
                    try (Connection con = DriverManager.getConnection(url, "root", "Shlok2401@")) {
                        String sql = "INSERT INTO available_doctors (doctor_name, available_date_from, available_date_to, available_start_time, available_end_time) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement pst = con.prepareStatement(sql)) {
                            pst.setString(1, username);
                            pst.setString(2, formattedStartDate);
                            pst.setString(3, formattedEndDate);
                            pst.setString(4, formattedStartTime);
                            pst.setString(5, formattedEndTime);
                            pst.executeUpdate();

                            tableModel.setRowCount(0);

                            String selectSQL = "SELECT * FROM available_doctors WHERE doctor_name = ?";
                            try (PreparedStatement selectPst = con.prepareStatement(selectSQL)) {
                                selectPst.setString(1, username);
                                ResultSet rs = selectPst.executeQuery();
                                while (rs.next()) {
                                    String doctorName = rs.getString("doctor_name");
                                    String availableFrom = rs.getString("available_date_from");
                                    String availableTo = rs.getString("available_date_to");
                                    String start = rs.getString("available_start_time");
                                    String end = rs.getString("available_end_time");
                                    tableModel.addRow(new Object[]{doctorName, availableFrom, availableTo, start, end});
                                }
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
        );

        setTitle("Doctor Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ddashboard("Rasika");
    }
}