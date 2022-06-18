package ru.ulstu.is.sbapp.ApartmentPersonaHouse.service;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(Long id) {
        super(String.format("Persona with id [%s] is not found", id));
    }
}
