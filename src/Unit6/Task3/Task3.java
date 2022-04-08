package Unit6.Task3;

import java.util.*;
import java.util.stream.Collectors;

public class Task3 {
    static List<Client> clients = new ArrayList<>();
    static String[] names = {"Павел", "Михаил", "Оксана", "Адрей", "Александр"};

    public static void main(String[] args) {
        Random random = new Random();
        initialize();

        System.out.println("Исходный массив:");
        System.out.println(clients);

        System.out.println();

        String name = names[random.nextInt(5)];
        System.out.println("Суммарный возраст для имени " + name + " - " +
                clients.stream().filter(e -> e.getName().equals(name))
                        .mapToInt(e -> e.getAge()).sum());

        System.out.println();

        LinkedHashSet<String> setNames =
                new LinkedHashSet(clients.stream().map(e -> e.getName()).collect(Collectors.toList()));

        System.out.println("Множество имен в порядке упоминания в исходном массиве:");
        System.out.println(setNames);

        System.out.println();

        System.out.println("Содержит клиента с именем " + name + " - " +
                (clients.stream().anyMatch(e -> e.getName().equals(name))));

        System.out.println();

        LinkedHashMap<Integer, String> mapClients =
        new LinkedHashMap(clients.stream().collect(Collectors.toMap(x -> x.getId(), y -> y.getName())));

        System.out.println("Массив в виде Map:");
        System.out.println(mapClients);

        System.out.println();

        Map<Integer, Collection<Client>> mapClients2 =
        clients.stream().map(e -> e.getAge()).distinct().collect(Collectors.toMap(x -> x,
                y -> clients.stream().filter(e -> e.getAge() == y).collect(Collectors.toList())));

        System.out.println("Map(ключ - возраст, значение - коллекция клиентов с таким возрастом)");
        System.out.println(mapClients2);

        System.out.println();

        System.out.println("Все телефоны клиентов:");
        System.out.println(clients.stream().
                map(e -> e.getPhones()).
                flatMap(p -> p.stream()).
                map(p -> p.getPhone()).
                collect(Collectors.toList()));

        System.out.println();

        System.out.println("Самый возрастной клиент со стационарным телефоном:");
        System.out.println(clients.stream().
                filter(c -> c.getPhones().stream().anyMatch(p -> p.getType() == PhoneType.HOME)).
                sorted((c,o) -> o.getAge() - c.getAge()).
                findFirst().orElse(null)
        );

    }

    static void initialize(){
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            Set<Phone> phones = new HashSet<>();
            for(int j = 0; j < random.nextInt(3); j++){
                phones.add(new Phone(PhoneType.values()[random.nextInt(PhoneType.values().length)],
                        "+7" + generatePhone()));
            }
            clients.add(new Client(i, names[random.nextInt(names.length)], random.nextInt(100), phones));
        }
    }

    static String generatePhone(){
        StringBuilder phoneNumber = new StringBuilder();
        Random random = new Random();
        for (int i=0; i<10; i++){
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }


}
