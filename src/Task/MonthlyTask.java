package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type, Frequency.MONTHLY);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        long diff = ChronoUnit.DAYS.between(localDate, getDateTime().toLocalDate());
        return diff % Frequency.MONTHLY.getFrequency() == 0;
    }
}
