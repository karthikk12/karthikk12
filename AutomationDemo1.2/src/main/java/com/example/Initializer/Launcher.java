package com.example.Initializer;

import java.io.IOException;
import java.text.ParseException;

import com.example.ReportsProcessor.SummaryHpReport;

public class Launcher {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		System.out.println("Initializing pgm....");

		SummaryHpReport.getListofDatesForFuture();

		SummaryHpReport.begin();
	}

	public static void ender() {

		System.out.println("******* Process Completed *********");
	}

}
