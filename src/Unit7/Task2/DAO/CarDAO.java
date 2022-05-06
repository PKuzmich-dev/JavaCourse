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

public class CarDAO implements CRUDDAO<Car> {
    private Connection connection;
    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Car> getCarByContact(int id) throws SQLException {
        Statement s = connection.createStatement();
        List<Car> lc = new ArrayList<>();
        ResultSet r = s.executeQuery("select distinct car.id, car.num, car_brand.brand, car.model from "
                                         + "contact_car, car, car_brand, contact "
                                         + "where "
                                         + "contact.id = contact_car.contact_id and "
                                         + "contact.id = '" + id + "' and "
                                         + "contact_car.car_id = car.id and "
                                         + "contact.deleted is null and "
                                         + "car.brand_id = car_brand.id");
        while(r.next()){
            lc.add(new Car(r.getInt("id"),
                               r.getString("num"),
                               r.getString("brand"),
                               r.getString("model")));
        }
        return lc;
    }

    public int deleteCarForContact(Contact contact) throws SQLException {
        Statement s = connection.createStatement();
        return s.executeUpdate("delete from contact_car where contact_id = '" + contact.getId() + "'");
    }

    @Override
    public void add(Car car) {
        ResultSet rs;
        try(PreparedStatement st = connection.prepareStatement("insert into car (num, brand_id, model) "
                                                                   + "values (?, ?, ?) returning id")){

            st.setString(1, car.getNum());
            st.setInt(2, new BrandDAO(connection).getIdByBrand(car.getBrand()));
            st.setString(3, car.getModel());

            rs = st.executeQuery();
            if (rs.next())
                car.setId(rs.getInt("id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int delete(Car car) {
        int result = 0;
        try(Statement st = connection.createStatement()){
            result = st.executeUpdate("delete from car where id = '" + car.getId() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Car car) {
        try(PreparedStatement st = connection.prepareStatement("update car set num=?, brand_id=?, model=? "
                                                                   + "where id=?")){
            st.setString(1, car.getNum());
            st.setInt(2, new BrandDAO(connection).getIdByBrand(car.getBrand()));
            st.setString(3, car.getModel());
            st.setInt(4, car.getId());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Car> get(String searchSpec) {
        List<Car> cars = new ArrayList<>();
        ResultSet rs;
        try(Statement st = connection.createStatement()){
            rs = st.executeQuery("select car.id, car.num, car_brand.brand, car.model from car, car_brand "
                                     + "where car.brand_id = car_brand.id "
                                     + ((searchSpec==null) ? "" : "and "+searchSpec));
            while(rs.next()){
                cars.add(new Car(rs.getInt("id"), rs.getString("num"),
                                 rs.getString("brand"), rs.getString("model")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cars;
    }
}
