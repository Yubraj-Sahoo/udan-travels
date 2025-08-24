package com.raj.travels.udan.flight_search_service.domain;

import com.raj.travels.udan.travel_domain.enums.air.CabinType;
import com.raj.travels.udan.travel_domain.enums.air.TravellerType;
import com.raj.travels.udan.travel_domain.enums.air.TripType;
import com.raj.travels.udan.travel_domain.enums.connection.ConnectionType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FlightSearchDetails {
    private TripType tripType;
    private List<FlightSearchLeg> flightSearchLegs;
    private PassengerDetails passengerDetails;
    private FlightSearchCriteria flightSearchCriteria;
    private ConnectionType connectionType;

    @Data
    private static class FlightSearchLeg {
        private Integer legNumber;
        private String origin;
        private String destination;
        private LocalDate departureDate;
    }

    @Data
    private static class PassengerDetails {
        private TravellerType travellerType;
        private Integer count;
    }

    @Data
    private static class FlightSearchCriteria {
        private String preferredAirline;
        private CabinType cabinType;
        private Integer maxStopOvers;
        private String currency;
        private String country;
        private int noOfResults;
    }
}
