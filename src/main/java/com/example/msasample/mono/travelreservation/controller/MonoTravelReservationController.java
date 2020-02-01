package com.example.msasample.mono.travelreservation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.msasample.mono.travelreservation.externalservices.flight.FlightApplicationInfoOfFlightMS;
import com.example.msasample.mono.travelreservation.externalservices.flight.FlightMicroService;
import com.example.msasample.mono.travelreservation.externalservices.flight.FlightReservationOfFlightMS;
import com.example.msasample.mono.travelreservation.externalservices.hotel.HotelApplicationInfoOfHotelMS;
import com.example.msasample.mono.travelreservation.externalservices.hotel.HotelMicroService;
import com.example.msasample.mono.travelreservation.externalservices.hotel.HotelReservationOfHotelMS;
import com.example.msasample.mono.travelreservation.model.entities.ApplicationInfo;
import com.example.msasample.mono.travelreservation.model.entities.FlightReservation;
import com.example.msasample.mono.travelreservation.model.entities.HotelReservation;
import com.example.msasample.mono.travelreservation.model.entities.Itinerary;
import com.example.msasample.mono.travelreservation.services.ApplicationInfoService;
import com.example.msasample.mono.travelreservation.services.ItineraryService;

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
	public List<Long> reserve(@RequestBody ApplicationInfo applicationInfo, WebRequest webRequest) throws Exception {

		List<Runnable> compensations = new ArrayList<>();
		webRequest.setAttribute("Compensations", compensations, WebRequest.SCOPE_SESSION);

		ApplicationInfo savedApplicationInfo = applicationInfoService.save(applicationInfo);

		Itinerary savedItinerary = this.reserveFlightAndHotel(savedApplicationInfo, compensations);

		return Arrays.asList(savedApplicationInfo.getId(), savedItinerary.getId());
	}

	@Transactional
	public Itinerary reserveFlightAndHotel(ApplicationInfo applicationInfo, List<Runnable> compensations)
			throws RuntimeException {
		List<FlightReservation> flightReservations = new ArrayList<>();
		List<HotelReservation> hotelReservations = new ArrayList<>();

		// フライト予約情報を取得する
		applicationInfo.getDesiredFlights().stream().map(f -> {
			FlightApplicationInfoOfFlightMS flightApplicationInfoOfFlightMS = FlightApplicationInfoOfFlightMS.builder()//
					.flightName(f.getFlightName())//
					.departureDate(f.getDepartureDate())//
					.build();
			FlightReservationOfFlightMS flightReservationOfFlightMS = flightMicroService
					.reserve(flightApplicationInfoOfFlightMS);
			compensations.add(0,
					() -> flightMicroService.cancelFlight(flightReservationOfFlightMS.getFlightReservationId()));

			FlightReservation flightReservation = new FlightReservation();
			BeanUtils.copyProperties(flightReservationOfFlightMS, flightReservation);
			return flightReservation;
		}).forEach(flightReservations::add);

		// ホテル予約情報を取得する
		applicationInfo.getDesiredHotels().stream().map(h -> {
			HotelApplicationInfoOfHotelMS hotelApplicationInfoOfHotelMS = HotelApplicationInfoOfHotelMS.builder()
					.name(h.getName())//
					.checkInDate(h.getCheckInDate())//
					.checkOutDate(h.getCheckOutDate())//
					.build();
			HotelReservationOfHotelMS hotelReservationOfHotelMS = hotelMicroService
					.reserve(hotelApplicationInfoOfHotelMS);
			compensations.add(0,
					() -> hotelMicroService.cancelHotel(hotelReservationOfHotelMS.getHotelReservationId()));

			HotelReservation hotelReservation = new HotelReservation();
			BeanUtils.copyProperties(hotelReservationOfHotelMS, hotelReservation);
			return hotelReservation;
		}).forEach(hotelReservations::add);

		return itineraryService.save(applicationInfo.getTourists(), flightReservations, hotelReservations,
				applicationInfo.getApplicant());

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
