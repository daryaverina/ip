package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.service.HouseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/House")
public class HouseController {
    private final HouseService HouseService;

    public HouseController(HouseService HouseService) {
        this.HouseService = HouseService;
    }

    @GetMapping("/{id}")
    public HouseDto getHouse(@PathVariable Long id) {
        return new HouseDto(HouseService.findHouse(id));
    }

    @GetMapping("/")
    public List<HouseDto> getHouses() {
        return HouseService.findAllHouses().stream()
                .map(HouseDto::new)
                .toList();
    }

    @PostMapping("/")
    public HouseDto createHouse(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        return new HouseDto(HouseService.addHouse(firstName, lastName));
    }

    @PatchMapping("/{id}")
    public HouseDto updateHouse(@RequestBody @Valid HouseDto HouseDto) {
        return HouseService.updateHouse(HouseDto);
    }

    @DeleteMapping("/{id}")
    public HouseDto deleteHouse(@PathVariable Long id) {
        return new HouseDto(HouseService.deleteHouse(id));
    }
}