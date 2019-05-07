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
@Table(name = "discount")
@NamedQueries(
        @NamedQuery(name = "queryAllDiscounts",
                query = "SELECT discount FROM Discount discount ")
)
public class Discount {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value")
    private BigDecimal value;

}
