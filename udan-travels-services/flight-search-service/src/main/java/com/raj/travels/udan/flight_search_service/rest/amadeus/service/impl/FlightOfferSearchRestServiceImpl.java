package com.raj.travels.udan.flight_search_service.rest.amadeus.service.impl;

import com.raj.travels.udan.flight_search_service.domain.FlightSearchDetails;
import com.raj.travels.udan.flight_search_service.dto.FlightOfferSearchRequest;
import com.raj.travels.udan.flight_search_service.rest.amadeus.builder.FlightOfferSearchRequestBuilder;
import com.raj.travels.udan.flight_search_service.rest.amadeus.service.FlightOfferSearchRestService;
import com.raj.travels.udan.travel_domain.domain.air.AirItinerary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the FlightOfferSearchRestService interface.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-08-24
 */
@Service
@RequiredArgsConstructor
public class FlightOfferSearchRestServiceImpl implements FlightOfferSearchRestService {
    private FlightOfferSearchRequestBuilder flightOfferSearchRequestBuilder;

    /**
     * Searches for flights based on the provided search details.
     *
     * @param flightSearchDetails the details of the flight search
     * @return an AirItinerary containing the search results
     */
    @Override
    public AirItinerary searchFlights(FlightSearchDetails flightSearchDetails) {
        FlightOfferSearchRequest flightOfferSearchRequest = flightOfferSearchRequestBuilder.build(flightSearchDetails);
        return null;
    }
}
