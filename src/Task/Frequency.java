package Task;

public enum Frequency {
    ONETIME("однократная", 0),
    DAILY("ежедневная", 1),
    WEEKLY("еженедельная", 7),
    MONTHLY("ежемесячная", 31),
    YEARLY("ежегодная", 365);

    private static final Frequency[] list = Frequency.values();

    public static Frequency getFrequency(int i) {
        return list[i];
    }

    public static int getLastIndex() {
        return list.length - 1;
    }

    private final String frequencyTitle;
    private final int frequency;

    Frequency(String frequencyTitle, int frequency) {
        this.frequencyTitle = frequencyTitle;
        this.frequency = frequency;
    }

    public String getFrequencyTitle() {
        return frequencyTitle;
    }

    public int getFrequency() {
        return frequency;
    }
}
