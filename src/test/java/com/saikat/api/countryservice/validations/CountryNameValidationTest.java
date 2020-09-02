package com.saikat.api.countryservice.validations;

import com.saikat.api.countryservice.exceptions.InvalidDataException;
import com.saikat.api.countryservice.validators.CountryNameValidation;
import com.saikat.api.countryservice.validators.CountryNameValidationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CountryNameValidationTest {

    private final CountryNameValidation validator = new CountryNameValidationImpl();

    @Test
    public void testSuccess() {
        try {
            validator.validateCountryName("India");
        } catch (Exception e) {
            fail("Should not have reached");
        }
    }

    @Test
    public void failsIfEmptyCity() {
        try {
            validator.validateCountryName("");
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidDataException);
            assertEquals(e.getMessage(), "country name can not be empty");
        }
    }

    @Test
    public void shouldFailForSpaces() {
        try {
            validator.validateCountryName(" ");
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidDataException);
            assertEquals(e.getMessage(), "country name can not be empty");
        }
    }

    @Test
    public void shouldFailInvalidChars() {
        try {
            validator.validateCountryName(">>China>>");
            fail("Should have thrown exception");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidDataException);
            assertEquals(e.getMessage(), "Invalid country name provided : >>China>>");
        }
    }
}
