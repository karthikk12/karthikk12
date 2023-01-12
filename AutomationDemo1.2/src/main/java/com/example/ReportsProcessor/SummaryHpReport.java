package com.example.ReportsProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.DataSetter.ReportDataSetter;
import com.example.Enums.ChromeType;
import com.example.ReportsDto.SummaryHpDto;

public class SummaryHpReport {

	static StringBuilder builder = new StringBuilder();

	static ReportDataSetter reportDataSetter = new ReportDataSetter();

	static List<SummaryHpDto> reportDtoList = new CopyOnWriteArrayList<SummaryHpDto>();

	static List<String> dateList = new ArrayList<String>();

	static SummaryHpDto summaryHpDto = new SummaryHpDto();

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");

	static String todayDate = String.valueOf(LocalDateTime.now());

	static String mainPage = "http://report.nml.com/sites/cbpbi/Reports/Forms/current.aspx?RootFolder=%2fsites%2fcbpbi%2fReports%2fReconciliation&FolderCTID=0x0120003A31CE0010038F4D85F3E8E2E06252C3";

	static ReconciliationSummaryVantageStarter vantageStarter = new ReconciliationSummaryVantageStarter();

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		System.out.println("Initializing pgm....");

		getListofDatesForFuture();

		begin();
	}

	public static void begin() throws InterruptedException, IOException, ParseException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\FWR7591\\Downloads\\chromedriver_win32-108\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions().addArguments(String.valueOf(ChromeType.openType.getAction()));

		WebDriver driver = new ChromeDriver(options);

		String secondPage = "http://report.nml.com/sites/cbpbi/Reports/Reconciliation/Reconciliation_Summary_HP.rdl?Web=1";

		String response = supplylogin(secondPage, driver);

		int responseWaiter = 0;

		while (!StringUtils.containsIgnoreCase(driver.getPageSource().toString(), ">Reconciliation Report - HP")) {

			responseWaiter++;

			System.out.println("Waiting Summary Hp page Response......" + responseWaiter);

			Thread.sleep(3000);

			if (responseWaiter == Integer.parseInt(ChromeType.waitTime.getAction())) {

				begin();

				responseWaiter = 0;

			}

		}

		String tableResponse = driver.getPageSource();

		// System.out.println(tableResponse);

		if (StringUtils.containsIgnoreCase(tableResponse, ">Reconciliation Report - HP")) {

			List<WebElement> elements = driver.findElements(By.xpath(
					"//div[@id='VisibleReportContentm_sqlRsWebPart_ctl00_ReportViewer_ctl09']//div/table/tbody/tr/*"));

			for (WebElement eachElement : elements) {

				// System.out.println(eachElement.getText());

				builder.append(eachElement.getText().trim());
			}

			getDataFromPageContect(builder.toString());

		}

		else {

			System.out.println("Some error Occurred....");
		}

	}

	public static String supplylogin(String secondPage, WebDriver driver) throws InterruptedException, ParseException {

		try {

			driver.manage().window().maximize();

			driver.get(mainPage);

			Thread.sleep(1000);

			driver.get(secondPage);

			Thread.sleep(2000);

			String startDate = getStartDate();

			String endDate = getEndDate();

			String response = driver.getPageSource();

			if (StringUtils.containsIgnoreCase(response, ">Start date")
					&& StringUtils.containsIgnoreCase(response, ">End date")) {

				driver.findElement(By.name("m_sqlRsWebPart$ctl00$ctl19$ctl06$ctl03$txtValue")).sendKeys(startDate);
				driver.findElement(By.name("m_sqlRsWebPart$ctl00$ctl19$ctl06$ctl05$txtValue")).sendKeys(endDate);

				driver.findElement(By.name("m_sqlRsWebPart$ctl00$ctl19$ApplyParameters")).click();

			}

		} catch (TimeoutException e) {

			System.out.println("Reconciliation Web site is Down..Please try again after Sometimes");

			e.printStackTrace();
		}

		return driver.getPageSource();
	}

	public static void getListofDatesForFuture() {

		LocalDate todayDate = LocalDate.now();
		// 9
		LocalDate checkStartdate = todayDate.minusDays(2);
		// 6
		LocalDate checkEnddate = checkStartdate.minusDays(3);

		dateList.add(String.valueOf(formatter.format(checkEnddate)));

		while (!checkStartdate.toString().equals(checkEnddate.toString())) {

			checkEnddate = checkEnddate.plusDays(1);

			dateList.add(String.valueOf(formatter.format(checkEnddate)));

		}

	}

	private static String getEndDate() throws ParseException {

		LocalDate todayDate = LocalDate.now();

		LocalDate endDate = todayDate.minusDays(2);

		return formatter.format(endDate).toString();

	}

	private static String getStartDate() throws ParseException {

		LocalDate todayDate = LocalDate.now().minusDays(2);

		LocalDate startDate = todayDate.minusDays(3);

		return formatter.format(startDate).toString();

	}

	public static void getDataFromPageContect(String builder) throws InterruptedException, IOException, ParseException {

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - HP")
				&& StringUtils.containsIgnoreCase(builder, "Message Discrepancy in HP")
				&& StringUtils.containsIgnoreCase(builder, "Date")
				&& StringUtils.containsIgnoreCase(builder, "Source")) {

			String reportData = StringUtils.substringBetween(builder.toString(), "Difference",
					"Message Discrepancy in HP");

			dataSetterInDto(reportData.trim());

			// System.out.println(reportData);

		}

		if (StringUtils.containsIgnoreCase(builder, "Reconciliation Report - HP")
				&& !StringUtils.containsIgnoreCase(builder, "Message Discrepancy in HP")
				&& StringUtils.containsIgnoreCase(builder, "Date")
				&& StringUtils.containsIgnoreCase(builder, "Source")) {

			String reportData = StringUtils.substringAfter(builder.toString(), "Difference");

			// System.out.println(reportData);

			dataSetterInDto(reportData.trim());

		}

	}

	public static void dataSetterInDto(String reportData) throws InterruptedException, IOException, ParseException {

		reportDtoList = reportDataSetter.setDataInRespectiveFields(reportData, summaryHpDto, reportDtoList);

		System.out.println("Summary Hp Page Data Successful........");

		prepareSecondReport(reportDtoList);

	}

	public static void prepareSecondReport(List<SummaryHpDto> reportDtoList)
			throws InterruptedException, IOException, ParseException {

		vantageStarter.beginVantageLogin(reportDtoList, dateList);

	}

}
