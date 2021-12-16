package Unit2.Task3;

/**
 * Контакт. Содержит логин и мессенджер
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Contact {
    private String login;
    private Messenger messenger;

    Contact(String login){
        this.login = login;
    }

    /**
     * устанавливает мессенджер контакту
     *
     */
    void setMessenger(Messenger messenger){
        this.messenger = messenger;
        System.out.println(this + " перешел в " + messenger);
    }

    /**
     * отправка сообщения другому контакту
     *
     */
    void sendMessage(Contact recipient, String message){
        if (messenger == null){
            System.out.println("не установлен мессенджер");
        }
        else {
            messenger.sendMessage(new Message(this, recipient, message));
        }
    }

    /**
     * получение сообщения
     *
     */
    Message readMessage(){
        if (messenger == null){
            System.out.println("не установлен мессенджер");
            return null;
        }
        else {
            return messenger.readMessage(this);
        }
    }

    @Override
    public String toString() {
        return login;
    }
}
