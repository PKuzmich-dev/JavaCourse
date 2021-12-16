package Unit2.Task3;


public class WhatsApp extends Messenger{

    @Override
    void sendMessage(Message message) {
        System.out.println(message.getSender() + " отправил сообщение через whatsapp " + message.getRecipient());
        listMessage.add(message);
    }
}
