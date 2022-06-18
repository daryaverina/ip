package ru.ulstu.is.sbapp.ApartmentPersonaHouse.controller;

public class DopApartmentDto {
    private String model;
    private String House_firstname;
    private String House_lastname;
    private String Persona_name;

    public DopApartmentDto() { }
    public DopApartmentDto(String model, String House_firstname, String House_lastname, String Persona_name) {
        this.model = model;
        this.House_firstname = House_firstname;
        this.House_lastname = House_lastname;
        this.Persona_name = Persona_name;
    }

    public String getModel() { return model; }

    public String getPersona_name() { return Persona_name; }

    public String getHouse_firstname() { return House_firstname; }

    public String getHouse_lastname() {
        return House_lastname;
    }

    public void setModel(String model) { this.model = model; }

    public void setPersona_name(String Persona_name) { this.Persona_name = Persona_name; }

    public void setHouse_firstname(String House_firstname) { this.House_firstname = House_firstname; }

    public void setHouse_lastname(String House_lastname) {
        this.House_lastname = House_lastname;
    }
}
