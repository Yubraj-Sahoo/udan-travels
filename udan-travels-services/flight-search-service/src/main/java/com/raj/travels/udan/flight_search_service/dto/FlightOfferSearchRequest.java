package com.raj.travels.udan.flight_search_service.dto;

import com.raj.travels.udan.travel_domain.enums.air.TravellerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * DTO representing a flight offer search request.
 * This class encapsulates all the necessary information required to perform a flight offer search,
 * including origin and destination details, traveler information, and search criteria.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightOfferSearchRequest {
    private String currencyCode;
    private List<OriginDestination> originDestinations;
    private List<Traveler> travelers;
    private List<String> sources;
    private SearchCriteria searchCriteria;

    /**
     * DTO representing an origin-destination pair for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class OriginDestination {
        private Integer id;
        private String originLocationCode;
        private String destinationLocationCode;
        private DepartureDateTimeRange departureDateTimeRange;

        //        radius
        private String originRadius;
        private String destinationRadius;

        //        alternate airports
        private List<String> alternativeOriginsCodes;
        private List<String> alternativeDestinationsCodes;
    }

    /**
     * DTO representing a departure date and time range for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class DepartureDateTimeRange {
        private LocalDate date;
        private LocalTime time;
        private String dateWindow;
        private String timeWindow;
    }

    /**
     * DTO representing a traveler for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Traveler {
        private Integer id;
        private TravellerType travelerType;
    }

    /**
     * DTO representing search criteria for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SearchCriteria {
        private Boolean addOneWayOffers;
        private Integer maxFlightOffers;
        private FlightFilters flightFilters;
        private PricingOption pricingOptions;
        private AdditionalInformation additionalInformation;
    }

    /**
     * DTO representing flight filters for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class FlightFilters {
        private List<CabinRestriction> cabinRestrictions;
        private CarrierRestriction carrierRestrictions;
    }

    /**
     * DTO representing pricing options for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class PricingOption {
        private Boolean includedCheckedBagsOnly;
        private Boolean refundableFare;
        private Boolean noPenaltyFare;
    }

    /**
     * DTO representing additional information for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class AdditionalInformation {
        private Boolean chargeableCheckedBags;
        private Boolean brandedFares;
    }

    /**
     * DTO representing cabin restrictions for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CabinRestriction {
        private String cabin;
        private String coverage;
        private List<String> originDestinationIds;
    }

    /**
     * DTO representing carrier restrictions for a flight search.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CarrierRestriction {
        private List<String> includedCarrierCodes;
    }


}
