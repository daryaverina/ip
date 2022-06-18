package ru.ulstu.is.sbapp.ApartmentPersonaHouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller.PersonaDto;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Persona;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository.PersonaRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    private final Logger log = LoggerFactory.getLogger(PersonaService.class);
    private final PersonaRepository PersonaRepository;
    private final ValidatorUtil validatorUtil;

    public PersonaService(PersonaRepository PersonaRepository, ValidatorUtil validatorUtil) {
        this.PersonaRepository = PersonaRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Persona addPersona(String name) {
        if(!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Persona name is null or empty");
        }
        final Persona Persona = new Persona(name);
        validatorUtil.validate(Persona);
        return PersonaRepository.save(Persona);
    }

    @Transactional(readOnly = true)
    public Persona findPersona(Long id) {
        final Optional<Persona> Persona = PersonaRepository.findById(id);
        return Persona.orElseThrow(() -> new PersonaNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Persona findPersonaByName(String name) {
        var Personas = PersonaRepository.findAll();
        for (var Persona : Personas)
        {
            if (Persona.getName().equals(name)) {
                return Persona;
            }
        }
        throw new EntityNotFoundException(String.format("Persona with name [%s] is not found", name));
    }

    @Transactional(readOnly = true)
    public List<Persona> findAllPersonas() {
        return PersonaRepository.findAll();
    }

    @Transactional
    public Persona updatePersona(Long id, String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Persona name is null or empty");
        }
        final Persona currentPersona = findPersona(id);
        currentPersona.setName(name);
        validatorUtil.validate(currentPersona);
        return PersonaRepository.save(currentPersona);
    }

    @Transactional
    public PersonaDto updatePersona(PersonaDto PersonaDto) {
        return new PersonaDto(updatePersona(PersonaDto.getId(), PersonaDto.getName()));
    }

    @Transactional
    public Persona deletePersona(Long id) {
        final Persona currentPersona = findPersona(id);
        PersonaRepository.delete(currentPersona);
        return currentPersona;
    }

    @Transactional
    public void deleteAllPersonasUnsafe() {
        log.warn("Unsafe usage!");
        List<Persona> Personas = findAllPersonas();
        for(var Persona : Personas){
            if(Persona.getApartments().size() > 0)
                Persona.removeAllApartments();
        }
        PersonaRepository.deleteAll();
    }

    @Transactional
    public void deleteAllPersonas() throws InPersonafoundApartmentsException{
        var Personas = findAllPersonas();
        for (var Persona : Personas) {
            if (Persona.getApartments().size() > 0) {
                throw new InPersonafoundApartmentsException("В сервисе " + Persona.getName() + " есть автомобили");
            }
        }
        PersonaRepository.deleteAll();
    }
}
