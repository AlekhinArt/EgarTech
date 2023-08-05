package EGAR.Technology.EGAR_Technology.service;

import EGAR.Technology.EGAR_Technology.dto.CarDto;
import EGAR.Technology.EGAR_Technology.exception.DataConflictException;
import EGAR.Technology.EGAR_Technology.exception.NotFoundException;
import EGAR.Technology.EGAR_Technology.mapper.CarMapper;
import EGAR.Technology.EGAR_Technology.model.Car;
import EGAR.Technology.EGAR_Technology.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto createCar(CarDto carDto) {
        Car car = CarMapper.toCar(carDto);

        try {
            car = carRepository.save(car);
        } catch (Exception e) {
            throw new DataConflictException("State number : " + car.getStateNumber() + " is already exist");
        }
        return CarMapper.toCarDto(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCar(Long id) {
        return CarMapper.toCarDto(carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car with id " + id + " not found")));

    }

    @Override
    public Page<Car> findAllById(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public void updateCar(Car newCar) {
        Car oldCar = carRepository.findById(newCar.getId())
                .orElseThrow(() -> new NotFoundException("Car with id " + newCar.getId() + " not found"));
        oldCar.setBrand(newCar.getBrand());
        oldCar.setModel(newCar.getModel());
        oldCar.setCategory(newCar.getCategory());
        oldCar.setStateNumber(newCar.getStateNumber());
        oldCar.setType(newCar.getType());
        oldCar.setYearOfRelease(newCar.getYearOfRelease());
        oldCar.setTrailer(newCar.getTrailer());
        carRepository.save(oldCar);

    }

    @Override
    public Page<Car> findByParam(String brand, String model, String category,
                                 String stateNumber, Integer yearOfRelease, Pageable pageable) {

        return carRepository.findByBrandOrModelOrCategoryOrStateNumberOrYearOfRelease(brand, model, category,
                stateNumber, yearOfRelease, pageable);
    }
}
