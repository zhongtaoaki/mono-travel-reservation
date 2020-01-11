package com.example.msasample.mono.travelreservation.externalservices.flight;

import org.springframework.stereotype.Service;

/**
 * フライトマイクロサービス。
 * 
 * @author ootsuka
 *
 */
@Service
public interface FlightMicroService {

	FlightReservationOfFlightMS reserve(FlightApplicationInfoOfFlightMS flightApplicationInfoOfFlightMS)
			throws Exception;
}
