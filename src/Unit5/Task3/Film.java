package Unit5.Task3;


import java.io.Serializable;
import java.util.HashSet;

public class Film implements Serializable {
    private String name;
    HashSet<Actor> actors;

    public Film(String name, HashSet<Actor> actors) {
        this.name = name;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return name + actors.toString();
    }

    public void edit(String name, HashSet<Actor> actors) {
        this.name = name;
        this.actors = actors;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Film f = (Film) obj;
        return this.name == f.name;
    }
}
