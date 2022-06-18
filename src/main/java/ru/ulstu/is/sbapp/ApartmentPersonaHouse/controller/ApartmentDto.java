package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Apartment;

public class ApartmentDto {
    private long id;
    private String model;
    private float price;
    private long House;
    private long Persona;

    public ApartmentDto() { }

    public ApartmentDto(Apartment Apartment) {
        this.id = Apartment.getId();
        this.model = Apartment.getModel();
        this.price = Apartment.getPrice();
        if (Apartment.getHouse() != null) {
            House = Apartment.getHouse().getId();
        }
        if (Apartment.getPersona() != null) {
            Persona = Apartment.getPersona().getId();
        }
    }

    public long getId() { return id; }

    public String getModel() { return model; }

    public float getPrice() { return price; }

    public long getPersona(){ return Persona; }

    public long getHouse() { return House; }
}
