package com.example.msasample.mono.travelreservation.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * アプリケーションの例外ハンドラ。
 * 
 * @author ootsuka
 *
 */
@RestControllerAdvice
@Slf4j
public class MonoTravelReservationExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.debug("入力エラー", e);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		BindingResult result = e.getBindingResult();
		for (ObjectError error : result.getGlobalErrors()) {
			map.add("global", error.getDefaultMessage());
		}
		for (FieldError error : result.getFieldErrors()) {
			map.add(error.getField(), error.getDefaultMessage());
		}

		return map;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleExcetion(Exception e, WebRequest webRequest) {
		log.error("例外発生", e);

		@SuppressWarnings("unchecked")
		List<Runnable> compensationsList = (List<Runnable>) webRequest.getAttribute("Compensations",
				WebRequest.SCOPE_SESSION);

		compensationsList.stream().forEach(Runnable::run);

		return Collections.singletonMap("message", e.getMessage());
	}
}
