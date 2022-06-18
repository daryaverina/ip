package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseDto {
    private long id;
    private String first_name;
    private String last_name;
    private List<Long> Apartments;

    public HouseDto() {}
    public HouseDto(House House){
        this.id = House.getId();
        this.first_name = House.getFirstName();
        this.last_name = House.getLastName();
        Apartments = new ArrayList<>();
        if (House.getApartments() != null) {
            for (var Apartment : House.getApartments()) {
                Apartments.add(Apartment.getId());
            }
        }
    }

    public long getId() { return id; }

    public String getFirstName() { return first_name; }

    public String getLastName() { return last_name; }

    public List<Long> getApartments() { return Apartments; }
}
