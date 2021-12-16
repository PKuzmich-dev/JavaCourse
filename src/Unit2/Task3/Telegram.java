package Unit2.Task3;

public class Telegram extends Messenger{

    @Override
    void sendMessage(Message message) {
        System.out.println(message.getSender() + " отправил сообщение через telegram " + message.getRecipient());
        listMessage.add(message);
    }

}
