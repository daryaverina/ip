package ru.ulstu.is.sbapp.ApartmentPersonaHouse.service;

public class HouseNotFoundException extends RuntimeException{
    public HouseNotFoundException(Long id) {
        super(String.format("House with is [%s] is not found", id));
    }
}
