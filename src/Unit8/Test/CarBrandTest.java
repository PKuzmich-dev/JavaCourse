package Unit8.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import Unit8.DAO.CarBrandDAO;
import Unit8.DAO.ContactDAO;
import Unit8.domain.CarBrand;
import Unit8.domain.Contact;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarBrandTest {

    private static String carBrandName = "KIA_Test";
    private static String carBrandName2 = "KIA_Test2";
    private static CarBrandDAO carBrandDAO = new CarBrandDAO();
    private static ContactDAO contactDAO = new ContactDAO();
    private static Contact contactIdForDelete;

    @AfterClass
    public static void after(){
        CarBrand carBrand = carBrandDAO.getByName(carBrandName);
        if (carBrand != null)
            carBrandDAO.delete(carBrand);
        CarBrand carBrand2 = carBrandDAO.getByName(carBrandName2);
        if (carBrand2 != null)
            carBrandDAO.delete(carBrand2);
        if (contactIdForDelete != null)
            contactDAO.delete(contactIdForDelete);
    }

    @Test
    public void test1CreateCarBrand() throws Exception {
        CarBrand carBrand = new CarBrand(carBrandName);
        carBrandDAO.add(carBrand);
        assertEquals(carBrandName, carBrandDAO.getById(carBrand.getId()).getBrand());
    }

    @Test
    public void test2GetCarBrandByName(){
        CarBrand carBrand = carBrandDAO.getByName(carBrandName);
        assertEquals(carBrandName, carBrand.getBrand());
    }

    @Test
    public void test3UpdateBrand(){
        CarBrand carBrand = carBrandDAO.getByName(carBrandName);
        carBrand.setBrand(carBrandName2);
        carBrandDAO.update(carBrand);
        assertEquals(carBrandName2, carBrandDAO.getById(carBrand.getId()).getBrand());
    }

    @Test
    public void test4DeleteCarBrand(){
        CarBrand carBrand = carBrandDAO.getByName(carBrandName2);
        carBrandDAO.delete(carBrand);
        carBrand = carBrandDAO.getByName(carBrandName2);
        assertNull(carBrand);
    }
}
