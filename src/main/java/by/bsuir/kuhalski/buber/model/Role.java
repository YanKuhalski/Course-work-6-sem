package by.bsuir.kuhalski.buber.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Scope("prototype")
@Table(name = "role")
@NamedQueries(
        @NamedQuery(name = "queryAllRoles",
                query = "SELECT role FROM Role role ")
)
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id ;

    @Column(name = "role",nullable = false)
    private String role;
}
