package EGAR.Technology.EGAR_Technology.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;
    private String brand;
    private String model;
    private String category;
    @Column(name = "state_number")
    private String stateNumber;
    @Enumerated(EnumType.STRING)
    private CarType type;
    @Column(name = "year_of_release")
    private int yearOfRelease;
    private Boolean trailer;


}
