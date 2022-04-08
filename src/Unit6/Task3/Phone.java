package Unit6.Task3;

public class Phone {
    private PhoneType type;
    private String phone;

    public Phone(PhoneType type, String phone) {
        if (phone.length() == 0)
            throw new IllegalArgumentException("Номер телефона не может быть пустым");
        this.type = type;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public PhoneType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
