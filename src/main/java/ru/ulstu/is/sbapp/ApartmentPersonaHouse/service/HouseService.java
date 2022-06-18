package ru.ulstu.is.sbapp.ApartmentPersonaHouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller.HouseDto;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.House;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository.HouseRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final Logger log = LoggerFactory.getLogger(HouseService.class);
    private final HouseRepository HouseRepository;
    private final ValidatorUtil validatorUtil;

    public HouseService(HouseRepository HouseRepository, ValidatorUtil validatorUtil) {
        this.HouseRepository = HouseRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public House addHouse(String firstName, String lastname) {
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastname)) {
            throw new IllegalArgumentException("House FIO is null or empty");
        }
        final House House = new House(firstName, lastname);
        validatorUtil.validate(House);
        return HouseRepository.save(House);
    }

    @Transactional(readOnly = true)
    public House findHouse(Long id) {
        final Optional<House> House = HouseRepository.findById(id);
        return House.orElseThrow(() -> new HouseNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public House findHouseByFIO(String firstName, String lastName) {
        List<House> Houses = HouseRepository.findAll();
        for (var House : Houses) {
            if (House.getFirstName().equals(firstName) && House.getLastName().equals(lastName))
            {
                return House;
            }
        }
        throw new EntityNotFoundException(String.format("House with fio [%s] [%s] is not found", firstName, lastName));
    }

    @Transactional(readOnly = true)
    public List<House> findAllHouses() {
        return HouseRepository.findAll();
    }

    @Transactional
    public House updateHouse(Long id, String firstName, String lastName) {
        if(!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("House FIO is null or empty");
        }
        final House currentHouse = findHouse(id);
        currentHouse.setFirstName(firstName);
        currentHouse.setLastName(lastName);
        validatorUtil.validate(currentHouse);
        return HouseRepository.save(currentHouse);
    }

    @Transactional
    public HouseDto updateHouse(HouseDto HouseDto) {
        return new HouseDto(updateHouse(HouseDto.getId(), HouseDto.getFirstName(), HouseDto.getLastName()));
    }

    @Transactional
    public House deleteHouse(Long id) {
        House currentHouse = findHouse(id);
        HouseRepository.delete(currentHouse);
        return currentHouse;
    }

    @Transactional
    public void deleteAllHousesUnS() {
        log.warn("Unsafe usage!");
        List<House> Houses = findAllHouses();
        for(var House : Houses){
            if(House.getApartments().size() > 0)
                House.removeAllApartments();
        }
        HouseRepository.deleteAll();
    }

    @Transactional
    public void deleteAllHouses() throws InHouseFoundApartmentsException {
        List<House> Houses = findAllHouses();
        for(var House : Houses){
            if(House.getApartments().size() > 0)
                throw new InHouseFoundApartmentsException("У " + House.getFirstName() + " " + House.getLastName() + " есть автомобили");
        }
        HouseRepository.deleteAll();
    }
}
