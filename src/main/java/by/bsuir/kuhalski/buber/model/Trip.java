package by.bsuir.kuhalski.buber.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;

@EqualsAndHashCode
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Scope("prototype")
@Table(name = "trip")
@NamedQueries(
        @NamedQuery(name = "queryAllTrips",
                query = "SELECT trip FROM Trip trip ")
)
public class Trip {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "start_region_id")
    private Region startRegion;

    @ManyToOne
    @JoinColumn(name = "end_region_id")
    private Region endRegion;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "is_payed")
    private boolean isPayed;

    @Column(name = "is_finished")
    private boolean isFinished;

    @PostConstruct
    public void init() {
        isAccepted = false;
        isPayed = false;
        isFinished = false;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ",\n client=" + client.getLogin() +
                ",\n driver=" + driver.getLogin() +
                ",\n car=" + car.getCarNumber() +
                ",\n startRegion=" + startRegion.getName() +
                ",\n endRegion=" + endRegion.getName() +
                ",\n discount=" + discount.getValue() +
                ",\n isAccepted=" + isAccepted +
                ",\n isPayed=" + isPayed +
                ",\n isFinished=" + isFinished +
                '}';
    }
}

