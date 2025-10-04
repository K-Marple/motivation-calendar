import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String title;
    private LocalDate date;
    private LocalTime time;

    public Task(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time + " - " + title;
    }
}
