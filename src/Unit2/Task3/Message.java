package Unit2.Task3;

/**
 * Сообщение. Содержит отправителя, получателя, текст сообщения
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Message {
    private Contact sender;
    private Contact recipient;
    private String message;

    Message(Contact sender, Contact recipient, String message){
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    Contact getSender(){
        return sender;
    }

    Contact getRecipient(){
        return recipient;
    }

    @Override
    public String toString() {
        return sender + ": " + message;
    }
}
