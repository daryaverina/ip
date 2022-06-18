package ru.ulstu.is.sbapp.ApartmentPersonaHouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pers_seq")
    @SequenceGenerator(name="pers_seq", sequenceName = "PERS_SEQUENCE", allocationSize=1)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Persona_fk")
    private List<Apartment> Apartments = new ArrayList<>();

    public Persona() {
    }

    public Persona(String name) {
        this.name = name;
    }

    public Persona(String name, List<Apartment> Apartments){
        this.name = name;
        this.Apartments = Apartments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Apartment> getApartments(){
        return Apartments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApartment(Apartment Apartment){
        if(!Apartments.contains(Apartment))
        {
            Apartments.add(Apartment);
            if(Apartment.getPersona() != this)
            {
                Apartment.setPersona(this);
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

    public void updateApartment(Long id, Apartment c) {
        for (var Apartment : Apartments) {
            if(Objects.equals(Apartment.getId(), c.getId())) {
                Apartment = c;
                return;
            }
        }
    }

    public void removeAllApartments() {
        Apartments.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona Persona = (Persona) o;
        return Objects.equals(id, Persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return name;
    }
}
