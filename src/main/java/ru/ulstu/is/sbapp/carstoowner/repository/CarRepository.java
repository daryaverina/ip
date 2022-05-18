package ru.ulstu.is.sbapp.carstoowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ulstu.is.sbapp.carstoowner.model.Car;
import ru.ulstu.is.sbapp.carstoowner.model.DopCar;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    //Добавить метод получения машин в определенной стоимости (прим. от 150к до 300к)
    //информация : модель, владелец, сто

    @Query(value = "select c.model, o.firstName, o.lastName, s.name from Car c inner join Owner o on c.owner.id = o.id inner join STO s on c.sto.id = s.id where c.price >= :price1 and c.price <= :price2")
    Optional<Object> takeByPrice(@Param("price1") int price1,
                                 @Param("price2") int price2);
}
