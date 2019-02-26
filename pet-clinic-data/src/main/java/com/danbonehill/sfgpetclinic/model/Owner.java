package com.danbonehill.sfgpetclinic.model;

import java.util.Set;

public class Owner extends Person {

    private String address;
    private String city;
    private String telephony;
    private Set<Pet> pets;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephony() {
        return telephony;
    }

    public void setTelephony(String telephony) {
        this.telephony = telephony;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
