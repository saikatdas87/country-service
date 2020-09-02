package com.saikat.api.countryservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CountryDetails extends Country {
    private String capital;
    private Integer population;
    private String flagFileUrl;

    public String getCapital() {
        return capital;
    }

    @JsonSetter("capital")
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getPopulation() {
        return population;
    }

    @JsonSetter("population")
    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getFlagFileUrl() {
        return flagFileUrl;
    }

    public void setFlagFileUrl(String flagFileUrl) {
        this.flagFileUrl = flagFileUrl;
    }

    @JsonProperty("flag")
    public void setFlagFilePath(String path) {
        this.setFlagFileUrl(path);
    }
}
