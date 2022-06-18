package ru.ulstu.is.sbapp.ApartmentPersonaHouse.service;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException(Long id) {
        super(String.format("Apartment with id [%s] is not found", id));
    }
}
