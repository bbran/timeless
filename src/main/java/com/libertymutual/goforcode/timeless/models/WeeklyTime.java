package com.libertymutual.goforcode.timeless.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeeklyTime {
	private double monHours;
	private double tueHours;
	private double wedHours;
	private double thuHours;
	private double friHours;
	private String	weekOf = "";
	
	public double getMonHours() {
		return monHours;
	}
	public void setMonHours(double monHours) {
		this.monHours = monHours;
	}
	public double getTueHours() {
		return tueHours;
	}
	public void setTueHours(double tueHours) {
		this.tueHours = tueHours;
	}
	public double getWedHours() {
		return wedHours;
	}
	public void setWedHours(double wedHours) {
		this.wedHours = wedHours;
	}
	public double getThuHours() {
		return thuHours;
	}
	public void setThuHours(double thuHours) {
		this.thuHours = thuHours;
	}
	public double getFriHours() {
		return friHours;
	}
	public void setFriHours(double friHours) {
		this.friHours = friHours;
	}
	public String getWeekOf() {
		return weekOf;
	}
	public void setWeekOf(String weekOf) {
		this.weekOf = weekOf;
	}
	
	public String getWeekOfForBrowser()	{
		SimpleDateFormat stored = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat displayed = new SimpleDateFormat("mm/dd/yyyy");
		Date parsedDate = null;
		System.out.println(weekOf);
		try {
			parsedDate = stored.parse(weekOf);
		} catch (ParseException e) {
			System.err.println("Cannot parse date.");
		}
		return displayed.format(parsedDate);		
	}
	
	public double getTotalHours() {
		return monHours + tueHours + wedHours + thuHours + friHours;
	}
}
