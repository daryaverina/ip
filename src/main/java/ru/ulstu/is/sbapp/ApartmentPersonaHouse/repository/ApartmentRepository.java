package ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}