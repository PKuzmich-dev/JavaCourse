package Unit6.Task3;

public enum PhoneType {
    CELLULAR("сотовый"),
    WORK("рабочий"),
    HOME("домашний");

    private String type;

    PhoneType(String type) {
        this.type = type;
    }
}
