package Unit8.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import Unit8.DAO.CarBrandDAO;
import Unit8.DAO.CarDAO;
import Unit8.DAO.ContactDAO;
import Unit8.domain.Car;
import Unit8.domain.CarBrand;
import Unit8.domain.Contact;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactTest {

    private static String carBrandName = "KIA_Test";
    private static CarBrandDAO carBrandDAO = new CarBrandDAO();
    private static ContactDAO contactDAO = new ContactDAO();
    private static CarDAO carDAO = new CarDAO();
    private static Contact contactTest;
    private static Set<Car> cars;
    @AfterClass
    public static void after(){
        for (Car car : cars) {
            System.out.println("before delete car");
            carDAO.delete(car);
        }
        CarBrand carBrand = carBrandDAO.getByName(carBrandName);
        System.out.println("before delete carBrand");
        if (carBrand != null)
            carBrandDAO.delete(carBrand);
    }

    @Test
    public void test1CreateContact(){
        Contact contact = new Contact("Рязанов", "Михаил", "Рязань");
        CarBrand carBrand = carBrandDAO.getByName(carBrandName);
        if (carBrand == null){
            carBrand = new CarBrand(carBrandName);
        }
        cars = new HashSet<Car>();
        cars.add(new Car("a000aa62", "шестерка", carBrand));
        cars.add(new Car("b000bb62","девятка", carBrand));
        contact.setCars(cars);
        contactDAO.add(contact);
        contactTest = contact;
        assertEquals(contact.getId(), contactDAO.getById(contact.getId()).getId());
        assertEquals(contact.getCars().size(), contactDAO.getByIdWithCar(contact.getId()).getCars().size());
    }

    @Test
    public void test2GetContactById(){
        Contact contact = contactDAO.getById(contactTest.getId());
        assertEquals(contactTest.getId(), contact.getId());
    }

    @Test
    public void test3UpdateContact(){
        contactTest.setLastName("Невежин");
        contactTest.setFirstName("Роман");
        contactDAO.update(contactTest);

        assertEquals(contactTest.getLastName(), contactDAO.getById(contactTest.getId()).getLastName());
        assertEquals(contactTest.getFirstName(), contactDAO.getById(contactTest.getId()).getFirstName());
    }

    @Test
    public void test4DeleteContact(){
        Integer idContact = contactTest.getId();
        contactDAO.delete(contactTest);
        assertNull(contactDAO.getById(idContact));
    }
}
