package com.raj.travels.udan.flight_search_service.rest;

import com.raj.travels.udan.flight_search_service.domain.FlightSearchDetails;
import com.raj.travels.udan.travel_domain.domain.air.AirItinerary;

/**
 * Service interface for searching flights.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-08-24
 */
public interface FlightSearchRestService {
    /**
     * Searches for flights based on the provided search details.
     *
     * @param flightSearchDetails the details of the flight search
     * @return an AirItinerary containing the search results
     */
    AirItinerary searchFlights(FlightSearchDetails flightSearchDetails);
}
