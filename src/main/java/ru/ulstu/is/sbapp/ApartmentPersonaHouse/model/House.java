package ru.ulstu.is.sbapp.ApartmentPersonaHouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//Один-ко-многим к автомобилям
//Один владелец может иметь несколько машин
@Entity
public class House {
    @Id
    @SequenceGenerator(name = "House_seq",
            sequenceName = "House_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "House_seq")
    private Long id;
    @NotBlank(message="House first name can't be null or empty")
    private String firstName;
    @NotBlank(message="House last name can't be null or empty")
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "House_fk")
    private List<Apartment> Apartments = new ArrayList<>();

    public House(){ }

    public House(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public House(String firstName, String lastName, List<Apartment> Apartments){
        this.firstName = firstName;
        this.lastName = lastName;
        this.Apartments = Apartments;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){ return lastName; }

    public List<Apartment> getApartments(){
        return Apartments;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return firstName + "$" + lastName;
    }
}
