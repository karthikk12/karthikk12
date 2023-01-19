package com.example.Initializer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.example.ReportsProcessor.SummaryHpReport;

public class Launcher {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		System.out.println("******* Initializing pgm ********");

		getTimer("Start Time");

		System.out.println();

		SummaryHpReport.getListofDatesForFuture();

		getUserDetails();

		SummaryHpReport.begin();
	}

	private static void getTimer(String position) {

		String time = new SimpleDateFormat("hh:mm:ss").format(new Date());

		System.out.println(position + " : " + time);

	}

	public static void ender() {

		getTimer("End Time");

		System.out.println("******* Process Completed *********");
	}

	public static String getUserDetails() {

		String userNm = "";

		Map<String, String> ipDetails = new HashMap<>();

		ipDetails.put("10.160.83.16", "FWR7591");
		ipDetails.put("10.60.79.220", "MAJ6604");
		ipDetails.put("10.60.76.243", "EVH9795");

		try {

			InetAddress inetAddress = InetAddress.getLocalHost();

			String validIp = StringUtils.substringAfter(inetAddress.toString(), "/").trim();

			Iterator<String> itr = ipDetails.keySet().iterator();

			while (itr.hasNext()) {

				String ipAdd = itr.next();

				if (ipAdd.equals(validIp)) {

					String NmId = ipDetails.get(ipAdd);

					userNm = NmId;

					break;

				}

			}
		} catch (UnknownHostException e) {

			System.out.println("UnKnown Host Exception Caused.." + e.getMessage());
		}
		return userNm;
	}

}
