package ru.ulstu.is.sbapp.ApartmentPersonaHouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_seq")
    @SequenceGenerator(name="house_seq", sequenceName = "HOUSE_SEQUENCE", allocationSize=1)
    private Long id;
    private String street;
    private Integer number;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "House_fk")
    private List<Apartment> Apartments = new ArrayList<>();

    public House(){ }

    public House(String street, Integer number){
        this.street = street;
        this.number = number;
    }

    public House(String street, Integer number, List<Apartment> Apartments){
        this.street = street;
        this.number = number;
        this.Apartments = Apartments;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber(){ return number; }

    public List<Apartment> getApartments(){
        return Apartments;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setApartment(Apartment Apartment){
        if(!Apartments.contains(Apartment))
        {
            Apartments.add(Apartment);
            if(Apartment.getHouse() != this)
            {
                Apartment.setHouse(this);
            }
        }
    }

    public Apartment removeApartment(Long ApartmentId) {
        for (var Apartment : Apartments) {
            if (Objects.equals(Apartment.getId(), ApartmentId)){
                Apartments.remove(Apartment);
                return Apartment;
            }
        }
        return null;
    }

    public void removeAllApartments() {
        Apartments.clear();
    }

    public void updateApartment(Long id, Apartment c) {
        for (var Apartment : Apartments) {
            if(Objects.equals(Apartment.getId(), c.getId())) {
                Apartment = c;
                return;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House House = (House) o;
        return Objects.equals(id, House.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return street + "$" + number;
    }
}
