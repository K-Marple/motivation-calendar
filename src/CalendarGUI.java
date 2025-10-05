import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.List;

public class CalendarGUI extends JFrame {
    //
    private TaskManager manager;
    private YearMonth currentMonth;

    // Create calendar "popup" window
    public CalendarGUI(TaskManager manager) {
        this.manager = manager;
        this.currentMonth = YearMonth.now();

        setTitle("Task Scheduler Calendar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawCalendar();
        setVisible(true);
    }

    // Populate information for calendar
    private void drawCalendar() {
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
        JLabel header = new JLabel(currentMonth.getMonth() + " " + currentMonth.getYear(), JLabel.CENTER);
        add(header, BorderLayout.NORTH);

        String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        for (String day : days) {
            calendarPanel.add(new JLabel(day, JLabel.CENTER));
        }

        // Ensure start of month is on correct day
        LocalDate firstDay = currentMonth.atDay(1);
        int startOffset = firstDay.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < startOffset; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // Highlight today and days with tasks
        LocalDate today = LocalDate.now();
        int daysInMonth = currentMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentMonth.atDay(day);
            JButton dayButton = new JButton(String.valueOf(day));

            if (date.equals(today)) {
                dayButton.setBackground(Color.YELLOW);
                dayButton.setFont(dayButton.getFont().deriveFont(Font.BOLD));
                dayButton.setToolTipText("Today");
            }

            List<Task> tasks = manager.getTasks(date);
            if (!tasks.isEmpty()) {
                dayButton.setBackground(Color.CYAN);
                dayButton.setToolTipText(tasks.size() + " task(s)");
            }

            dayButton.addActionListener(e -> openTaskDialog(date));
            calendarPanel.add(dayButton);
        }

        add(calendarPanel, BorderLayout.CENTER);
    }

    // Add task "popup"
    private void openTaskDialog(LocalDate date) {
        JTextField titleField = new JTextField();
        JTextField timeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Task:"));
        panel.add(titleField);
        panel.add(new JLabel("Time (HH:MM):"));
        panel.add(timeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add task for " + date, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String timeText = timeField.getText();

            try {
                LocalTime time = LocalTime.parse(timeText);
                Task task = new Task(title, date, time);
                manager.addTask(task);
                JOptionPane.showMessageDialog(this, "Task added!");
                refreshCalendar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid time format. Use HH:MM");
            }
        }

        // OLD CODE FOR DISPLAYING TASKS OF GIVEN DATE //
        // List<Task> tasks = manager.getTasks(date);
        // StringBuilder message = new StringBuilder("Tasks for " + date + ":\n");
        // for (Task task : tasks) {
        // message.append(task).append("\n");
        // }
        // JOptionPane.showMessageDialog(this, message.toString());
    }

    // Refresh calendar after adding task
    private void refreshCalendar() {
        getContentPane().removeAll();
        drawCalendar();
        revalidate();
        repaint();
    }
}
