package Task;

public enum Type {
    WORK("Рабочая"),
    PERSONAL("Личная");
    private static final Type[] list = Type.values();

    public static Type getType(int i) {
        return list[i];
    }

    public static int getLastIndex() {
        return list.length - 1;
    }

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
