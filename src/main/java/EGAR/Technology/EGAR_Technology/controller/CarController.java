package EGAR.Technology.EGAR_Technology.controller;

import EGAR.Technology.EGAR_Technology.dto.CarDto;
import EGAR.Technology.EGAR_Technology.model.Car;
import EGAR.Technology.EGAR_Technology.model.CarType;
import EGAR.Technology.EGAR_Technology.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final String filterMethod = "ALL";
    private final String sortDateMethod = "ASC";

    @PostMapping("/save")
    public String createCar(@RequestParam String brand,
                            @RequestParam String model,
                            @RequestParam String category,
                            @RequestParam String stateNumber,
                            @RequestParam String type,
                            @RequestParam String yearOfRelease,
                            @RequestParam Boolean trailer
    ) {
        log.info("Create car carDto: {},{},{},{},{},{},{};",
                brand, model, category, stateNumber, type, yearOfRelease, trailer);

        carService.createCar(CarDto.builder()
                .brand(brand)
                .model(model)
                .category(category)
                .stateNumber(stateNumber)
                .yearOfRelease(Integer.parseInt(yearOfRelease))
                .type(CarType.valueOf(type))
                .trailer(trailer)
                .build());
        return "redirect:/";

    }

    @GetMapping("/car/{id}")
    public CarDto getCar(@PathVariable Long id) {
        log.info("Get car with id: {};", id);
        return carService.getCar(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        log.info("delete car with id: {};", id);
        carService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newCar() {
        return "operations/new";
    }

    @GetMapping("/")
    public String list(Model model, Pageable pageable) {
        Page<Car> notePage = carService.findAllById(pageable);
        PageWrapper<Car> page = new PageWrapper<Car>(notePage, "/");
        model.addAttribute("cars", page.getContent());
        model.addAttribute("sort", sortDateMethod);
        model.addAttribute("filter", filterMethod);
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/search")
    public String searchBy(@RequestParam String brand,
                           @RequestParam String model,
                           @RequestParam String category,
                           @RequestParam String stateNumber,
                           @RequestParam String yearOfRelease,
                           Pageable pageable,
                           Model models) {
        log.info("Search car : {},{},{},{},{};",
                brand, model, category, stateNumber, yearOfRelease);
        int year;
        if (yearOfRelease.isEmpty()) {
            year = 0;
        } else {
            year = Integer.parseInt(yearOfRelease);
        }
        Page<Car> notePage = carService.findByParam(brand, model, category,
                stateNumber, year, pageable);
        PageWrapper<Car> page = new PageWrapper<Car>(notePage, "/");
        models.addAttribute("cars", page.getContent());
        models.addAttribute("sort", sortDateMethod);
        models.addAttribute("filter", filterMethod);
        models.addAttribute("page", page);

        return "index";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        CarDto car = carService.getCar(id);
        log.info("edit car with id: {};{}", id, car.getId());
        model.addAttribute("car", car);
        return "operations/edit";
    }

    @PostMapping("/update")
    public String updateCar(@RequestParam Long id,
                            @RequestParam String brand,
                            @RequestParam String model,
                            @RequestParam String category,
                            @RequestParam String stateNumber,
                            @RequestParam String type,
                            @RequestParam String yearOfRelease,
                            @RequestParam Boolean trailer) {

        log.info("Create car carDto: {} {},{},{},{},{},{},{};",
                id, brand, model, category, stateNumber, type, yearOfRelease, trailer);

        Car newCar = new Car(id, brand, model, category, stateNumber,
                CarType.valueOf(type), Integer.parseInt(yearOfRelease), trailer);

        carService.updateCar(newCar);
        return "redirect:/";
    }


}
