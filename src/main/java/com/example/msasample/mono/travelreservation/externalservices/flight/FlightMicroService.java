package com.example.msasample.mono.travelreservation.externalservices.flight;

import java.time.LocalDate;
import java.util.List;


/**
 * フライトマイクロサービス。
 * 
 * @author ootsuka
 *
 */
public interface FlightMicroService {
	FlightReservationOfFlightMS reserve(
			String flightName, LocalDate departureDate, List<String> tourists);
}
