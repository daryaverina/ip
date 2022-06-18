package ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}
