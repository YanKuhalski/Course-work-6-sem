package by.bsuir.kuhalski.buber.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Scope("prototype")
@Table(name = "region")
@NamedQueries(
        @NamedQuery(name = "queryAllRegions",
                query = "SELECT region FROM Region region ")
)
public class Region {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "zone_number")
    private int value;
}
