package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type, Frequency.DAILY);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        return true;
    }
}
