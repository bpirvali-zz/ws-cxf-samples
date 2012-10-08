package com.bp.bs;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestgetNextUpdateTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GregorianCalendar now = new GregorianCalendar();
		System.out.println(now.getTime().toString());
		System.out.println(getNextUpdateTime(now).getTime().toString());
	}

	private static GregorianCalendar getNextUpdateTime(GregorianCalendar now) {
		GregorianCalendar nextUpdate = new GregorianCalendar();
		nextUpdate.setTime(now.getTime());
		nextUpdate.set(Calendar.HOUR_OF_DAY, 21);
		nextUpdate.set(Calendar.MINUTE, 0);
		nextUpdate.set(Calendar.SECOND, 0);
		nextUpdate.set(Calendar.MILLISECOND, 0);
		System.out.println(nextUpdate.getTime().toString());
		System.out.println(now.get(Calendar.HOUR_OF_DAY));
		if (now.get(Calendar.HOUR_OF_DAY) >= nextUpdate.get(Calendar.HOUR_OF_DAY)) {
			nextUpdate.add(Calendar.DAY_OF_YEAR, 1);
		}
		return nextUpdate;
	}
}
