package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.service.PersonaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Persona")
public class PersonaController {
    private final PersonaService PersonaService;

    public PersonaController(PersonaService PersonaService) {
        this.PersonaService = PersonaService;
    }

    @GetMapping("/{id}")
    public PersonaDto getPersona(@PathVariable Long id){
        return new PersonaDto(PersonaService.findPersona(id));
    }

    @GetMapping("/")
    public List<PersonaDto> getPersonas(){
        return PersonaService.findAllPersonas().stream()
                .map(PersonaDto::new)
                .toList();
    }

    @PostMapping("/")
    public PersonaDto createPersona(@RequestParam("name") String name) {
        return new PersonaDto(PersonaService.addPersona(name));
    }

    @PatchMapping("/{id}")
    public PersonaDto updatePersona(@RequestBody @Valid PersonaDto PersonaDto){
        return PersonaService.updatePersona(PersonaDto);
    }

    @DeleteMapping("/{id}")
    public PersonaDto deletePersona(@PathVariable Long id) {
        return new PersonaDto(PersonaService.deletePersona(id));
    }
}
