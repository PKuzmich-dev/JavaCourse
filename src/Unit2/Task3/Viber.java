package Unit2.Task3;

public class Viber extends Messenger{

    @Override
    void sendMessage(Message message) {
        System.out.println(message.getSender() + " отправил сообщение через viber " + message.getRecipient());
        listMessage.add(message);
    }
}
