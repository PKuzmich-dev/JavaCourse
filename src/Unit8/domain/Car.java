package Unit8.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @GeneratedValue
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "num", nullable = false, length = 20)
    private String num;
    @Basic
    @Column(name = "model", nullable = true, length = 50)
    private String model;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name="brand_id")
    private CarBrand carBrand;

    @ManyToMany(mappedBy = "cars")
    Set<Contact> contacts = new HashSet<>();

    public Car(String num, String model, CarBrand carBrand){
        this.num = num;
        this.model = model;
        this.carBrand = carBrand;
    }

    public void removeContact(Contact contact){
        contacts.remove(contact);
        contact.getCars().remove(this);
    }
}
