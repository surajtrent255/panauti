package com.ishanitech.ipalika.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.TimeZone;

public class AgeCalculatorUtil {

	
	public static String calculateAge(String dateOfBirth) {
String calculatedAge = "";
		
		
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		formatter.setTimeZone(tz);
		formatter.setLenient(false);
		Date date = null;
		try {
			date = (Date)formatter.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String adjustedDOB = formatter.format(date).toString();
		
		LocalDate DOB = LocalDate.parse(adjustedDOB);

		LocalDate now = LocalDate.now();
 
		//Calculates the difference between the DOB and the current time
        Period diff = Period.between(DOB, now);
		
        //Gets the year
        String extractedYear = convertToDevanagari(Integer.toString(diff.getYears()));
        
        //Gets the month
        String extractedMonth = convertToDevanagari(Integer.toString(diff.getMonths()));
        
        //Gets the day
        String extractedDay = convertToDevanagari(Integer.toString(diff.getDays()));
       
        
       //Checking for null year, month, day 
        if((extractedYear != null && !extractedYear.isEmpty()) && (extractedMonth != null && !extractedMonth.isEmpty()) && (extractedDay != null && !extractedDay.isEmpty())) {
        	calculatedAge = extractedYear + " बर्ष " + extractedMonth + " महिना " + extractedDay + " दिन "; 
        } else if((extractedYear != null && !extractedYear.isEmpty()) && (extractedMonth != null && !extractedMonth.isEmpty())) {
        	calculatedAge = extractedYear + " बर्ष " + extractedMonth + " महिना "; 
        } else if((extractedYear != null && !extractedYear.isEmpty()) &&  (extractedDay != null && !extractedDay.isEmpty())) {
        	calculatedAge = extractedYear + " बर्ष " +  extractedDay + " दिन "; 
        } else if((extractedYear != null && !extractedYear.isEmpty())) {
        	calculatedAge = extractedYear + " बर्ष ";
        } else if((extractedMonth != null && !extractedMonth.isEmpty()) && (extractedDay != null && !extractedDay.isEmpty())) {
        	calculatedAge = extractedMonth + " महिना " + extractedDay + " दिन "; 
        } else if((extractedMonth != null && !extractedMonth.isEmpty())) {
        	calculatedAge = extractedMonth + " महिना ";
        } else if((extractedDay != null && !extractedDay.isEmpty())) {
        	calculatedAge = extractedDay + " दिन "; 
        }
        
		return calculatedAge;
	}
	
	private static String convertToDevanagari(String englishText) {
		return englishText.replaceAll("0", "").replaceAll("1", "१").replaceAll("2", "२").replaceAll("3", "३").replaceAll("4", "४")
				.replaceAll("5", "५").replaceAll("6", "६").replaceAll("7", "७").replaceAll("8", "८").replaceAll("9", "९");
	}
}
