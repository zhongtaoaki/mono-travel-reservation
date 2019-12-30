package com.example.msasample.mono.travelreservation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.msasample.mono.travelreservation.externalservices.flight.FlightMicroService;
import com.example.msasample.mono.travelreservation.externalservices.flight.FlightReservationOfFlightMS;
import com.example.msasample.mono.travelreservation.externalservices.hotel.HotelMicroService;
import com.example.msasample.mono.travelreservation.externalservices.hotel.HotelReservationOfHotelMS;
import com.example.msasample.mono.travelreservation.model.entities.ApplicationInfo;
import com.example.msasample.mono.travelreservation.model.entities.FlightReservation;
import com.example.msasample.mono.travelreservation.model.entities.HotelReservation;
import com.example.msasample.mono.travelreservation.model.entities.Itinerary;
import com.example.msasample.mono.travelreservation.model.entities.Reservation;
import com.example.msasample.mono.travelreservation.model.services.ApplicationInfoService;
import com.example.msasample.mono.travelreservation.model.services.ItineraryService;

/**
 * 旅行予約アプリケーションのコントローラー。
 * 
 * @author ootsuka
 *
 */
@RestController
public class MonoTravelReservationController {

	@Autowired
	private ItineraryService itineraryService;

	@Autowired
	private ApplicationInfoService applicationInfoService;

	@Autowired
	private FlightMicroService flightMicroService;

	@Autowired
	private HotelMicroService hotelMicroService;

	/**
	 * 予約を行う。
	 * 
	 * @param applicationInfo 予約パラメータ情報。
	 */
	@PostMapping("/application")
	public List<Long> reserve(@Valid ApplicationInfo applicationInfo) {

		ApplicationInfo savedApplicationInfo = applicationInfoService.save(applicationInfo);

		List<Reservation> reservations = new ArrayList<>();

		applicationInfo.getDesiredFlights().stream().map(f -> {
			FlightReservationOfFlightMS flightReservationOfFlightMS = flightMicroService.reserve(f.getFlightName(),
					f.getDepartureDate(), null);
			FlightReservation flightReservation = new FlightReservation();
			BeanUtils.copyProperties(flightReservationOfFlightMS, flightReservation);
			return flightReservation;
		}).forEach(reservations::add);

		applicationInfo.getDesiredHotels().stream().map(h -> {
			HotelReservationOfHotelMS hotelReservationOfHotelMS = hotelMicroService.reserve(h.getName(),
					h.getCheckInDate(), h.getCheckOutDate());
			HotelReservation hotelReservation = new HotelReservation();
			BeanUtils.copyProperties(hotelReservationOfHotelMS, hotelReservation);
			return hotelReservation;
		}).forEach(reservations::add);

		Itinerary savedItinerary = itineraryService.save(applicationInfo.getTourists(), reservations,
				applicationInfo.getApplicant());

		return Arrays.asList(savedApplicationInfo.getId(), savedItinerary.getId());
	}

	/**
	 * 予約された旅程情報をすべて取得する。
	 * 
	 * @return 旅程情報のリスト。
	 */
	@GetMapping("/itinerary/listAll")
	public List<Itinerary> getAllItineraries() {
		return itineraryService.getAllItineraries();
	}
}
