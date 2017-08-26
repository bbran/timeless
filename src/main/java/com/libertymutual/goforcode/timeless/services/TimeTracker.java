package com.libertymutual.goforcode.timeless.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.timeless.models.WeeklyTime;

@Service
public class TimeTracker {
	private List<WeeklyTime> submittedWeeks;
	private WeeklyTime currentWeek;
	
	public List<WeeklyTime> getAll()	{
		if (submittedWeeks == null)	{
			submittedWeeks = getCSVRecordsFromCSV("weeklyTime.csv");
		}
		return submittedWeeks;
	}

	public WeeklyTime getCurrent() {
		if (currentWeek == null)	{
			if (!getCSVRecordsFromCSV("currentWeek.csv").isEmpty())	{
				currentWeek = getCSVRecordsFromCSV("currentWeek.csv").get(0);
			} else	{
				currentWeek = new WeeklyTime();
			}
		}
		return currentWeek;
	}

	public WeeklyTime getCurrentWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCurrentWeek(WeeklyTime updatedCurrentWeek) {
		// TODO Auto-generated method stub
		
	}

	public void submitWeek(WeeklyTime updatedCurrentWeek) {
		// TODO Auto-generated method stub
		
	}
	
	public List<WeeklyTime> getCSVRecordsFromCSV(String filepath)	{
		List<WeeklyTime> weeklyTimeFromCSV = new ArrayList<WeeklyTime>();
		try (	FileReader reader = new FileReader(filepath);
				BufferedReader br = new BufferedReader(reader))	{
			Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(br);
			
			WeeklyTime weekTime;
			for (CSVRecord record : records)	{
				weekTime = new WeeklyTime();
				weekTime.setWeekOf(record.get(0));
				weekTime.setMonHours(Double.parseDouble(record.get(1)));
				weekTime.setTueHours(Double.parseDouble(record.get(2)));
				weekTime.setWedHours(Double.parseDouble(record.get(3)));
				weekTime.setThuHours(Double.parseDouble(record.get(4)));
				weekTime.setFriHours(Double.parseDouble(record.get(5)));
				weeklyTimeFromCSV.add(weekTime);
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
		} catch (IOException e) {
			System.err.println("Cannot access file");
		}
		
		return weeklyTimeFromCSV;		
	}

}
