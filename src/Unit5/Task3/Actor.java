package Unit5.Task3;

import java.io.Serializable;

public class Actor implements Serializable {
    private String name;

    public Actor(String lastName) {
        this.name = lastName;
    }

    @Override
    public String toString() {
        return name;
    }
}
