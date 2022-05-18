package ru.ulstu.is.sbapp.carstoowner.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.carstoowner.model.Car;
import ru.ulstu.is.sbapp.carstoowner.model.DopCar;
import ru.ulstu.is.sbapp.carstoowner.service.CarService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable Long id) {
        return new CarDto(carService.findCar(id));
    }

    @GetMapping("/")
    public List<CarDto> getCars() {
        return carService.findAllCars().stream()
                .map(CarDto::new)
                .toList();
    }

    @PostMapping("/")
    public CarDto createCar(@RequestBody @Valid CarDto carDto) {
        return carService.addCar(carDto);
    }

    @PatchMapping("/")
    public CarDto updateCar(@RequestBody @Valid CarDto carDto) {
        return carService.updateCar(carDto);
    }

    @DeleteMapping("/{id}")
    public CarDto deleteCar(@PathVariable Long id) {
        return new CarDto(carService.deleteCar(id));
    }

    @GetMapping("/?{price1}&{price2}")
    public List<DopCarDto> getCarsByPrice(@PathVariable float price1,
                                       @PathVariable float price2) {
        return carService.getCarsByPrice(price1, price2).stream()
                .map(DopCarDto::new)
                .toList();
    }
}