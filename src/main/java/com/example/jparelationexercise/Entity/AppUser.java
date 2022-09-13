package com.example.jparelationexercise.Entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  email;
    private String name;
    private String passWord;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressID")
    private Address address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    Collection<Car> ownedCars;

    protected AppUser() {
    }
    public AppUser(String email, String name, String passWord, Address adress) {
        this.email = email;
        this.name = name;
        this.passWord = passWord;
        this.address = adress;
    }
    public void addCar(Car car) {
        ownedCars.add(car);
        car.setOwner(this);
    }
    public void removeCar(Car car) {
        car.setOwner(null);
        ownedCars.remove(car);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Car> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(Collection<Car> ownedCars) {
        this.ownedCars = ownedCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(email, appUser.email) && Objects.equals(name, appUser.name) && Objects.equals(passWord, appUser.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, passWord);

    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", address=" + address +
                '}';
    }
}
