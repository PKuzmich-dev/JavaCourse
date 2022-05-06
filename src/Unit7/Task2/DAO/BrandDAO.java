package Unit7.Task2.DAO;

import Unit7.Task2.Entity.Brand;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO implements CRUDDAO<Brand> {
    private Connection connection;

    public BrandDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Brand brand) {
        if (brand.getId() != null) {
            return;
        }
        ResultSet rs;
        try(Statement st = connection.createStatement()){
            rs = st.executeQuery("insert into car_brand (brand) values ('" + brand.getBrand() + "') returning id");
            if (rs.next())
                brand.setId(rs.getInt("id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int delete(Brand brand) {
        int result = 0;
        try(Statement st = connection.createStatement()){
            result = st.executeUpdate("delete from car_brand where brand = '" + brand.getBrand() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Brand brand) {
        try(Statement st = connection.createStatement()){
            st.execute("update car_brand set brand = '" + brand.getBrand() + "' where "
                                          + "id = '" + brand.getId() + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Brand> get(String searchSpec) {
        List<Brand> brands = new ArrayList<>();
        ResultSet rs;
        try(Statement st = connection.createStatement()){
            rs = st.executeQuery("select id, brand from car_brand " +
                                     ((searchSpec==null) ? "" : "where "+searchSpec));
            while(rs.next()){
                brands.add(new Brand(rs.getInt("id"), rs.getString("brand")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return brands;
    }

    public int getIdByBrand(String brand){
        return get("brand = '" + brand + "'").get(0).getId();
    }
}
