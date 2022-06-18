package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

import ru.ulstu.is.sbapp.ApartmentPersonaHouse.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaDto {
    private long id;
    private String name;
    private List<Long> apartments;
    public PersonaDto() {}
    public PersonaDto(Persona Persona){
        this.id = Persona.getId();
        this.name = Persona.getName();
        apartments = new ArrayList<>();
        if (Persona.getApartments() != null) {
            for (var Apartment : Persona.getApartments()) {
                apartments.add(Apartment.getId());
            }
        }
    }



    public long getId() { return id; }

    public String getName() { return name; }

    public List<Long> getApartments() { return apartments; }

}
