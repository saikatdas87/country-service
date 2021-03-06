package com.saikat.api.countryservice.repos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class ExternalServiceRepoImpl implements ExternalServiceRepo {

    private final Logger logger = LoggerFactory.getLogger(ExternalServiceRepoImpl.class);

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public ExternalServiceRepoImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    /**
     * The method to call any external API (GET) and store the required response data in corresponding POJO and then return it
     *
     * @param responseType response POJO
     * @param uri          external service uri
     * @param uriParams    the params for the URI
     * @return T the POJO passed as @param - responseType
     * @throws RestClientException
     */
    public <T> T fetchFromExternalService(Class<T> responseType, String uri, Object... uriParams) throws RestClientException {
        URI exchangeUrl = new UriTemplate(uri).expand(uriParams);
        RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET, exchangeUrl);
        RestTemplate template = restTemplateBuilder.build();
        logger.debug("Calling external service : " + exchangeUrl.toString());

        ResponseEntity<T> converterResponse = template.exchange(exchangeUrl, HttpMethod.GET, request, responseType);
        logger.debug("External service call successfully completed : ");

        return converterResponse.getBody();
    }
}
