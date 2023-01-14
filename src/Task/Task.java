package Task;

import java.time.LocalDateTime;

public abstract class Task implements Repeatable {
    private static int idGenerator = 0;
    private final Frequency frequency;
    private final int id;
    private final LocalDateTime dateTime;
    private final Type type;
    private String description;
    private String title;

    public Task(String title, String description, LocalDateTime dateTime, Type type, Frequency frequency) {
        if (title == null || title.isEmpty()) {
            throw new IncorrectArgumentException("Incorrect Argument", "title");
        }
        if (description == null || description.isEmpty()) {
            throw new IncorrectArgumentException("Incorrect Argument", "description");
        }
        if (dateTime == null) {
            throw new IncorrectArgumentException("Incorrect Argument", "date");
        }
        if (type == null) {
            throw new IncorrectArgumentException("Incorrect Argument", "type");
        }
        if (frequency == null) {
            throw new IncorrectArgumentException("Incorrect Argument", "frequency");
        }
        this.id = idGenerator++;
        this.description = description;
        this.dateTime = dateTime;
        this.type = type;
        this.title = title;
        this.frequency = frequency;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("    #%d %s%n        %s%n", id, title, description);
//        return String.format("%Td.%Tm.%TY    №%d %s, %s%n   %s", dateTime, dateTime, dateTime, id, title, description, type.getType());
    }
}
