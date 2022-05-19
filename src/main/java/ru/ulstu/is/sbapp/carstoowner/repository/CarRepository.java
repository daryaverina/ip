package ru.ulstu.is.sbapp.carstoowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ulstu.is.sbapp.carstoowner.controller.DopCarDto;
import ru.ulstu.is.sbapp.carstoowner.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    //Добавить метод получения машин в определенной стоимости (прим. от 150к до 300к)
    //информация : модель, владелец, сто

    @Query(value = "select new ru.ulstu.is.sbapp.carstoowner.controller.DopCarDto(c.model, o.firstName, o.lastName, s.name) from Car c inner join Owner o on c.owner.id = o.id inner join STO s on c.sto.id = s.id where c.price >= :price1 and c.price <= :price2")
    List<DopCarDto> takeByPrice(@Param("price1") float price1,
                                @Param("price2") float price2);
}