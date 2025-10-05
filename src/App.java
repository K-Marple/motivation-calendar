import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Bring in files and GUI
        TaskManager manager = new TaskManager();
        SwingUtilities.invokeLater(() -> new CalendarGUI(manager));

        // OLD CODE FOR CALENDAR WITHOUT GUI //
        // Scanner scanner = new Scanner(System.in);
        // TaskManager manager = new TaskManager();

        // while (true) {
        // System.out.println("\n1. View Calendar\n2. Add Task\n3. View Tasks\n4.
        // Exit");
        // int choice = scanner.nextInt();
        // scanner.nextLine();

        // if (choice == 1) {
        // System.out.print("Enter year and month (e.g., 2025 10): ");
        // int year = scanner.nextInt();
        // int month = scanner.nextInt();
        // CalendarView.printMonth(year, month);
        // } else if (choice == 2) {
        // System.out.print("Enter task title: ");
        // String title = scanner.nextLine();
        // System.out.print("Enter date (YYYY-MM-DD): ");
        // LocalDate date;
        // try {
        // date = LocalDate.parse(scanner.nextLine());
        // } catch (DateTimeParseException e) {
        // System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        // continue;
        // }
        // System.out.print("Enter time (HH:MM): ");
        // LocalTime time = LocalTime.parse(scanner.nextLine());
        // manager.addTask(new Task(title, date, time));
        // System.out.println("Task added.");
        // } else if (choice == 3) {
        // System.out.print("Enter date to view tasks (YYYY-MM-DD): ");
        // LocalDate date = LocalDate.parse(scanner.nextLine());
        // List<Task> tasks = manager.getTasks(date);
        // if (tasks.isEmpty()) {
        // System.out.println("No tasks for this date.");
        // } else {
        // tasks.forEach(System.out::println);
        // }
        // } else {
        // break;
        // }
        // }
    }
}