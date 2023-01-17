package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class WeeklyTask extends Task {

    public WeeklyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type, Frequency.WEEKLY);
    }

    @Override
    public boolean isRepeatable(LocalDate localDate) {
        long diff = ChronoUnit.DAYS.between(localDate, getDateTime().toLocalDate());
        return diff % Frequency.WEEKLY.getFrequency() == 0;
    }
}
