package Unit7.Task2;

import Unit7.Task2.DAO.BrandDAO;
import Unit7.Task2.DAO.CarDAO;
import Unit7.Task2.DAO.ContactDAO;
import Unit7.Task2.Entity.Brand;
import Unit7.Task2.Entity.Car;
import Unit7.Task2.Entity.Contact;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean inMainMenu = true;
        int mainMenuNum;
        String brand;
        int contactId;
        ResultSet rs;

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Unit7",
                                                            "postgres", "sadmin");
            Statement statement = connection.createStatement()) {
            ContactDAO contactDAO = new ContactDAO(connection);
            CarDAO carDAO = new CarDAO(connection);

            CRUDExample();

            while (inMainMenu) {
                mainMenu();
                try {
                    mainMenuNum = Integer.parseInt(br.readLine());
                } catch (IOException e) {
                    mainMenuNum = -1;
                }
                switch (mainMenuNum) {
                    case 1:
                        System.out.println("введите марку");
                        try {
                            brand = br.readLine();
                            System.out.println(contactDAO.getContactByCarBrand(brand).stream()
                                                   .map(e -> e.getFIO()).collect(Collectors.toList())
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        System.out.println("введите id клиента");
                        try {
                            contactId = Integer.parseInt(br.readLine());
                            System.out.println(carDAO.getCarByContact(contactId));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.println("введите id клиента");
                        try {
                            contactId = Integer.parseInt(br.readLine());
                            Contact contact = contactDAO.getContactById(contactId);
                            if (contact == null){
                                System.out.println("Клиент с Id = " + contactId + " не существует");
                            }
                            else {
                                System.out.println("удалено " + carDAO.deleteCarForContact(contact) + " авто");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        rs = statement.executeQuery("select c.id, c.last_name||' '||c.first_name||"
                                                          + "case when c.mid_name is null then '' "
                                                          + "else ' '||c.mid_name end fio, "
                                                          + "count(contact_car.contact_id) cnt "
                                                          + "from contact c left join contact_car "
                                                          + "on c.id = contact_car.contact_id "
                                                          + "where c.deleted is null "
                                                          + "group by c.id, c.last_name, "
                                                          + "c.first_name, c.mid_name "
                                                          + "order by c.last_name, c.first_name, c.mid_name");
                        while (rs.next()){
                            System.out.println(rs.getInt("id") + "\t" +
                                                   rs.getString("fio") + " " +
                                                   rs.getString("cnt")
                            );
                        }
                        break;
                    case 5:
                        rs = statement.executeQuery("select c.id, c.last_name||' '||c.first_name||"
                                                        + "case when c.mid_name is null then '' "
                                                        + "else ' '||c.mid_name end fio, "
                                                        + "count(contact_car.contact_id) cnt "
                                                        + "from contact c left join contact_car "
                                                        + "on c.id = contact_car.contact_id and "
                                                        + "c.deleted is null "
                                                        + "group by c.id, c.last_name, "
                                                        + "c.first_name, c.mid_name "
                                                        + "having count(1) > 2"
                                                        + "order by c.last_name, c.first_name, c.mid_name");
                        while (rs.next()){
                            System.out.println(rs.getInt("id") + "\t" +
                                                   rs.getString("fio") + " " +
                                                   rs.getString("cnt")
                            );
                        }
                        break;
                    case 9:
                        inMainMenu = false;
                        break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static void mainMenu() {
        System.out.println();
        System.out.println("Выберите действие");
        System.out.println("1 - Получить список владельцев по марке");
        System.out.println("2 - Получить список автомобилей по владельцу");
        System.out.println("3 - Удалить автомобили у клиента");
        System.out.println("4 - Получить количество автомобилей по клиентам");
        System.out.println("5 - Получить клиентов, у которых больше 2 машин");
        System.out.println("9 - Выйти");
    }

    static void CRUDExample(){
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Unit7",
                                                                "postgres", "sadmin"))
        {
            CarDAO bd2 = new CarDAO(connection);
            List<Car> listCars = new ArrayList<>(bd2.get("(car.id='2' or car.id='3')"));

            Contact contact = new Contact("Тетера", "Оксана", null, "Махачкала", 0, listCars);
            ContactDAO bd = new ContactDAO(connection);
            // добавление
            bd.add(contact);
            System.out.println("после создания");
            System.out.println(contact);
            // изменение
            contact.setLastName("Заводовская");
            bd.update(contact);
            // проверяем как обновили
            List<Contact> ls = bd.get("id = '" + contact.getId() + "'");
            System.out.println("после обновления");
            System.out.println(ls);

            bd.delete(contact);
            ls = bd.get(null);
            System.out.println("Список контактов после удаления");
            System.out.println(ls);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
