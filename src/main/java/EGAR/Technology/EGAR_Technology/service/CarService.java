package EGAR.Technology.EGAR_Technology.service;

import EGAR.Technology.EGAR_Technology.dto.CarDto;
import EGAR.Technology.EGAR_Technology.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface CarService {

    CarDto createCar(CarDto carDto);

    void delete(Long id);

    CarDto getCar(Long id);

    Page<Car> findAllById(Pageable pageable);

    void updateCar(Car newCar);

    Page<Car> findByParam(String brand, String model, String category,
                          String stateNumber, Integer yearOfRelease, Pageable pageable);
}
