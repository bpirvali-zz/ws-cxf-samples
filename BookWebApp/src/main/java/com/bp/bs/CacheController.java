package com.bp.bs;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response.ResponseBuilder;

public class CacheController {
	public static void setExpiry(ResponseBuilder builder) {
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar nextUpdate = getNextUpdateTime(now);
		int maxAge = (int) ((nextUpdate.getTimeInMillis() - now
				.getTimeInMillis()) / 1000L);
		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(maxAge);
		builder.cacheControl(cacheControl);
		builder.expires(nextUpdate.getTime());
	}

	private static GregorianCalendar getNextUpdateTime(GregorianCalendar now) {
		GregorianCalendar nextUpdate = new GregorianCalendar();
		nextUpdate.setTime(now.getTime());
		nextUpdate.set(Calendar.HOUR_OF_DAY, 10);
		nextUpdate.set(Calendar.MINUTE, 0);
		nextUpdate.set(Calendar.SECOND, 0);
		nextUpdate.set(Calendar.MILLISECOND, 0);
		if (now.get(Calendar.HOUR_OF_DAY) >= 10) {
			nextUpdate.add(Calendar.DAY_OF_YEAR, 1);
		}
		return nextUpdate;
	}
}