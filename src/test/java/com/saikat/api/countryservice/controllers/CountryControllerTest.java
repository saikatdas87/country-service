package com.saikat.api.countryservice.controllers;

import com.saikat.api.countryservice.exceptions.InvalidDataException;
import com.saikat.api.countryservice.exceptions.ResourceNotFoundException;
import com.saikat.api.countryservice.models.Country;
import com.saikat.api.countryservice.models.CountryDetails;
import com.saikat.api.countryservice.services.CountryService;
import com.saikat.api.countryservice.validators.CountryNameValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CountryController.class)
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @MockBean
    private CountryNameValidation validator;

    @Test
    public void whenExceptionFetchingCountries() throws Exception {
        when(countryService.fetchAllCountries()).thenThrow(new ResourceNotFoundException("No countries found"));
        mockMvc.perform(get("/countries")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenFetchesCountries() throws Exception {
        Country india = new Country();
        india.setName("India");
        india.setCountryCode("IN");
        when(countryService.fetchAllCountries()).thenReturn(new ArrayList<Country>() {
            {
                add(india);
            }
        });
        MvcResult mvcResult = mockMvc.perform(get("/countries/")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertTrue(actualResponseBody.contains("India"));
        assertTrue(actualResponseBody.contains("IN"));
    }

    @Test
    public void whenEmptyInputForDetails() throws Exception {
        doThrow(new InvalidDataException("Empty")).when(validator).validateCountryName("");
        mockMvc.perform(get("/countries/ ")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenNoDetailsFound() throws Exception {
        doNothing().when(validator).validateCountryName("London");
        when(countryService.fetchCountryDetails("London")).thenThrow(new ResourceNotFoundException("No country"));
        mockMvc.perform(get("/countries/London")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenValidInputForFetchingDetails() throws Exception {
        doNothing().when(validator).validateCountryName("India");
        CountryDetails details = new CountryDetails();
        details.setName("India");
        details.setCapital("New Delhi");
        when(countryService.fetchCountryDetails("India")).thenReturn(details);
        MvcResult mvcResult = mockMvc.perform(get("/countries/India")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertTrue(actualResponseBody.contains("India"));
        assertTrue(actualResponseBody.contains("New Delhi"));
    }
}
