package ru.ulstu.is.sbapp.ApartmentPersonaHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller.DopApartmentDto;
import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Apartment;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    //Добавить метод получения машин в определенной стоимости (прим. от 150к до 300к)
    //информация : модель, владелец, сто

    @Query(value = "select new ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller.DopApartmentDto(c.model, o.firstName, o.lastName, s.name) from Apartment c inner join House o on c.House.id = o.id inner join Persona s on c.Persona.id = s.id where c.price >= :price1 and c.price <= :price2")
    List<DopApartmentDto> takeByPrice(@Param("price1") float price1,
                                @Param("price2") float price2);
}