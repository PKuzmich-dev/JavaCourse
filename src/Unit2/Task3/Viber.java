package Unit2.Task3;

import java.util.ArrayList;

public class Viber implements Messenger {
    private static ArrayList<Message> listMessage = new ArrayList<>();

    Viber(){
        System.out.println("Добро пожаловать в Viber");
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
        return "Viber";
    }
}
