package com.saikat.api.countryservice.validators;

import com.saikat.api.countryservice.exceptions.InvalidDataException;
import org.springframework.stereotype.Service;

@Service
public class CountryNameValidationImpl implements CountryNameValidation {

    private final String COUNTRY_REGEX = "^([A-Z][a-z]*)+(?:[\\s-][A-Z][a-z]*)*$";

    @Override
    public void validateCountryName(String country) {
        if (country.trim().equals("")) {
            throw new InvalidDataException("country name can not be empty");
        }
        final Boolean validName = country.matches(COUNTRY_REGEX);

        if (!validName) {
            throw new InvalidDataException("Invalid country name provided : " + country);
        }
    }


}
