package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type, Frequency.YEARLY);
    }

    @Override
    public boolean isRepeatable(LocalDate localDate) {
        return ChronoUnit.DAYS.between(localDate, getDateTime().toLocalDate())
                % Frequency.YEARLY.getFrequency() == 0;
    }
}
