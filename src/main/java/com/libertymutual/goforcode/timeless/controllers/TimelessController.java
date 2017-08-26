package com.libertymutual.goforcode.timeless.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.goforcode.timeless.models.WeeklyTime;
import com.libertymutual.goforcode.timeless.services.TimeTracker;

@Controller
@RequestMapping("/")
public class TimelessController {
	private TimeTracker tracker;
	private WeeklyTime currentWeek;
	private List<WeeklyTime> submittedWeeks;
	
	public TimelessController(TimeTracker tracker)	{
		this.tracker = tracker;
	}
	
	@GetMapping("")
	public String goToDefault()	{
		return "redirect:/timeless";
	}
	
	@GetMapping("timeless")
	public ModelAndView displayDefault()	{
		ModelAndView modelAndView = new ModelAndView("timeless/default");
		submittedWeeks = tracker.getAll();
		currentWeek = tracker.getCurrentWeek();
		modelAndView.addObject("currentWeek", currentWeek);	
		modelAndView.addObject("submittedWeeks", submittedWeeks);
		return modelAndView;
	}
	
	@PostMapping("updateTime")
	public String update(WeeklyTime updatedCurrentWeek, String action)	{
		if (action.equals("update"))	{
			tracker.updateCurrentWeek(updatedCurrentWeek);
			currentWeek = updatedCurrentWeek;
		} else if (action.equals("submit"))	{
			tracker.submitWeek(updatedCurrentWeek);
			currentWeek = new WeeklyTime();
		}
		return "redirect:/timeless";
	}
}
