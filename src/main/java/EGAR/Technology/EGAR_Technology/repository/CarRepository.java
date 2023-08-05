package EGAR.Technology.EGAR_Technology.repository;

import EGAR.Technology.EGAR_Technology.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAll(Pageable pageable);

    Page<Car> findByBrandOrModelOrCategoryOrStateNumberOrYearOfRelease(@Param("brand") String brand,
                                                                       @Param("model") String model,
                                                                       @Param("category") String category,
                                                                       @Param("stateNumber") String stateNumber,
                                                                       @Param("yearOfRelease") int yearOfRelease,
                                                                       Pageable pageable);


}