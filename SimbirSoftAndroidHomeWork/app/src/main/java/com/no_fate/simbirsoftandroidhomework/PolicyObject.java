package com.no_fate.simbirsoftandroidhomework;

/**
 * Created by Denis on 16.08.2017.
 */

abstract class PolicyObject {
    abstract String getFormattedInfo();
}

class FIO extends PolicyObject{
    private String lastName;
    private String name;
    private String secondaryName;

    public FIO(String lastName, String name, String secondaryName) {
        this.lastName = lastName;
        this.name = name;
        this.secondaryName = secondaryName;
    }

    @Override
    String getFormattedInfo() {
        return lastName + " " + name + " " + secondaryName;
    }
}

class Automobile extends PolicyObject{
    private String brand;
    private String model;

    public Automobile(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    @Override
    String getFormattedInfo() {
        return brand + " " + model;
    }
}

class HomeAddress extends PolicyObject{
    private String country;
    private String city;
    private String house;
    private String room;

    public HomeAddress(String country, String city, String house, String room) {
        this.country = country;
        this.city = city;
        this.house = house;
        this.room = room;
    }

    @Override
    String getFormattedInfo() {
        return country + " " + city + " " + house + " " + room;
    }
}