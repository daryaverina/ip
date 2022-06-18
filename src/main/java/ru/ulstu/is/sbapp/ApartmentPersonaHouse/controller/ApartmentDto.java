package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Apartment;

public class ApartmentDto {
    private long id;
    private Integer floor;
    private Integer number;
    private long House;
    private long Persona;

    public ApartmentDto() { }

    public ApartmentDto(Apartment Apartment) {
        this.id = Apartment.getId();
        this.floor = Apartment.getFloor();
        this.number = Apartment.getNumber();
        if (Apartment.getHouse() != null) {
            House = Apartment.getHouse().getId();
        }
        if (Apartment.getPersona() != null) {
            Persona = Apartment.getPersona().getId();
        }
    }

    public long getId() { return id; }

    public Integer getFloor() { return floor; }

    public Integer getNumber() { return number; }

    public long getPersona(){ return Persona; }

    public long getHouse() { return House; }
}
