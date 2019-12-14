package com.example.msasample.mono.travelreservation.externalservices.flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.msasample.mono.travelreservation.util.DefaultClock;
import com.example.msasample.mono.travelreservation.util.SpringContext;

public class FlightMicroServiceImpl implements FlightMicroService {

	@Override
	public FlightReservationOfFlightMS reserve(
			String flightName, LocalDate departureDate, List<String> tourists) {
		
		return FlightReservationOfFlightMS.builder()
				.flightName(flightName) //
				.flightReservationId(1L)
				.departureDateTime(LocalDateTime.now(SpringContext.getBean(DefaultClock.class).getClock()))
				.arrivalDateTime(LocalDateTime.now(SpringContext.getBean(DefaultClock.class).getClock()))
				.arrivalAirport("KIX")
				.departureAirport("HND")
				.seatNo("H7")
				.build();
	}

}
