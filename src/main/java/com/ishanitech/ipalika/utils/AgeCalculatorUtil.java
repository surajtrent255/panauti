package com.ishanitech.ipalika.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class AgeCalculatorUtil {

	
	public static String calculateAge(String dateOfBirth) {
		String calculatedAge = "";
		
		
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			date = (Date)formatter.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		formatter = new SimpleDateFormat("yyyy-mm-dd");
		String adjustedDOB = formatter.format(date).toString();
		System.out.println("adjustedDOB ###########3"  +  adjustedDOB);
		
		LocalDate DOB = LocalDate.parse(adjustedDOB);

		LocalDate now = LocalDate.now();
 
        Period diff = Period.between(DOB, now);
 
        System.out.printf("\nDifference is %d years, %d months and %d days old\n\n", 
                    diff.getYears(), diff.getMonths(), diff.getDays());
		
        String extractedYear = convertToDevanagari(Integer.toString(diff.getYears()));
        String extractedMonth = convertToDevanagari(Integer.toString(diff.getMonths()));
        String extractedDay = convertToDevanagari(Integer.toString(diff.getDays()));
        
        
        calculatedAge = extractedYear + " बर्ष  " + extractedMonth + " महिना " + extractedDay + " दिन "; 
		
        
		return calculatedAge;
	}
	
	private static String convertToDevanagari(String englishText) {
		return englishText.replaceAll("0", "").replaceAll("1", "१").replaceAll("2", "२").replaceAll("3", "३").replaceAll("4", "४")
				.replaceAll("5", "५").replaceAll("6", "६").replaceAll("7", "७").replaceAll("8", "८").replaceAll("9", "९");
	}
}
