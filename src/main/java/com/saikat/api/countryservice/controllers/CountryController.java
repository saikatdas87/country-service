package com.saikat.api.countryservice.controllers;

import com.saikat.api.countryservice.exceptions.NoPropertiesDefinedException;
import com.saikat.api.countryservice.models.Country;
import com.saikat.api.countryservice.services.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {

    Logger logger = LoggerFactory.getLogger(CountryController.class);
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping("/countries")
    public List<Country> getAllCountries() throws NoPropertiesDefinedException {

        logger.debug("fetching all countries");
        return countryService.fetchAllCountries();
    }

    @GetMapping("/countries/{countryName}")
    public String getNodeById(@PathVariable Optional<String> countryName) {
        return null;
    }

}
