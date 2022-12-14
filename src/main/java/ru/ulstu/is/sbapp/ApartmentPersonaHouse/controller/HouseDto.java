package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseDto {
    private long id;
    private String street;
    private Integer number;
    private List<Long> apartments;

    public HouseDto() {}
    public HouseDto(House House){
        this.id = House.getId();
        this.street = House.getStreet();
        this.number = House.getNumber();
        apartments = new ArrayList<>();
        if (House.getApartments() != null) {
            for (var Apartment : House.getApartments()) {
                apartments.add(Apartment.getId());
            }
        }
    }

    public long getId() { return id; }

    public String getStreet() { return street; }

    public Integer getNumber() { return number; }

    public List<Long> getApartments() { return apartments; }
}
