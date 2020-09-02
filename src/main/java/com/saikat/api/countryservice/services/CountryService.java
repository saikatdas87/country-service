package com.saikat.api.countryservice.services;

import com.saikat.api.countryservice.exceptions.NoPropertiesDefinedException;
import com.saikat.api.countryservice.exceptions.ResourceNotFoundException;
import com.saikat.api.countryservice.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> fetchAllCountries() throws NoPropertiesDefinedException, ResourceNotFoundException;
}
