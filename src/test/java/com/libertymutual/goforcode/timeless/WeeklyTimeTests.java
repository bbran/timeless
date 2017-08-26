package com.libertymutual.goforcode.timeless;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.timeless.models.WeeklyTime;

public class WeeklyTimeTests {
	private WeeklyTime weekTime;
	
	@Test
	public void test_that_calculateHours_sums_hours_from_all_days()	{
		//Arrange
		weekTime.setMonHours(4.2);
		weekTime.setTueHours(5.0);
		weekTime.setWedHours(3);
		weekTime.setThuHours(15.1);
		weekTime.setFriHours(6);
		
		//Act
		double total = weekTime.getTotalHours();
		
		//Assert
		assertThat(total).isCloseTo(33.3, within(.000001));
	}
	
	@Test
	public void test_setting_and_getting_weekOf() {
		//Arrange
		
		
		//Act
		weekTime.setWeekOf("1/1/2017");
		
		//Assert
		assertThat(weekTime.getWeekOf()).isEqualTo("1/1/2017");
	}

	@Test
	public void test_setting_and_getting_hours_for_Monday() {
		//Arrange
		
		//Act
		weekTime.setMonHours(4.2);
		
		//Assert
		assertThat(weekTime.getMonHours()).isEqualTo(4.2);
	}
	
	@Test
	public void test_setting_and_getting_hours_for_Tuesday() {
		//Arrange
		
		//Act
		weekTime.setTueHours(4.2);
		
		//Assert
		assertThat(weekTime.getTueHours()).isEqualTo(4.2);
	}
	
	@Test
	public void test_setting_and_getting_hours_for_Wednesday() {
		//Arrange
		
		//Act
		weekTime.setWedHours(4.2);
		
		//Assert
		assertThat(weekTime.getWedHours()).isEqualTo(4.2);
	}
	
	@Test
	public void test_setting_and_getting_hours_for_Thursday() {
		//Arrange
		
		//Act
		weekTime.setThuHours(4.2);
		
		//Assert
		assertThat(weekTime.getThuHours()).isEqualTo(4.2);
	}
	
	@Test
	public void test_setting_and_getting_hours_for_Friday() {
		//Arrange
		
		//Act
		weekTime.setFriHours(4.2);
		
		//Assert
		assertThat(weekTime.getFriHours()).isEqualTo(4.2);
	}
	
	@Before
	public void setUp()	{
		weekTime = new WeeklyTime();
	}

}
