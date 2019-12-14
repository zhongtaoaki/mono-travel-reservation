package com.example.msasample.mono.travelreservation.model.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.msasample.mono.travelreservation.model.entities.ApplicationInfo;
import com.example.msasample.mono.travelreservation.model.entities.ApplicationInfoRepository;

/**
 * 申込情報を保存する。
 * 
 * @author ootsuka
 *
 */
public class ApplicationInfoService {

	@Autowired
	ApplicationInfoRepository applicationInfoRepository;

	@Transactional
	public ApplicationInfo save(ApplicationInfo applicationInfo) {
		return null;
	}
}
