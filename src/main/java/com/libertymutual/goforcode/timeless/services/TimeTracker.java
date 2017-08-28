package com.libertymutual.goforcode.timeless.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.timeless.models.WeeklyTime;

@Service
public class TimeTracker {
	private List<WeeklyTime> submittedWeeks;
	private WeeklyTime currentWeek;
	
	public List<WeeklyTime> getSubmittedWeeks()	{
		if (submittedWeeks == null)	{
			submittedWeeks = getWeeklyTimesFromCSV("weeklyTime.csv");
		}
		return submittedWeeks;
	}

	public WeeklyTime getCurrentWeek() {
		if (currentWeek == null)	{
			List<WeeklyTime> currentWeekFromCSV = getWeeklyTimesFromCSV("currentWeek.csv");
			System.out.println(currentWeekFromCSV);
			System.out.println(currentWeekFromCSV.isEmpty());
			if (!currentWeekFromCSV.isEmpty())	{
				currentWeek = currentWeekFromCSV.get(0);
			} else	{
				currentWeek = new WeeklyTime();
			}
		}
		return currentWeek;
	}

	public void updateCurrentWeek(WeeklyTime updatedCurrentWeek) {
		writeWeeklyTimetoCSV(updatedCurrentWeek, "currentWeek.csv", false);
		currentWeek = updatedCurrentWeek;
		
	}

	public void submitWeek(WeeklyTime updatedCurrentWeek) {
		writeWeeklyTimetoCSV(updatedCurrentWeek, "weeklyTime.csv", true);
		submittedWeeks.add(0, updatedCurrentWeek);
		currentWeek = new WeeklyTime();
		clearCurrentWeekFile();
		
	}
	
	private List<WeeklyTime> getWeeklyTimesFromCSV(String filepath)	{
		List<WeeklyTime> weeklyTimeFromCSV = new ArrayList<WeeklyTime>();
		try (	FileReader reader = new FileReader(filepath);
				BufferedReader br = new BufferedReader(reader))	{
			Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(br);
			
			WeeklyTime weekTime;
			for (CSVRecord record : records)	{
				if (record.size() > 1) {
					weekTime = new WeeklyTime();
					weekTime.setWeekOf(record.get(0));
					weekTime.setMonHours(Double.parseDouble(record.get(1)));
					weekTime.setTueHours(Double.parseDouble(record.get(2)));
					weekTime.setWedHours(Double.parseDouble(record.get(3)));
					weekTime.setThuHours(Double.parseDouble(record.get(4)));
					weekTime.setFriHours(Double.parseDouble(record.get(5)));
					weeklyTimeFromCSV.add(0, weekTime);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
		} catch (IOException e) {
			System.err.println("Cannot access file");
		}
		
		return weeklyTimeFromCSV;		
	}
	
	private void writeWeeklyTimetoCSV(WeeklyTime weekTime, String filepath, boolean append)	{
		try (	FileWriter writer = new FileWriter(filepath, append);
				BufferedWriter bw = new BufferedWriter(writer);
				CSVPrinter printer = new CSVPrinter(bw, CSVFormat.RFC4180))	{
			
			String[] lineToWrite = new String[] {	weekTime.getWeekOf(), 
													Double.toString(weekTime.getMonHours()), 
													Double.toString(weekTime.getTueHours()), 
													Double.toString(weekTime.getWedHours()),
													Double.toString(weekTime.getThuHours()),
													Double.toString(weekTime.getFriHours()),};
			
			printer.printRecord(lineToWrite);
			
		} catch (IOException e) {
			System.err.println("Cannot access file");
		}
	}
	
	private void clearCurrentWeekFile()	{
		try (	FileWriter writer = new FileWriter("currentWeek.csv", false);
				BufferedWriter bw = new BufferedWriter(writer);
				CSVPrinter printer = new CSVPrinter(bw, CSVFormat.RFC4180))	{
			
			String[] lineToWrite = new String[] {};
			
			printer.printRecord(lineToWrite);
			
		} catch (IOException e) {
			System.err.println("Cannot access file");
		}
		
	}

}
