package Unit2.Task3;

public interface Messenger {
    void sendMessage(Message message);

    Message readMessage(String login);
}
