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
    private String street;
    private String city;
    private String house;
    private String room;

    public HomeAddress(String city, String street, String house, String room) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.room = room;
    }

    @Override
    String getFormattedInfo() {
        return city + ", " + street + ", " + house + ", " + room;
    }
}