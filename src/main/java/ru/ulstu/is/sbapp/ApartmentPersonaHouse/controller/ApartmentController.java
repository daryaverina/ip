package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.service.ApartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Apartment")
public class ApartmentController {
    private final ApartmentService ApartmentService;

    public ApartmentController(ApartmentService ApartmentService) {
        this.ApartmentService = ApartmentService;
    }

    @GetMapping("/{id}")
    public ApartmentDto getApartment(@PathVariable Long id) {
        return new ApartmentDto(ApartmentService.findApartment(id));
    }

    @GetMapping("/")
    public List<ApartmentDto> getApartments() {
        return ApartmentService.findAllApartments().stream()
                .map(ApartmentDto::new)
                .toList();
    }

    //@PostMapping("/")
    //public ApartmentDto createApartment(@RequestBody @Valid ApartmentDto ApartmentDto) {
      //  return ApartmentService.addApartment(ApartmentDto);
    //}

    @PostMapping("/")
    public ApartmentDto createApartment(@RequestParam("floor") Integer floor,
                                        @RequestParam("number") Integer number,
                                        @RequestParam("house id")Integer house,
                                        @RequestParam("person id")Integer person) {
        return new ApartmentDto(ApartmentService.addApartment(floor, number, house, person));
    }

    @PatchMapping("/")
    public ApartmentDto updateApartment(@RequestBody @Valid ApartmentDto ApartmentDto) {
        return ApartmentService.updateApartment(ApartmentDto);
    }

    @DeleteMapping("/{id}")
    public ApartmentDto deleteApartment(@PathVariable Long id) {
        return new ApartmentDto(ApartmentService.deleteApartment(id));
    }

}