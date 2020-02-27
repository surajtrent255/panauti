package com.ishanitech.ipalika.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AgeCalculatorUtil {

	
	
	public static String calculateAge(String dateOfBirth) {
		String calculatedAge = "";
		
		
//		String [] dateParts = dateOfBirth.split("-");
//		String day = dateParts[0];
//		String month = dateParts[1];
//		String year = dateParts[2];
		
		
		
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
		calculatedAge = diff.getYears() + " years " + diff.getMonths() + " months " + diff.getDays() + " days "; 
		return calculatedAge;
	}
}
