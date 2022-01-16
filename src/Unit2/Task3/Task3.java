package Unit2.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3 {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean inMainMenu = true;
        int mainMenuNum;
        Messenger messenger;

        Task3 task3 = new Task3();

        while(inMainMenu){
            task3.mainMenu();
            mainMenuNum = Integer.parseInt(task3.br.readLine());
            switch (mainMenuNum){
                case 1:
                    messenger = new Viber();
                    task3.run(messenger);
                    break;
                case 2:
                    messenger = new WhatsApp();
                    task3.run(messenger);
                    break;
                case 3:
                    messenger = new Telegram();
                    task3.run(messenger);
                    break;
                case 9:
                    inMainMenu = false;
                    break;
            }
        }
        task3.br.close();
    }

    void mainMenu() {
        System.out.println();
        System.out.println("Выберите мессенджер");
        System.out.println("1 - Viber");
        System.out.println("2 - WhatsApp");
        System.out.println("3 - Telegram");
        System.out.println("9 - выйти");
    }

    void run(Messenger messenger) throws IOException {

        String userLoginName;
        int messengerMenuNum;
        boolean inMessengerMenu = true;
        String recipientLogin;
        String messageText;
        Message message;


        System.out.println("введите логин");
        userLoginName = br.readLine();

        while(inMessengerMenu){
            messengerMenu();
            messengerMenuNum = Integer.parseInt(br.readLine());
            switch (messengerMenuNum){
                case 1:
                    System.out.println("введите логин адресата");
                    recipientLogin = br.readLine();
                    System.out.println("введите текст сообщения");
                    messageText = br.readLine();
                    messenger.sendMessage(new Message(userLoginName, recipientLogin, messageText));
                    break;
                case 2:
                    message = messenger.readMessage(userLoginName);
                    System.out.println(messenger.toString() + (message == null ? " нет сообщений" : message));
                    break;
                case 3:
                    System.out.println("введите логин");
                    userLoginName = br.readLine();
                    break;
                case 9:
                    inMessengerMenu = false;
                    break;
            }
        }
    }

    void messengerMenu(){
        System.out.println();
        System.out.println("Выберите необходимое действие");
        System.out.println("1 - написать сообщение");
        System.out.println("2 - получить сообщение");
        System.out.println("3 - перелогиниться");
        System.out.println("9 - выйти");
    }
}
