package Unit2.Task3;

import java.util.ArrayList;
/**
 * Мессенджер. Содержит массив сообщений
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public abstract class Messenger {
    ArrayList<Message> listMessage = new ArrayList<>();

    /**
     * Отправка сообщения, механизм реализуется в мессенджере
     *
     */
    abstract void sendMessage(Message message);

    /**
     * Получение сообщения, считаем, что способ получения для всех
     * мессенджеров одинаковый
     *
     */
    Message readMessage(Contact c){
        for (Message m : listMessage){
            if (m.getRecipient().equals(c)){
                listMessage.remove(m);
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
