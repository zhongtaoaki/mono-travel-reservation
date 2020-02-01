package com.example.msasample.mono.travelreservation.externalservices.flight;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlightMicroServiceImpl implements FlightMicroService {

	@Value(value = "${mono-flight-host}")
	private String host;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public FlightReservationOfFlightMS reserve(FlightApplicationInfoOfFlightMS requestBody) throws RuntimeException {
		String uri = host + "/flight";
		RequestEntity<FlightApplicationInfoOfFlightMS> request;
		try {
			request = RequestEntity.post(new URI(uri)).accept(MediaType.APPLICATION_JSON).body(requestBody);
			// ホテルサービスと通信する
			ResponseEntity<FlightReservationOfFlightMS> response = restTemplate.exchange(request,
					FlightReservationOfFlightMS.class);
			return response.getBody();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void cancelFlight(Long id) throws RuntimeException {
		String uri = host + "/flight/" + id;
		RequestEntity<Void> request;
		try {
			request = RequestEntity.delete(new URI(uri)).build();
			// ホテルサービスと通信する
			restTemplate.exchange(request, Void.class);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
