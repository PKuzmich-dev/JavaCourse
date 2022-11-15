package Unit8.domain;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OrderBy;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Contact {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "mid_name", nullable = true, length = 50)
    private String midName;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "deleted", nullable = true)
    private Timestamp deleted;

    @ManyToMany
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name="contact_car",
            joinColumns = {@JoinColumn(name="contact_id")},
        inverseJoinColumns = {@JoinColumn(name="car_id")}
        )
    Set<Car> cars = new HashSet<>();

    public Contact(String lastName, String firstName, String city){
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
    }

}
