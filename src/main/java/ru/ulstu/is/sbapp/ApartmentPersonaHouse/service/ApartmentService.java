package ru.ulstu.is.sbapp.ApartmentPersonaHouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller.ApartmentDto;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Apartment;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository.ApartmentRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    private final ApartmentRepository ApartmentRepository;
    private final ValidatorUtil validatorUtil;
    private final HouseService HouseService;
    private final PersonaService PersonaService;

    public ApartmentService(ApartmentRepository ApartmentRepository, ValidatorUtil validatorUtil, HouseService HouseService,
                      PersonaService PersonaService) {
        this.ApartmentRepository = ApartmentRepository;
        this.validatorUtil = validatorUtil;
        this.PersonaService = PersonaService;
        this.HouseService = HouseService;
    }

    @Transactional
    public Apartment addApartment(Integer floor, Integer number, long HouseId, long PersonaId) {
        if(!StringUtils.hasText(floor.toString()) || HouseId == 0 || PersonaId == 0) {
            throw new IllegalArgumentException("Apartment data is null or empty");
        }
        var House = HouseService.findHouse(HouseId);
        var Persona = PersonaService.findPersona(PersonaId);
        var Apartment = new Apartment(floor, number);
        Apartment.setHouse(House);
        Apartment.setPersona(Persona);
        validatorUtil.validate(Apartment);
        return ApartmentRepository.save(Apartment);
    }

    @Transactional
    public ApartmentDto addApartment(ApartmentDto ApartmentDto) {
        return new ApartmentDto(addApartment(ApartmentDto.getFloor(), ApartmentDto.getNumber(), ApartmentDto.getHouse(), ApartmentDto.getPersona()));
    }


    @Transactional(readOnly = true)
    public Apartment findApartment(Long id) {
        final Optional<Apartment> Apartment = ApartmentRepository.findById(id);
        return Apartment.orElseThrow(() -> new ApartmentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Apartment> findAllApartments() {
        return ApartmentRepository.findAll();
    }

    @Transactional
    public Apartment updateApartment(long id, Integer floor, Integer number, long HouseId, long PersonaId) {
        if(!StringUtils.hasText(floor.toString())) {
            throw new IllegalArgumentException("Apartment data is null or empty");
        }
        final Apartment currentApartment = findApartment(id);
        var House = HouseService.findHouse(HouseId);
        var Persona = PersonaService.findPersona(PersonaId);
        currentApartment.setFloor(floor);
        currentApartment.setNumber(number);
        if (currentApartment.getHouse().getId().equals(HouseId)) {
            currentApartment.getHouse().updateApartment(id, currentApartment);
        }
        else {
            currentApartment.getHouse().removeApartment(id);
            currentApartment.setHouse(House);
        }

        if (currentApartment.getPersona().getId().equals(PersonaId)) {
            currentApartment.getPersona().updateApartment(id, currentApartment);
        }
        else {
            currentApartment.getPersona().removeApartment(id);
            currentApartment.setPersona(Persona);
        }
        validatorUtil.validate(currentApartment);
        return ApartmentRepository.save(currentApartment);
    }

    @Transactional
    public ApartmentDto updateApartment(ApartmentDto ApartmentDto) {
        return new ApartmentDto(updateApartment(ApartmentDto.getId(), ApartmentDto.getFloor(), ApartmentDto.getNumber(), ApartmentDto.getHouse(), ApartmentDto.getPersona()));
    }

    @Transactional
    public Apartment deleteApartment(Long id) {
        final Apartment currentApartment = findApartment(id);
        ApartmentRepository.delete(currentApartment);
        return currentApartment;
    }

    @Transactional
    public void deleteAllApartments() {
        ApartmentRepository.deleteAll();
    }

}
