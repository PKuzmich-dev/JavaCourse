package Unit2.Task1;
/**
 * Канцтовар. Содержит название и стоимость
 *
 * @author Кузмич Павел
 * @version 1.0
 */
public abstract class Stationery {
    protected String name;
    protected double cost;

    Stationery(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    void setCost(double cost){
        this.cost = cost;
    }

    void setCost(long cost){
        this.cost = cost;
    }

    void setCost(int cost){
        this.cost = cost;
    }

    double getCost(){
        return cost;
    }

    @Override
    public String toString() {
        return name + " " + cost;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || !(obj instanceof Stationery)){
            return false;
        }
        Stationery s = (Stationery) obj;
        return name.equals(s.name) && (cost == s.cost);
    }
}
