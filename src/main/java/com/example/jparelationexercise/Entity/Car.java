package com.example.jparelationexercise.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int carId;
    private String regNr;
    private String  brand;
    private String model;
    private LocalDate regDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "owner_id")
    private  AppUser owner;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "cars")
    Collection<Status> statusCodes;

    public Car() {
    }

    public Car(String regNr, String brand, String model, LocalDate regDate) {
        this.regNr = regNr;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
    }

    public int getCarId() {
        return carId;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && regNr.equals(car.regNr) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(regDate, car.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, regNr, brand, model, regDate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", regNr='" + regNr + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regDate=" + regDate +
                ", owner=" + owner +
                '}';
    }
}
