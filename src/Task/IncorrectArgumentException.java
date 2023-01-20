package Task;

public class IncorrectArgumentException extends RuntimeException {
    private final String argument;

    public IncorrectArgumentException(String message, String argument) {
        super(message);
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message + ": " + getArgument()) : s;
    }
}
