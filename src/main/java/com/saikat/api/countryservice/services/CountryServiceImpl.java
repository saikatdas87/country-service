package com.saikat.api.countryservice.services;

import com.saikat.api.countryservice.exceptions.NoPropertiesDefinedException;
import com.saikat.api.countryservice.exceptions.ResourceNotFoundException;
import com.saikat.api.countryservice.models.Country;
import com.saikat.api.countryservice.models.CountryDetails;
import com.saikat.api.countryservice.properties.CountryApplicationProperties;
import com.saikat.api.countryservice.repos.ExternalServiceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final ExternalServiceRepo externalServiceRepo;
    private final CountryApplicationProperties properties;

    @Autowired
    public CountryServiceImpl(ExternalServiceRepo externalServiceRepo, CountryApplicationProperties properties) {
        this.externalServiceRepo = externalServiceRepo;
        this.properties = properties;
    }

    /**
     * Method to fetch all countries calling external repo with the help of configured API
     *
     * @return List<Country> the list of countries
     * @throws NoPropertiesDefinedException
     * @throws ResourceNotFoundException
     */
    @Override
    public List<Country> fetchAllCountries() throws NoPropertiesDefinedException {
        final Optional<String> maybeProviderApi = Optional.ofNullable(properties.getAllCountriesApi());
        return maybeProviderApi.map(uri -> {
            if (uri.trim().equals("")) {
                throw new ResourceNotFoundException("No uri defined for fetching All countries in configuration");
            } else {
                try {
                    return Arrays.asList(externalServiceRepo.fetchFromExternalService(Country[].class, uri));
                } catch (RestClientException re) {
                    logger.error("RestClientException fetching countries : ", re);
                    throw new ResourceNotFoundException("Exception fetching all countries : " + re.getMessage());
                } catch (Exception e) {
                    logger.error("Some Exception occurred fetching all countries : ", e);
                    throw new ResourceNotFoundException("Something wrong fetching all countries : " + e.getMessage());
                }
            }
        }).orElseThrow(() -> new NoPropertiesDefinedException("No uri defined for fetching All countries in configuration"));
    }

    @Override
    public CountryDetails fetchCountryDetails(final String countryName) throws NoPropertiesDefinedException {
        final Optional<String> maybeProviderApi = Optional.ofNullable(properties.getCountryDetailsApi());
        return maybeProviderApi.map(uri -> {
            if (uri.trim().equals("")) {
                throw new ResourceNotFoundException("No uri defined for weather fetching All countries in configuration");
            } else {
                try {
                    final Optional<CountryDetails[]> maybeDetails =
                            Optional.ofNullable(externalServiceRepo.fetchFromExternalService(CountryDetails[].class, uri, countryName));
                    return maybeDetails.map(detailsArray -> {
                        if (detailsArray.length > 0) {
                            return detailsArray[0];
                        }
                        throw new ResourceNotFoundException("No details could be fetched for : " + countryName);
                    }).orElseThrow(() -> new ResourceNotFoundException("Something wrong fetching all countries : "));
                } catch (RestClientException re) {
                    logger.error("RestClientException fetching details for : " + countryName, re);
                    throw new ResourceNotFoundException("Exception fetching details for " + countryName + " : " + re.getMessage());
                } catch (Exception e) {
                    logger.error("Some Exception occurred fetching details for " + countryName, e);
                    throw new ResourceNotFoundException("Something wrong fetching details for " + countryName + " : " + e.getMessage());
                }
            }
        }).orElseThrow(() -> new NoPropertiesDefinedException("No uri defined for fetching country details in configuration"));
    }
}
