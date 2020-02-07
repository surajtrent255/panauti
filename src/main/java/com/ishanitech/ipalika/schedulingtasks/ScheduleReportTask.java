package com.ishanitech.ipalika.schedulingtasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ishanitech.ipalika.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduleReportTask {
	private final ReportService reportService;
	
	public ScheduleReportTask(ReportService reportService) {
		this.reportService = reportService;
	}

	//Run Report Generation Service Everyday at midnight
	//@Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(fixedRate = 60000)
	public void generateReport() {
		log.info("Generate Report Called");
		reportService.generateReport();
	}
}
