import java.time.YearMonth;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarView {
    public static void printMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate firstDay = ym.atDay(1);
        DayOfWeek startDay = firstDay.getDayOfWeek();
        int daysInMonth = ym.lengthOfMonth();

        System.out.printf("%n%s %d%n", ym.getMonth(), year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        int value = startDay.getValue() % 7;
        for (int i = 0; i < value; i++) {
            System.out.print("    ");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate today = LocalDate.now();
            LocalDate current = LocalDate.of(year, month, day);

            if (current.equals(today)) {
                System.out.printf("[%2d]", day);
            } else {
                System.out.printf(" %2d ", day);
            }

            if ((day + value) % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}
