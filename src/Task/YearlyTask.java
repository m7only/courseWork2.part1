package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type, Frequency.YEARLY);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        long diff = ChronoUnit.DAYS.between(localDate, getDateTime().toLocalDate());
        return diff % Frequency.YEARLY.getFrequency() == 0;
    }
}
