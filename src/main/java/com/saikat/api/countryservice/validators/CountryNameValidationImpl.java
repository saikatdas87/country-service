package com.saikat.api.countryservice.validators;

import com.saikat.api.countryservice.exceptions.InvalidDataException;
import org.springframework.stereotype.Service;

@Service
public class CountryNameValidationImpl implements CountryNameValidation {

    /**
     * Validates if country name provided is a valid one
     *
     * @param country name of the country to be validated
     */
    @Override
    public void validateCountryName(String country) {
        if (country.trim().equals("")) {
            throw new InvalidDataException("country name can not be empty");
        }

        final String countryRegex = "^([A-Z][a-z]*)+(?:[\\s-][A-Z][a-z]*)*$";
        if (!country.matches(countryRegex)) {
            throw new InvalidDataException("Invalid country name provided : " + country);
        }
    }
}
