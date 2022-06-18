package ru.ulstu.is.sbapp.ApartmentPersonaHouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
//Многие-к-одному к Persona
//Много автомобилей в одном СТО
//Многие-к-одному к House
//Один владелец может иметь несколько автомобилей
@Entity
public class Apartment {
    @Id
    @SequenceGenerator(name = "Apartment_seq",
    sequenceName = "Apartment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Apartment_seq")
    private Long id;
    @NotBlank (message="Apartment model can't be null or empty")
    private String model;
    private float price;

    @ManyToOne()
    @JoinColumn(name = "House_fk")
    private House House;

    @ManyToOne()
    @JoinColumn(name = "Persona_fk")
    private Persona Persona;

    public Apartment(){
    }

    public Apartment(String model, float price){
        this.model = model;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public float getPrice() {
        return price;
    }

    public House getHouse(){ return House; }

    public Persona getPersona(){ return Persona; }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setHouse(House House){
        this.House = House;
        if(!House.getApartments().contains(this)){
            House.setApartment(this);
        }
    }

    public void setPersona(Persona Persona){
        this.Persona = Persona;
        if(!Persona.getApartments().contains(this)){
            Persona.setApartment(this);
        }
    }

    public void removeHouse() {
        if(House.removeApartment(getId()) != null)
        {
            House.removeApartment(getId());
        }
        House = null;
    }

    public void removePersona() {
        if(Persona.removeApartment(getId()) != null)
        {
            Persona.removeApartment(getId());
        }
        Persona = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment Apartment = (Apartment) o;
        return Objects.equals(id, Apartment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return model + "$" + price;
    }
}
