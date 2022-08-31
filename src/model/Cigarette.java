package model;

import java.io.Serializable;

public class Cigarette implements Serializable {
    private int id;
    private String name;
    private Country country;
    private Company company;
    private double price;

    public Cigarette() {
    }

    public Cigarette(int id, String name, Country country, Company company, double price) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.company = company;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cigarette   { " +
                " id = " + id +
                ",         name = " + name + '\'' +"        "+"country = "+
                 country.getName() + "        "+"company = "+
                 company.getName() +
                ",         price = " + price +
                " }";
    }
}
