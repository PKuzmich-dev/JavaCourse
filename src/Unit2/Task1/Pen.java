package Unit2.Task1;

public class Pen extends Stationery {

    public Pen(String name, double cost) {
        super(name, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        return super.equals(obj);
    }
}
