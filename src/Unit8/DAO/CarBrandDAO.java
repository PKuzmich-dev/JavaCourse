package Unit8.DAO;

import Unit8.domain.CarBrand;
import java.util.function.Consumer;
import javax.persistence.NoResultException;
import org.hibernate.Session;

public class CarBrandDAO implements CRUDDAO<CarBrand>{

    @Override
    public void add(CarBrand carBrand) {
        HibernateUtil.doInHibernate(session -> session.save(carBrand));
    }

    @Override
    public void delete(CarBrand carBrand) {
        HibernateUtil.doInHibernate(session -> session.delete(carBrand));
    }

    @Override
    public void update(CarBrand carBrand) {
        HibernateUtil.doInHibernate(session -> session.update(carBrand));
    }
    @Override
    public CarBrand getById(Integer id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CarBrand carBrand = session.find(CarBrand.class, id);
            session.getTransaction().commit();
            return carBrand;
        }
    }

    public CarBrand getByName(String name) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            CarBrand carBrand = session.createQuery("select cb from CarBrand cb where cb.brand = ?1", CarBrand.class)
                .setParameter(1, name).getSingleResult();
            System.out.println("Найдена марка " + name);
            session.getTransaction().commit();
            return carBrand;
        }
        catch(NoResultException e){
            System.out.println("Не найдена марка " + name);
            return null;
        }
    }
}