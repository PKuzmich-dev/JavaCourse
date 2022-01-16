package Unit2.Task3;

/**
 * Сообщение. Содержит отправителя, получателя, текст сообщения
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Message {
    private String sender;
    private String recipient;
    private String message;


    Message(String sender, String recipient, String message){
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    String getRecipient(){
        return recipient;
    }

    @Override
    public String toString() {
        return "(" + sender + "): " + message;
    }
}
