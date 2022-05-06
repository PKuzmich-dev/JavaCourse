package Unit7.Task2.DAO;

import Unit7.Task2.Entity.Brand;
import Unit7.Task2.Entity.Car;
import Unit7.Task2.Entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements CRUDDAO<Contact> {
    private Connection connection;
    public ContactDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Contact> getContactByCarBrand(String brand) throws SQLException {
        Statement s = connection.createStatement();
        List<Contact> lc = new ArrayList<>();
        ResultSet r = s.executeQuery("select distinct contact.* from contact, contact_car, car, car_brand where "
                                         + "car_brand.brand = '" + brand + "' and "
                                         + "car.brand_id = car_brand.id and "
                                         + "contact_car.car_id = car.id and "
                                         + "contact.deleted is null and "
                                         + "contact.id = contact_car.contact_id");
        while(r.next()){
            lc.add(new Contact(r.getInt("id"),
                               r.getString("last_name"),
                               r.getString("first_name"),
                               r.getString("mid_name"),
                               r.getString("city"), 0, null));
        }
        return lc;
    }

    public Contact getContactById(int id) throws SQLException {
        Statement s = connection.createStatement();
        ResultSet r = s.executeQuery("select * from contact where "
                                         + "contact.id = '" + id + "'");
        if (r.next()){
            return new Contact(r.getInt("id"),
                        r.getString("last_name"),
                        r.getString("first_name"),
                        r.getString("mid_name"),
                        r.getString("city"), 0,null);
        }
        else{
            return null;
        }
    }

    @Override
    public void add(Contact contact) {
        if (contact.getId() != null) {
            return;
        }
        ResultSet rs;
        try(PreparedStatement st = connection.prepareStatement("insert into contact (last_name, first_name, "
                                                                   + "mid_name, city) "
                                                                   + "values (?, ?, ?, ?) returning id")){

            st.setString(1, contact.getLastName());
            st.setString(2, contact.getFirstName());
            st.setString(3, contact.getMiddleName());
            st.setString(4, contact.getCity());

            rs = st.executeQuery();
            if (rs.next()) {
                contact.setId(rs.getInt("id"));
                contact.setVersion(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // добавляем к контакту тачки
        addCars(contact.getId(), contact.getListCars());
    }

    private void addCars(int idContact, List<Car> cars){
        // добавляем к контакту тачки
        try(PreparedStatement st = connection.prepareStatement("insert into contact_car (contact_id, car_id) "
                                                                   + "values (?, ?)")){
            for(Car car : cars){
                st.setInt(1, idContact);
                st.setInt(2, car.getId());
                st.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteCars(int idContact, List<Car> cars){
        // добавляем к контакту тачки
        try(PreparedStatement st = connection.prepareStatement("delete from contact_car where contact_id=? and "
                                                                   + "car_id = ? ")){
            for(Car car : cars){
                st.setInt(1, idContact);
                st.setInt(2, car.getId());
                st.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int delete(Contact contact) {
        int result = 0;
        try(Statement st = connection.createStatement()){
            result =
                st.executeUpdate("update contact set deleted = current_timestamp where id = '" + contact.getId() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Contact contact) {
        ResultSet rs;
        int cnt;
        try(PreparedStatement st = connection.prepareStatement("update contact set last_name=?, first_name=?, "
                                                                   + "mid_name=?,  city=?, version = ? "
                                                                   + "where id=? and version = ?")){
            st.setString(1, contact.getLastName());
            st.setString(2, contact.getFirstName());
            st.setString(3, contact.getMiddleName());
            st.setString(4, contact.getCity());
            st.setInt(5, contact.getVersion()+1);
            st.setInt(6, contact.getId());
            st.setInt(7, contact.getVersion());
            cnt  = st.executeUpdate();
            if (cnt == 0) {
                System.out.println("Запись была изменена другим пользователем");
                return;
            }

            // получаем машины контакта из базы
            List<Car> bdlistCars = new CarDAO(connection).getCarByContact(contact.getId());
            List<Car> newCars = new ArrayList<>(contact.getListCars());
            // из списка удаляем машины, которые уже есть
            newCars.removeAll(bdlistCars);
            // добавляем новые тачки
            addCars(contact.getId(), newCars);

            // формируем список тачек, которых не должно быть у контакта
            bdlistCars.removeAll(contact.getListCars());
            deleteCars(contact.getId(), bdlistCars);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Contact> get(String searchSpec) {
        List<Contact> contacts = new ArrayList<>();
        ResultSet rs;
        try(Statement st = connection.createStatement()){
            rs = st.executeQuery("select id, last_name, first_name, mid_name, city, version from contact where "
                                     + "deleted is null " +
                                     ((searchSpec==null) ? "" : "and "+searchSpec));
            while(rs.next()){
                List<Car> listcars = new CarDAO(connection).getCarByContact(rs.getInt("id"));
                contacts.add(new Contact(rs.getInt("id"), rs.getString("last_name"),
                                         rs.getString("first_name"), rs.getString("mid_name"),
                                         rs.getString("city"), rs.getInt("version"), listcars));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }
}
