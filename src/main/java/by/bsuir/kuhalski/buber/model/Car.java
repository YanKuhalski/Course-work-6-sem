package by.bsuir.kuhalski.buber.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"driver"})
@Getter
@Setter
@ToString(exclude = {"driver"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Scope("prototype")
@Table(name = "car")
@NamedQueries(
        @NamedQuery(name = "queryAllCars",
                query = "SELECT car FROM Car car ")
)
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_number")
    private String carNumber;

    @OneToOne
    @JoinColumn(name="driver")
    private User driver;
}
