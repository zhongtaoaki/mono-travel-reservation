package com.example.msasample.mono.travelreservation.externalservices.hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.msasample.mono.travelreservation.util.DefaultClock;
import com.example.msasample.mono.travelreservation.util.SpringContext;

public class HotelMicroServiceImpl implements HotelMicroService {

	@Override
	public HotelReservationOfHotelMS reserve(String hotelName, LocalDate checkInDate, LocalDate checkOutDate) {

		return HotelReservationOfHotelMS.builder()
				.hotelReservationId(1L)
				.name("HOTEL OSAKA")
				.checkInDateTime(LocalDateTime.now(SpringContext.getBean(DefaultClock.class).getClock()))
				.checkOutDateTime(LocalDateTime.now(SpringContext.getBean(DefaultClock.class).getClock()))
				.roomNo("808")
				.build();
	}

}
