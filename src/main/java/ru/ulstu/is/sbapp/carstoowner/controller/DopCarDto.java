package ru.ulstu.is.sbapp.carstoowner.controller;

import ru.ulstu.is.sbapp.carstoowner.model.Car;
import ru.ulstu.is.sbapp.carstoowner.model.DopCar;

public class DopCarDto {
    private String model;
    private String owner_firstname;
    private String owner_lastname;
    private String sto_name;

    public DopCarDto() { }

    public DopCarDto(DopCar car) {
        this.model = car.getModel();
        this.owner_firstname = car.getOwner_first_name();
        this.owner_lastname = car.getOwner_last_name();
        this.sto_name = car.getSto_name();
    }


    public String getModel() { return model; }

    public String getSto_name() { return sto_name; }

    public String getOwner_firstname() { return owner_firstname; }

    public String getOwner_lastname() {
        return owner_lastname;
    }
}
