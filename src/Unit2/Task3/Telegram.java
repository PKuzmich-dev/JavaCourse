package Unit2.Task3;

import java.util.ArrayList;

public class Telegram implements Messenger {
    private static ArrayList<Message> listMessage = new ArrayList<>();

    Telegram(){
        System.out.println("Добро пожаловать в Telegram");
    }

    @Override
    public Message readMessage(String login) {
        for (Message m : listMessage){
            if (m.getRecipient().equals(login)){
                listMessage.remove(m);
                return m;
            }
        }
        return null;
    }

    @Override
    public void sendMessage(Message message) {
        listMessage.add(message);
    }

    @Override
    public String toString() {
        return "Telegram";
    }
}
