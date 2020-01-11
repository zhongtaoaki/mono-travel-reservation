package com.example.msasample.mono.travelreservation.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.msasample.mono.travelreservation.model.entities.FlightReservation;
import com.example.msasample.mono.travelreservation.model.entities.FlightReservationRepository;
import com.example.msasample.mono.travelreservation.model.entities.HotelReservation;
import com.example.msasample.mono.travelreservation.model.entities.HotelReservationRepository;
import com.example.msasample.mono.travelreservation.model.entities.Itinerary;
import com.example.msasample.mono.travelreservation.model.entities.ItineraryRepository;

/**
 * メッセージのサービスクラス。
 * 
 * @author ootsuka
 *
 */
@Service
public class ItineraryService {

	@Autowired
	private ItineraryRepository itineraryRepository;

	@Autowired
	private FlightReservationRepository flightReservationRepository;

	@Autowired
	private HotelReservationRepository hotelReservationRepository;

	/**
	 * 旅程を保存する。
	 * 
	 * @param tourists     旅行者。
	 * @param reservations 予約情報。
	 * @return
	 */
	@Transactional
	public Itinerary save(List<String> tourists, List<FlightReservation> flightReservations,
			List<HotelReservation> hotelReservations, String applicant) {
		List<FlightReservation> savedFlightReservations = flightReservations.stream().map(this::saveReservation)
				.collect(Collectors.toList());
		List<HotelReservation> savedHotelReservations = hotelReservations.stream().map(this::saveReservation)
				.collect(Collectors.toList());

		Itinerary itinerary = Itinerary.builder() //
				// .tourists(tourists) //
				.applicant(applicant) //
				.flightReservations(savedFlightReservations) //
				.hotelReservations(savedHotelReservations) //
				.build();
		return itineraryRepository.save(itinerary);
	}

	/**
	 * フライト予約情報を保存します。
	 * 
	 * @param flightReservation フライト予約情報。
	 * @return 保存済みのフライト予約情報。
	 */
	private FlightReservation saveReservation(FlightReservation flightReservation) {
		return flightReservationRepository.saveAndFlush(flightReservation);
	}

	/**
	 * ホテル予約情報を保存します。
	 * 
	 * @param hotelReservation ホテル予約情報。
	 * @return 保存済みのホテル予約情報。
	 */
	private HotelReservation saveReservation(HotelReservation hotelReservation) {
		return hotelReservationRepository.saveAndFlush(hotelReservation);
	}

//	private Reservation saveReservation(Reservation reservation) {
//		if (reservation instanceof FlightReservation) {
//			return saveReservation((FlightReservation) reservation);
//		}
//		if (reservation instanceof HotelReservation) {
//			return saveReservation((HotelReservation) reservation);
//		}
//		throw new IllegalArgumentException(
//				"reservation is not desired Reservation class. It is " + reservation.getClass().getSimpleName() + ".");
//	}

	/**
	 * すべてのメッセージを取得する。 <br>
	 * <br>
	 * メッセージがない場合は空のListオブジェクトが返却される。
	 * 
	 * @return すべてのメッセージのリスト。
	 */
	@Transactional(readOnly = true)
	public List<Itinerary> getAllItineraries() {
		return itineraryRepository.findAll();
	}
}
