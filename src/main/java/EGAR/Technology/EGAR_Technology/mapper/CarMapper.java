package EGAR.Technology.EGAR_Technology.mapper;

import EGAR.Technology.EGAR_Technology.dto.CarDto;
import EGAR.Technology.EGAR_Technology.model.Car;

public class CarMapper {

    public static Car toCar(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .category(carDto.getCategory())
                .stateNumber(carDto.getStateNumber())
                .type(carDto.getType())
                .yearOfRelease(carDto.getYearOfRelease())
                .trailer(carDto.getTrailer())
                .build();
    }

    public static CarDto toCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .category(car.getCategory())
                .stateNumber(car.getStateNumber())
                .type(car.getType())
                .yearOfRelease(car.getYearOfRelease())
                .trailer(car.getTrailer())
                .build();
    }
}
