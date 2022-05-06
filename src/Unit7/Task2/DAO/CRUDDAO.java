package Unit7.Task2.DAO;

import java.util.List;

public interface CRUDDAO<T> {
    void add(T t);
    int delete(T t);
    void update(T t);
    List<T> get(String searchSpec);
}
