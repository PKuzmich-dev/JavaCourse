package Unit8.DAO;

public interface CRUDDAO<T> {
    void add(T t);
    void delete(T t);
    void update(T t);
    T getById(Integer id);
}
