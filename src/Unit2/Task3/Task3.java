package Unit2.Task3;

public class Task3 {
    public static void main(String[] args) {

        Contact petrov = new Contact("Petrov");
        Contact ivanov = new Contact("Ivanov");
        Contact sidorov = new Contact("Sidorov");

        WhatsApp whatsapp = new WhatsApp();
        Viber viber = new Viber();
        Telegram telegram = new Telegram();

        petrov.setMessenger(whatsapp);
        petrov.sendMessage(ivanov, "Иванов, привет! Как дела?");
        petrov.setMessenger(viber);
        petrov.sendMessage(sidorov, "Сидоров, привет! Как тебе viber?");

        ivanov.setMessenger(telegram);
        ivanov.sendMessage(petrov, "Петров, привет! Пошли обедать");
        ivanov.setMessenger(whatsapp);
        ivanov.sendMessage(sidorov, "Сидоров, привет! Как тебе эта программа?");

        sidorov.setMessenger(viber);
        sidorov.sendMessage(petrov, "Петров, привет! Я заболел");
        sidorov.setMessenger(telegram);
        sidorov.sendMessage(ivanov, "Иванов, привет! Я увольняюсь");

        System.out.println();
        petrov.setMessenger(telegram);
        System.out.println("Петров получил сообщение");
        System.out.println(petrov.readMessage());

        System.out.println();
        ivanov.setMessenger(whatsapp);
        System.out.println("Иванов получил сообщение");
        System.out.println(ivanov.readMessage());

        System.out.println();
        sidorov.setMessenger(viber);
        System.out.println("Сидоров получил сообщение");
        System.out.println(sidorov.readMessage());
    }

}
