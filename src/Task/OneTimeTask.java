package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title, description, dateTime, type, Frequency.ONETIME);
    }

    @Override
    public boolean isRepeatable(LocalDate localDate) {
        return false;
    }
}
