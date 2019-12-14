package com.example.msasample.mono.travelreservation.externalservices.hotel;

import java.time.LocalDate;

public interface HotelMicroService {
	
	HotelReservationOfHotelMS reserve(String hotelName, LocalDate checkInDate, LocalDate checkOutDate);
	
}
