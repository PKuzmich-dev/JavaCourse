package Unit8.DAO;

import Unit8.domain.Car;
import Unit8.domain.CarBrand;
import Unit8.domain.Contact;
import javax.persistence.NoResultException;
import org.hibernate.Session;

public class CarDAO implements CRUDDAO<Car>{

    @Override
    public void add(Car car){
        HibernateUtil.doInHibernate(session ->
                                    {
                                        session.save(car);
                                    }
                                    );
    }

    @Override
    public void delete(Car car) {
        HibernateUtil.doInHibernate(session -> {
            Car carInner  = session.createQuery("select c from Car c left join fetch c.contacts where c.id = ?1",
                                      Car.class)
                .setParameter(1, car.getId()).getSingleResult();
            for (Contact contact : carInner.getContacts())
                carInner.removeContact(contact);
            session.delete(carInner);
        });
    }

    @Override
    public void update(Car car) {
        HibernateUtil.doInHibernate(session ->
                                    {
                                        session.update(car);
                                    }
        );
    }

    @Override
    public Car getById(Integer id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Car car = session.find(Car.class, id);
            session.getTransaction().commit();
            return car;
        }
    }

    public Car getByNum(String num) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            Car car = session.createQuery("select car from Car car where car.num = ?1", Car.class)
                .setParameter(1, num).getSingleResult();
            System.out.println("Найден автомобиль с номером " + num);
            session.getTransaction().commit();
            return car;
        }
        catch(NoResultException e){
            System.out.println("Не найдена автомобиль с номером " + num);
            return null;
        }
    }

    public void addCartoContact(Car car, Contact contact){
        contact.getCars().add(car);
        new ContactDAO().update(contact);
    }
}
