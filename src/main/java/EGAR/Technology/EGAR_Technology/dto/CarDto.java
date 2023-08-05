package EGAR.Technology.EGAR_Technology.dto;

import EGAR.Technology.EGAR_Technology.model.CarType;

import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String category;
    private String stateNumber;
    private CarType type;
    private int yearOfRelease;
    private Boolean trailer;
}
