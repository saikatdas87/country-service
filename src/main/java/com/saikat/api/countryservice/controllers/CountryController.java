package com.saikat.api.countryservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {

    @GetMapping("/countries")
    public String getAllCountries() {
        return null;
    }

    @GetMapping("/countries/{countryName}")
    public String getNodeById(@PathVariable Optional<String> countryName) {
        return null;
    }

}
