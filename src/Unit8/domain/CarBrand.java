package Unit8.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car_brand", schema = "public", catalog = "Unit7")
@Getter
@Setter
@NoArgsConstructor
public class CarBrand {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    public CarBrand(String brand){
        this.brand = brand;
    }

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name="brand_id")
    //List<Car> cars = new ArrayList<Car>();

}
