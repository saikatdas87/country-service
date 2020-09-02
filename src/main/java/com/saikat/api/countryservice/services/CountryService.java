package com.saikat.api.countryservice.services;

import com.saikat.api.countryservice.exceptions.NoPropertiesDefinedException;
import com.saikat.api.countryservice.models.Country;
import com.saikat.api.countryservice.models.CountryDetails;

import java.util.List;

public interface CountryService {
    List<Country> fetchAllCountries() throws NoPropertiesDefinedException;
    CountryDetails fetchCountryDetails(final String countryName) throws NoPropertiesDefinedException;
}
