package com.saikat.api.countryservice.controllers;

import com.saikat.api.countryservice.exceptions.NoPropertiesDefinedException;
import com.saikat.api.countryservice.models.Country;
import com.saikat.api.countryservice.models.CountryDetails;
import com.saikat.api.countryservice.services.CountryService;
import com.saikat.api.countryservice.validators.CountryNameValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {

    private Logger logger = LoggerFactory.getLogger(CountryController.class);
    private final CountryService countryService;
    private final CountryNameValidation countryNameValidation;

    @Autowired
    public CountryController(CountryService countryService, CountryNameValidation countryNameValidation) {
        this.countryService = countryService;
        this.countryNameValidation = countryNameValidation;
    }

    /**
     * The API GET method for fetching all countries
     *
     * @return List<Country> a list of Country objects
     * @throws NoPropertiesDefinedException
     */
    @GetMapping("/countries")
    public List<Country> getAllCountries() throws NoPropertiesDefinedException {
        logger.debug("fetching all countries");
        return countryService.fetchAllCountries();
    }

    /**
     * The API GET method for fetching some detailed info for a country
     *
     * @param countryName name of the country
     * @return CountryDetails containing some details of the country
     * @throws NoPropertiesDefinedException
     */
    @GetMapping("/countries/{countryName}")
    public CountryDetails getNodeById(@PathVariable @NotNull Optional<String> countryName) throws NoPropertiesDefinedException {
        final String country = countryName.orElse("").trim();
        countryNameValidation.validateCountryName(country);
        return countryService.fetchCountryDetails(country);
    }

}
