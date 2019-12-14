package com.example.msasample.mono.travelreservation.util;

import java.time.Clock;

import org.springframework.stereotype.Component;

/**
 * 日時の基準クロックを管理する。
 * <br>
 * {@link #getClock()}を差し替えることで、実行時の基準時間を変更できるようにする目的で用意。
 * 
 * @author ootsuka
 *
 */
@Component
public class DefaultClock {
	
	public Clock getClock() {
		return Clock.systemDefaultZone();
	}
}
