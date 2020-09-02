package com.saikat.api.countryservice.services;

import com.saikat.api.countryservice.exceptions.NoPropertiesDefinedException;
import com.saikat.api.countryservice.exceptions.ResourceNotFoundException;
import com.saikat.api.countryservice.models.Country;
import com.saikat.api.countryservice.models.CountryDetails;
import com.saikat.api.countryservice.properties.CountryApplicationProperties;
import com.saikat.api.countryservice.repos.ExternalServiceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CountryServiceTest {
    @MockBean
    private ExternalServiceRepo externalServiceRepo;

    @MockBean
    private CountryApplicationProperties properties;

    private CountryService countryService;

    @BeforeEach
    public void setUp() {
        countryService = new CountryServiceImpl(externalServiceRepo, properties);
    }

    @Test
    public void throwsExceptionIfNoAllCountryAPIConfigured() {
        when(properties.getAllCountriesApi()).thenReturn(null);
        try {
            countryService.fetchAllCountries();
            fail("Should have not reached");
        } catch (NoPropertiesDefinedException e) {
            assertEquals(e.getMessage(), "No uri defined for fetching All countries in configuration");
        }
    }

    @Test
    public void throwsExceptionIfAllCountryAPIFails() throws NoPropertiesDefinedException {
        when(properties.getAllCountriesApi()).thenReturn("https://country.api");
        when(externalServiceRepo.fetchFromExternalService(Mockito.any(),
                Mockito.eq("https://country.api")))
                .thenThrow(new RestClientException("Invalid URI"));
        try {
            countryService.fetchAllCountries();
            fail("Should have not reached");
        } catch (ResourceNotFoundException e) {
            assertEquals(e.getMessage(), "Exception fetching all countries : Invalid URI");
        }
    }

    @Test
    public void successAllCountriesResponse() {
        when(properties.getAllCountriesApi()).thenReturn("https://country.api");
        Country india = new Country();
        india.setName("India");
        india.setCountryCode("IN");
        when(externalServiceRepo.fetchFromExternalService(Mockito.any(),
                Mockito.eq("https://country.api")))
                .thenReturn(new Country[] {india});
        try {
            List<Country> res = countryService.fetchAllCountries();
            assertEquals(res.size(), 1);
            assertEquals(res.get(0).getName(), india.getName());
            assertEquals(res.get(0).getCountryCode(), india.getCountryCode());
        } catch (Exception e) {
            fail("Should have not reached");
        }
    }


    @Test
    public void throwsExceptionIfNoCountryDetailsAPIConfigured() {
        when(properties.getCountryDetailsApi()).thenReturn(null);
        try {
            countryService.fetchCountryDetails("POK");
            fail("Should have not reached");
        } catch (NoPropertiesDefinedException e) {
            assertEquals(e.getMessage(), "No uri defined for fetching country details in configuration");
        }
    }

    @Test
    public void throwsExceptionIfDetailsAPIFails() throws NoPropertiesDefinedException {
        when(properties.getCountryDetailsApi()).thenReturn("https://country.api/{name}");
        when(externalServiceRepo.fetchFromExternalService(Mockito.any(),
                Mockito.eq("https://country.api/{name}"), Mockito.eq("POK")))
                .thenThrow(new RestClientException("Invalid Country POK"));
        try {
            countryService.fetchCountryDetails("POK");
            fail("Should have not reached");
        } catch (ResourceNotFoundException e) {
            assertEquals(e.getMessage(), "Exception fetching details for POK : Invalid Country POK");
        }
    }


    @Test
    public void throwsExceptionIfEmptyDetails() throws NoPropertiesDefinedException {
        when(properties.getCountryDetailsApi()).thenReturn("https://country.api/{name}");
        when(externalServiceRepo.fetchFromExternalService(Mockito.any(),
                Mockito.eq("https://country.api/{name}"), Mockito.eq("POK")))
                .thenReturn(new CountryDetails[]{});
        try {
            countryService.fetchCountryDetails("POK");
            fail("Should have not reached");
        } catch (ResourceNotFoundException e) {
            assertEquals(e.getMessage(), "Something wrong fetching details for POK : No details could be fetched for POK");
        }
    }

    @Test
    public void successDetailsResponse() {
        when(properties.getCountryDetailsApi()).thenReturn("https://country.api/{name}");
        CountryDetails india = new CountryDetails();
        india.setName("India");
        india.setCountryCode("IN");
        india.setCapital("New Delhi");
        when(externalServiceRepo.fetchFromExternalService(Mockito.any(),
                Mockito.eq("https://country.api/{name}"), Mockito.eq("India")))
                .thenReturn(new CountryDetails[] {india});
        try {
            CountryDetails res = countryService.fetchCountryDetails("India");
            assertEquals(res.getCapital(), india.getCapital());
            assertEquals(res.getName(), india.getName());
            assertEquals(res.getCountryCode(), india.getCountryCode());
        } catch (Exception e) {
            fail("Should have not reached", e);
        }
    }
}
