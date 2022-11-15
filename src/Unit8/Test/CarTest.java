package Unit8.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import Unit8.DAO.CarBrandDAO;
import Unit8.DAO.CarDAO;
import Unit8.DAO.ContactDAO;
import Unit8.domain.Car;
import Unit8.domain.CarBrand;
import Unit8.domain.Contact;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarTest {

    private static String carBrandName = "KIA_Test";
    private static String carBrandName2 = "KIA_Test2";
    private static String carNum = "a000aa33";

    private static Contact contactTest;
    private static Car carTest;

    private static CarBrandDAO carBrandDAO = new CarBrandDAO();
    private static CarDAO carDAO = new CarDAO();
    private static ContactDAO contactDAO = new ContactDAO();

    @BeforeClass
    public static void before() {
        //contactTest = contactDAO.getById(405);
        contactTest = new Contact("Дьяконов", "Михаил", "Рязань");
        contactDAO.add(contactTest);
    }

    @AfterClass
    public static void after() {
        System.out.println("after");
        if (carTest != null)
            carDAO.delete(carTest);
        CarBrand carBrand = carBrandDAO.getByName(carBrandName);
        if (carBrand != null)
            carBrandDAO.delete(carBrand);
        carBrand = carBrandDAO.getByName(carBrandName2);
        if (carBrand != null)
            carBrandDAO.delete(carBrand);
        if (contactTest != null) {
            contactTest = contactDAO.getById(contactTest.getId());
            contactDAO.delete(contactTest);
        }
    }

    @Test
    public void test1CreateCar(){
        carTest = new Car(carNum, "пятерка", new CarBrand("KIA_Test"));

        carDAO.add(carTest);

        Car actualCar = carDAO.getById(carTest.getId());
        assertEquals(carTest.getId(), actualCar.getId());
        assertEquals(carTest.getCarBrand().getBrand(), actualCar.getCarBrand().getBrand());
    }

    @Test
    public void test2GetCarByNum(){
        carTest = carDAO.getByNum(carNum);

        Car actualCar = carDAO.getById(carTest.getId());
        assertEquals(carTest.getId(), actualCar.getId());
        assertEquals(carTest.getCarBrand().getBrand(), actualCar.getCarBrand().getBrand());
    }

    @Test
    public void test3UpdateCar(){
        carTest = carDAO.getByNum(carNum);
        Integer carId = carTest.getId();

        carTest.setNum("b000bb33");
        carTest.setCarBrand(new CarBrand(carBrandName2));
        carTest.setModel("шестерка");

        carDAO.update(carTest);

        Car actualCar = carDAO.getById(carId);
        assertEquals(carTest.getId(), actualCar.getId());
        assertEquals(carTest.getCarBrand().getBrand(), actualCar.getCarBrand().getBrand());
        assertEquals(carTest.getModel(), actualCar.getModel());
    }

    @Test
    public void test4AddCarToContact(){

        Integer idContact = contactTest.getId();
        System.out.println("idContact = " + idContact);
        contactTest = contactDAO.getByIdWithCar(idContact);
        assertEquals(0, contactTest.getCars().size());
        carDAO.addCartoContact(carTest, contactTest);

        contactTest = contactDAO.getByIdWithCar(idContact);
        assertEquals(1, contactTest.getCars().size());
    }

    @Test
    public void test5DeleteCar(){
        String carNum = carTest.getNum();
        assertNotNull(carTest);
        carDAO.delete(carTest);

        carTest = carDAO.getByNum(carNum);
        assertNull(carTest);
    }

}
