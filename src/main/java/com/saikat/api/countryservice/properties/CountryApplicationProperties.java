package com.saikat.api.countryservice.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * The POJO class to hold all configured property values
 * <p>
 * Created by Saikat Das
 */

@Component
@PropertySource("classpath:application.properties")
public class CountryApplicationProperties {

    @Value("${country.provider.api.all}")
    private String allCountriesApi;

    @Value("${country.provider.api.details}")
    private String countryDetailsApi;

    public String getAllCountriesApi() {
        return allCountriesApi;
    }

    public void setAllCountriesApi(String allCountriesApi) {
        this.allCountriesApi = allCountriesApi;
    }

    public String getCountryDetailsApi() {
        return countryDetailsApi;
    }

    public void setCountryDetailsApi(String countryDetailsApi) {
        this.countryDetailsApi = countryDetailsApi;
    }
}
