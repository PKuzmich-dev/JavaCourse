package Unit2.Task1;

import java.util.ArrayList;

/**
 * Сотрудник. Содержит идентификатор, фамилию и массив канцтоваров
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public class Employee {
    private int id;
    private String lastName;
    private ArrayList<Stationery> listStationery;

    /**
     * Создает сотрудника с заданным id и фамилией
     *
     */
    Employee(int id, String lasName){
        this.id = id;
        this.lastName = lasName;
        listStationery = new ArrayList();
    }

    /**
     * добавляет канцелярку сотруднику
     *
     */
    void addStationery(Stationery stationary){
        listStationery.add(stationary);
    }

    /**
     * возвращает стоимость канцелярки сотрудника
     *
     */
    double getCost(){
        double result = 0;
        for (Stationery s : listStationery){
            result += s.getCost();
        }
        return result;
    }
    /**
     * меняет стоимость определенной канцелярки
     *
     * @param intType идентификатор типа канцелярки
     * @param cost стоимость канцелярки
     */
    void setStationeryCost(int intType, double cost){
        for (Stationery s : listStationery){
           if (intType == 1 && s instanceof Pen){
               s.setCost(cost);
           }
           if (intType == 2 && s instanceof Notepad){
               s.setCost(cost);
           }
            if (intType == 3 && s instanceof Marker){
                s.setCost(cost);
            }
        }
    }

    @Override
    public String toString() {
        String result;
        result  = lastName + " [";
        for (Stationery s : listStationery){
            result += s + "; ";
        }
        result += "]";
        return result;
    }

    @Override
    public int hashCode() {
        return id + lastName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Employee emp = (Employee) obj;
        return (id == emp.id) && (lastName.equals(emp.lastName));
    }
}
