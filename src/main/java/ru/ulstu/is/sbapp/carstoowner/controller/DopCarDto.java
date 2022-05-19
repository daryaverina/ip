package ru.ulstu.is.sbapp.carstoowner.controller;

public class DopCarDto {
    private String model;
    private String owner_firstname;
    private String owner_lastname;
    private String sto_name;

    public DopCarDto() { }
    public DopCarDto(String model, String owner_firstname, String owner_lastname, String sto_name) {
        this.model = model;
        this.owner_firstname = owner_firstname;
        this.owner_lastname = owner_lastname;
        this.sto_name = sto_name;
    }

    public String getModel() { return model; }

    public String getSto_name() { return sto_name; }

    public String getOwner_firstname() { return owner_firstname; }

    public String getOwner_lastname() {
        return owner_lastname;
    }

    public void setModel(String model) { this.model = model; }

    public void setSto_name(String sto_name) { this.sto_name = sto_name; }

    public void setOwner_firstname(String owner_firstname) { this.owner_firstname = owner_firstname; }

    public void setOwner_lastname(String owner_lastname) {
        this.owner_lastname = owner_lastname;
    }
}
