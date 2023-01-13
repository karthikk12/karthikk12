package com.example.DataSetter;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.example.ReportsDto.MrrSummaryDto;
import com.example.ReportsDto.RiverRunDto;
import com.example.ReportsDto.SummaryHpDto;
import com.example.ReportsDto.VantageDto;

public class ReportDataSetter {

	public List<SummaryHpDto> setDataInRespectiveFields(String reportData, SummaryHpDto summaryHpDto,
			List<SummaryHpDto> reportDtoList) {

		int counter = 0;

		Scanner scanner = new Scanner(reportData);

		while (scanner.hasNext()) {

			counter++;

			if (counter == 1) {

				summaryHpDto.setDate(scanner.nextLine());
			}
			if (counter == 2) {

				summaryHpDto.setSource(scanner.nextLine());
			}
			if (counter == 3) {

				summaryHpDto.setNmExchange(scanner.nextLine());
			}
			if (counter == 4) {

				summaryHpDto.setHpExchange(scanner.nextLine());
			}
			if (counter == 5) {

				String diffValue = scanner.nextLine();

				if (!StringUtils.equals(diffValue, "0")) {

					diffValue = "-" + diffValue;

				}

				summaryHpDto.setDifference(diffValue);
			}

			if (counter == 6) {

				reportDtoList.add(summaryHpDto);

				summaryHpDto = new SummaryHpDto();

				counter = 0;
			}

		}
		reportDtoList.add(summaryHpDto);

		return reportDtoList;

	}

	public List<VantageDto> setVantageInRespectiveFields(String reportData, List<VantageDto> vantageDtoList,
			VantageDto vantageDto) {

		int counter = 0;

		Scanner scanner = new Scanner(reportData);

		while (scanner.hasNext()) {

			counter++;

			if (counter == 1) {

				vantageDto.setDate(scanner.nextLine());
			}
			if (counter == 2) {

				vantageDto.setContentType(scanner.nextLine());
			}
			if (counter == 3) {

				vantageDto.setVantage(scanner.nextLine());
			}
			if (counter == 4) {

				vantageDto.setVantageHp(scanner.nextLine());
			}
			if (counter == 5) {

				String diffValue = scanner.nextLine();

				if (!StringUtils.equals(diffValue, "0")) {

					diffValue = "-" + diffValue;

				}

				vantageDto.setDifference(diffValue);
			}

			if (counter == 6) {

				vantageDtoList.add(vantageDto);

				vantageDto = new VantageDto();

				counter = 0;
			}

		}
		vantageDtoList.add(vantageDto);

		return vantageDtoList;

	}

	public List<MrrSummaryDto> setMrrDataInFields(String reportData, MrrSummaryDto mrrSummaryDto,
			List<MrrSummaryDto> mrrSummaryDtoList) {

		int counter = 0;

		Scanner scanner = new Scanner(reportData);

		while (scanner.hasNext()) {

			counter++;

			if (counter == 1) {

				mrrSummaryDto.setDate(scanner.nextLine());
			}
			if (counter == 2) {

				mrrSummaryDto.setSource(scanner.nextLine());
			}
			if (counter == 3) {

				mrrSummaryDto.setNmExchange(scanner.nextLine());
			}
			if (counter == 4) {

				mrrSummaryDto.setMrr(scanner.nextLine());
			}
			if (counter == 5) {

				String diffValue = scanner.nextLine();

				if (!StringUtils.equals(diffValue, "0")) {

					diffValue = "-" + diffValue;

				}

				mrrSummaryDto.setDifference(diffValue);
			}

			if (counter == 6) {

				mrrSummaryDtoList.add(mrrSummaryDto);

				mrrSummaryDto = new MrrSummaryDto();

				counter = 0;
			}

		}
		mrrSummaryDtoList.add(mrrSummaryDto);

		return mrrSummaryDtoList;

	}

	public List<RiverRunDto> setRiverRunDataInFileds(String report, List<RiverRunDto> riverRunDtoList,
			RiverRunDto riverRunDto) {

		int counter = 0;

		Scanner scanner = new Scanner(report);

		while (scanner.hasNext()) {

			counter++;

			if (counter == 1) {

				riverRunDto.setDate(scanner.nextLine());
			}
			if (counter == 2) {

				riverRunDto.setContentType(scanner.nextLine());
			}
			if (counter == 3) {

				riverRunDto.setRiverRunData(scanner.nextLine());
			}
			if (counter == 4) {

				riverRunDto.setHp(scanner.nextLine());
			}
			if (counter == 5) {

				String diffValue = scanner.nextLine();

				if (!StringUtils.equals(diffValue, "0")) {

					diffValue = "-" + diffValue;

				}

				riverRunDto.setDifference(diffValue);
			}

			if (counter == 6) {

				riverRunDtoList.add(riverRunDto);

				riverRunDto = new RiverRunDto();

				counter = 0;
			}

		}
		riverRunDtoList.add(riverRunDto);

		return riverRunDtoList;
	}

}
