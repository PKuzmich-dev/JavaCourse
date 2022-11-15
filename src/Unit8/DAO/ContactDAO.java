package Unit8.DAO;

import Unit8.domain.CarBrand;
import Unit8.domain.Contact;
import org.hibernate.Session;

public class ContactDAO implements CRUDDAO<Contact>{

    @Override
    public void add(Contact contact) {
        HibernateUtil.doInHibernate(session -> session.save(contact));
    }

    @Override
    public void delete(Contact contact) {
        HibernateUtil.doInHibernate(session -> session.delete(contact));
    }

    @Override
    public void update(Contact contact) {
        HibernateUtil.doInHibernate(session -> session.update(contact));
    }

    @Override
    public Contact getById(Integer id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Contact contact = session.find(Contact.class, id);
            session.getTransaction().commit();
            return contact;
        }
    }

    public Contact getByIdWithCar(Integer id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Contact contact = (Contact) session
                .createQuery("select c from Contact c left join fetch c.cars where c.id = :id")
                .setParameter("id",id).getResultList().get(0);
            session.getTransaction().commit();
            return contact;
        }
    }
}
