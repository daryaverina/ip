package ru.ulstu.is.sbapp.ApartmentPersonaHouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apart_seq")
    @SequenceGenerator(name="apart_seq", sequenceName = "APART_SEQUENCE", allocationSize=1)
    private Long id;
    private Integer floor;
    private Integer number;

    @ManyToOne()
    @JoinColumn(name = "House_fk")
    private House House;

    @ManyToOne()
    @JoinColumn(name = "Persona_fk")
    private Persona Persona;

    public Apartment(){
    }

    public Apartment(Integer floor, Integer number){
        this.floor = floor;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public Integer getFloor() {
        return floor;
    }

    public Integer getNumber() {
        return number;
    }

    public House getHouse(){ return House; }

    public Persona getPersona(){ return Persona; }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        return floor + "$" + number;
    }
}
