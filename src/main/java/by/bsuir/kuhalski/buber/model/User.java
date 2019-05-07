package by.bsuir.kuhalski.buber.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(exclude = {"roles", "discounts","car","tripsAsDriver","tripsAsClient"})
@Getter
@Setter
@ToString(exclude = {"discounts", "car", "tripsAsDriver", "tripsAsClient"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Scope("prototype")
@Table(name = "users")
@NamedQueries(
        @NamedQuery(name = "queryAllUsers",
                query = "SELECT users FROM User users ")
)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", length = 15)
    private String login;

    @Column(name = "password", length = 15)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_discount",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id", referencedColumnName = "id")}
    )
    private Set<Discount> discounts;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Car  car ;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Trip> tripsAsClient;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Trip> tripsAsDriver;

    @PostConstruct
    public void init() {
        tripsAsClient = new ArrayList<>();
        tripsAsDriver = new ArrayList<>();
        roles = new HashSet<>();
        discounts = new HashSet<>();
    }
}
