package com.example.msasample.mono.travelreservation.externalservices.hotel;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HotelMicroServiceImpl implements HotelMicroService {

	@Value(value = "${mono-hotel-host}")
	private String host;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public HotelReservationOfHotelMS reserve(HotelApplicationInfoOfHotelMS requestBody) throws Exception {

		String uri = host + "/hotel";

		RequestEntity<HotelApplicationInfoOfHotelMS> request = RequestEntity.post(new URI(uri))
				.accept(MediaType.APPLICATION_JSON).body(requestBody);

		// ホテルサービスと通信する
		ResponseEntity<HotelReservationOfHotelMS> response = restTemplate.exchange(request,
				HotelReservationOfHotelMS.class);

		return response.getBody();
	}

}
