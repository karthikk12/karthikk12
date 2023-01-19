package com.example.Printer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.example.Initializer.Launcher;

public class FileShifter {

	public static String copyOutlineContents() throws IOException {

		String masterFilePath = "C:\\Users\\" + Launcher.getUserDetails()
				+ "\\Downloads\\chromedriver_win32\\Reconciliation_Report_Outline1.xlsx";

		String tempFile = null;

		File masterFileLocation = new File(masterFilePath);

		File directory = new File("C:\\Users\\" + Launcher.getUserDetails() + "\\Downloads\\Reconciliaton Report");

		String dtAppender = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());

		String destination = "C:\\Users\\" + Launcher.getUserDetails()
				+ "\\Downloads\\Reconciliaton Report\\Reconciliation_Report_Data" + dtAppender + ".xlsx";

		tempFile = destination;

		File destinationLocation = new File(destination);

		if (!directory.exists()) {

			directory.mkdir();

		}

		FileUtils.copyFile(masterFileLocation, destinationLocation);

		return tempFile;
	}

}
