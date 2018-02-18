package com.calidad2018.pcc.utils;

public class Country {

    private String iso3;
    private String iso2;
    private String name;


    public Country(String iso3, String name) {
        this.iso3 = iso3;
        this.name = name;
    }

    public Country(String iso3, String iso2, String name) {
        this.iso3 = iso3;
        this.iso2 = iso2;
        this.name = name;
    }

    public Country() {
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
