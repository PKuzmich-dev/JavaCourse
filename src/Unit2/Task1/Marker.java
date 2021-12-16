package Unit2.Task1;

public class Marker extends Stationery {
    private String color;

    public Marker(String name, double cost, String color) {
        super(name, cost);
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Marker m = (Marker) obj;
        return (super.equals(m)) && color.equals(m.color);
    }
}
