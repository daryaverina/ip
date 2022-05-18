package ru.ulstu.is.sbapp.carstoowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ulstu.is.sbapp.carstoowner.model.Car;
import ru.ulstu.is.sbapp.carstoowner.model.DopCar;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    //Добавить метод получения машин в определенной стоимости (прим. от 150к до 300к)
    //информация : модель, владелец, сто

    @Query(value = "SELECT NEW ru.ulstu.is.sbapp.carstoowner.model.DopCar(c.model, o.firstName, o.lastName, s.name) " +
            "FROM Car c JOIN Owner o ON c.owner = o.id JOIN STO s ON c.sto = s.id WHERE c.price >= :price1 and c.price <= :price2")
    List<DopCar> takeCarsByPrice(@Param("price1") int price1,
                                 @Param("price2") int price2);
}
