package Unit2.Task3;

import java.util.ArrayList;

public class WhatsApp implements Messenger {
    private static ArrayList<Message> listMessage = new ArrayList<>();

    WhatsApp(){
        System.out.println("Добро пожаловать в WhatsApp");
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
        return "WhatsApp";
    }
}
