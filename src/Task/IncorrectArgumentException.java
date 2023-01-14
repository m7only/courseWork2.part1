package Task;

public class IncorrectArgumentException extends RuntimeException {
    private final String argument;

    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    public IncorrectArgumentException(String message, String argument) {
        super(message);
        this.argument = argument;
    }

    public IncorrectArgumentException(String message, Throwable cause, String argument) {
        super(message, cause);
        this.argument = argument;
    }

    public IncorrectArgumentException(Throwable cause, String argument) {
        super(cause);
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
