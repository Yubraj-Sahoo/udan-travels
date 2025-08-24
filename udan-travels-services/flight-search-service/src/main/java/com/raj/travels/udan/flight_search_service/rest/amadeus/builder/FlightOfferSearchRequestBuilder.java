package com.raj.travels.udan.flight_search_service.rest.amadeus.builder;

import com.raj.travels.udan.flight_search_service.domain.FlightSearchDetails;
import com.raj.travels.udan.flight_search_service.dto.FlightOfferSearchRequest;

/**
 * Builder interface for constructing FlightOfferSearchRequest objects from FlightSearchDetails.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-08-24
 */
public interface FlightOfferSearchRequestBuilder {
    /**
     * Builds a FlightOfferSearchRequest from the given FlightSearchDetails.
     *
     * @param flightSearchDetails the details of the flight search
     * @return a FlightOfferSearchRequest object
     */
    FlightOfferSearchRequest build(FlightSearchDetails flightSearchDetails);
}
