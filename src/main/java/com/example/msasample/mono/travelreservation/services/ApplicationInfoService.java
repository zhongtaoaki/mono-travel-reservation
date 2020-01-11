package com.example.msasample.mono.travelreservation.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msasample.mono.travelreservation.model.entities.ApplicationInfo;
import com.example.msasample.mono.travelreservation.model.entities.ApplicationInfoRepository;
import com.example.msasample.mono.travelreservation.model.entities.FlightApplicationInfoRepository;
import com.example.msasample.mono.travelreservation.model.entities.HotelApplicationInfoRepository;

/**
 * 申込情報を保存する。
 * 
 * @author ootsuka
 *
 */
@Service
public class ApplicationInfoService {

	@Autowired
	private ApplicationInfoRepository applicationInfoRepository;

	@Autowired
	private FlightApplicationInfoRepository flightApplicationInfoRepository;

	@Autowired
	private HotelApplicationInfoRepository hotelApplicationInfoRepository;

	@Transactional
	public ApplicationInfo save(ApplicationInfo applicationInfo) {

		applicationInfo.getDesiredFlights().forEach(f -> flightApplicationInfoRepository.save(f));
		applicationInfo.getDesiredHotels().forEach(h -> hotelApplicationInfoRepository.save(h));

		return applicationInfoRepository.save(applicationInfo);
	}
}
