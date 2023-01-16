package com.example.Printer;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class FileShifter {

	public static String copyOutlineContents() throws IOException {

		String masterFilePath = "C:\\Users\\FWR7591\\Downloads\\Reconciliation_Outline\\Reconciliation_Report_Outline.xlsx";

		String tempFile = "";

		File masterFileLocation = new File(masterFilePath);

		File directory = new File("C:\\Users\\FWR7591\\Downloads\\Reconciliaton Report");

		String destination = "C:\\Users\\FWR7591\\Downloads\\Reconciliaton Report\\Reconciliation_Report_Data"
				+ new Date().getSeconds() + ".xlsx";

		tempFile = destination;

		File destinationLocation = new File(destination);

		if (!directory.exists()) {

			directory.mkdir();

		}

		FileUtils.copyFile(masterFileLocation, destinationLocation);

		return tempFile;
	}

}
