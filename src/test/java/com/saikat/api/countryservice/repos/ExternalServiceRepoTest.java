package com.saikat.api.countryservice.repos;

import com.saikat.api.countryservice.models.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExternalServiceRepoTest {

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    private ExternalServiceRepo repo;

    private RestTemplate restTemplate;
    private final String URI = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID=xxxxxxxx&units=metric";

    @BeforeEach
    public void setUp() {
        repo = new ExternalServiceRepoImpl(restTemplateBuilder);
        restTemplate = mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void successfulRestCall() {
        Country country = new Country();
        country.setName("Finland");

        ResponseEntity<Country> responseEntity = new ResponseEntity<>(country, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(Mockito.<URI>any(), Mockito.eq(HttpMethod.GET),
                Mockito.any(), Mockito.<Class<Country>>any())).thenReturn(responseEntity);
        Country response = repo.fetchFromExternalService(Country.class, URI, "Finland");
        assertEquals(response.getName(), country.getName());
    }

    @Test
    public void externalServiceThrowsException() {
        when(restTemplate.exchange(Mockito.<URI>any(), Mockito.eq(HttpMethod.GET),
                Mockito.any(), Mockito.<Class<Country>>any())).thenThrow(new RestClientException("Invalid URL"));

        try {
            repo.fetchFromExternalService(Country.class, URI, "London");
            fail("Should have failed ");
        } catch (RestClientException re) {
            assertEquals(re.getMessage(), "Invalid URL");
        }

    }
}
