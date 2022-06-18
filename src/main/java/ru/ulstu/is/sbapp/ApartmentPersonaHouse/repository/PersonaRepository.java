package ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
