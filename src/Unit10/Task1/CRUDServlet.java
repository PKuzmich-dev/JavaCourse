package Unit10.Task1;

import GsonTest.Animal;
import Unit8.DAO.CarBrandDAO;
import Unit8.DAO.CarDAO;
import Unit8.DAO.ContactDAO;
import Unit8.domain.Car;
import Unit8.domain.CarBrand;
import Unit8.domain.Contact;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectWriter;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.util.JSONPObject;

public class CRUDServlet extends HttpServlet {
    private CarDAO carDAO = new CarDAO();
    private CarBrandDAO carBrandDAO = new CarBrandDAO();
    private ContactDAO contactDAO = new ContactDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "application/json");
        Object obj = null;
        Integer id = Integer.parseInt(req.getParameter("id"));
        switch(req.getRequestURI()){
            case "/cars":
                obj = carDAO.getById(id);
                break;
            case "/brands":
                obj = carBrandDAO.getById(id);
                break;
            case "/contacts":
                obj = contactDAO.getById(id);
                break;
            default:
                resp.setStatus(406);
                return;
        }
        if (obj == null)
            resp.setStatus(404);
        else
            resp.getWriter().print(objectToJSON(obj));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();
        Integer id = null;
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        CarBrand carBrand;
        Car car;
        Contact contact;

        resp.setHeader("Content-Type", "application/json");
        switch(req.getRequestURI()){
            case "/cars":
                // {"num":"m001mm33","model":"восьмерка","brand":"KIA_Test2"}
                String brandName = jsonObject.get("brand").getAsString();
                carBrand = carBrandDAO.getByName(brandName);
                car = gson.fromJson(json, Car.class);
                car.setCarBrand(carBrand);
                carDAO.add(car);
                id = car.getId();
                break;
            case "/brands":
                // {"brand":"Toyota"}
                carBrand = gson.fromJson(json, CarBrand.class);
                carBrandDAO.add(carBrand);
                id = carBrand.getId();
                break;
            case "/contacts":
                //{"lastName":"Ступин","firstName":"Константин","midName":"Валентинович","city":"Орел"}
                contact = gson.fromJson(json, Contact.class);
                contactDAO.add(contact);
                id = contact.getId();
                break;
            case "/contactscar":
                //{"carid":1,"contactid":2}
                car = carDAO.getById(jsonObject.get("carid").getAsInt());
                if (car == null){
                    resp.setStatus(404);
                    return;
                }
                contact = contactDAO.getByIdWithCar(jsonObject.get("contactid").getAsInt());
                if (contact == null){
                    resp.setStatus(404);
                    return;
                }
                carDAO.addCartoContact(car, contact);
                break;
            default:
                resp.setStatus(406);
                return;
        }
        if (id != null)
            resp.getWriter().print("{\"id\":\"" + id + "\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        switch(req.getRequestURI()){
            case "/cars":
                Car car = carDAO.getById(id);
                if (car == null) {
                    resp.setStatus(404);
                    return;
                }
                carDAO.delete(car);
                break;
            case "/brands":
                CarBrand carBrand = carBrandDAO.getById(id);
                if (carBrand == null) {
                    resp.setStatus(404);
                    return;
                }
                carBrandDAO.delete(carBrand);
                break;
            case "/contacts":
                Contact contact = contactDAO.getById(id);
                if (contact == null) {
                    resp.setStatus(404);
                    return;
                }
                contactDAO.delete(contact);
                break;
            default:
                resp.setStatus(406);
                return;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        CarBrand carBrand;

        switch(req.getRequestURI()) {
            case "/brands":
                carBrand = gson.fromJson(json, CarBrand.class);
                carBrandDAO.update(carBrand);
                break;
            case "/cars":
                //{"id":683,"model":"восьмерка2","num":"m005mm33","brand":"Volvo"}
                String brandName = jsonObject.get("brand").getAsString();
                if (brandName == null){
                    resp.setStatus(400);
                    return;
                }
                carBrand = carBrandDAO.getByName(brandName);
                if (carBrand == null){
                    resp.setStatus(404);
                    return;
                }
                Car car = gson.fromJson(json, Car.class);
                car.setCarBrand(carBrand);
                carDAO.update(car);
                break;
            case "/contacts":
                //{"id":686,"lastName":"Фамилия","firstName":"Имя","midName":"Отчество","city":"Москва","version":0}
                Contact contact = gson.fromJson(json, Contact.class);
                contactDAO.update(contact);
                break;
            default:
                resp.setStatus(406);
                return;
        }
    }

    <T> String objectToJSON(T obj){
        if (obj instanceof Car){
            Car car = (Car) obj;
            return "{\"id\":\"" + car.getId() + "\",\"num\":\"" + car.getNum() + "\",\"model\":\"" + car.getModel() +
                "\",\"brand\":\"" + car.getCarBrand().getBrand() + "\"}";
        }
        if (obj instanceof CarBrand){
            CarBrand carBrand = (CarBrand) obj;
            return "{\"id\":\"" + carBrand.getId() + "\",\"brand\":\"" + carBrand.getBrand() + "\"}";
        }
        if (obj instanceof Contact){
            Contact contact = (Contact) obj;
            return "{\"id\":\"" + contact.getId() + "\",\"lastName\":\"" + contact.getLastName() +
                "\",\"firstName\":\"" + contact.getFirstName() + "\",\"midName\":\"" + contact.getMidName() +
                "\",\"city\":\"" + contact.getCity() +"\",\"version\":" + contact.getVersion() + "}";
        }
        return "{\"error\":\"объект не определен\"}";
    }
}
