package com.raj.travels.udan.flight_search_service.unit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.raj.travels.udan.flight_search_service.dto.FlightOfferSearchRequest;
import com.raj.travels.udan.travel_domain.util.JsonUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestConverter {
    @Test
    void testCovert(){
        FlightOfferSearchRequest flightOfferSearchRequest = JsonUtils.readFromJsonFile(
                "src/test/resources/json/1.json", new TypeReference<FlightOfferSearchRequest>() {
        });

        assertNotNull(flightOfferSearchRequest);
        System.out.println(JsonUtils.writeAsJsonString(flightOfferSearchRequest));
    }
}
